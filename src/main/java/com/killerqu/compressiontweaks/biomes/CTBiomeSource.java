package com.killerqu.compressiontweaks.biomes;

import com.alcatrazescapee.hexlands.util.Hex;
import com.killerqu.compressiontweaks.CompressionTweaks;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.RandomSupport;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CTBiomeSource extends MultiNoiseBiomeSource implements ISeededBiomeSource{

    public static final DeferredRegister<Codec<? extends BiomeSource>> BIOME_SOURCES =
            DeferredRegister.create(Registries.BIOME_SOURCE, CompressionTweaks.MODID);

    private static final List<Pair<Integer, Integer>> CRATER_HEXES = List.of(Pair.of(0,0), Pair.of(0,1), Pair.of(0,-1), Pair.of(1,0), Pair.of(1,-1), Pair.of(-1,0), Pair.of(-1,1));

    private final Climate.Parameter OCEAN_RANGE = Climate.Parameter.span(-2F, -0.19F);
    //private final Climate.Parameter COAST_RANGE = Climate.Parameter.span(-0.19F, -0.11F);

    private long worldSeed = 0L;

    public static final RegistryObject<Codec<CTBiomeSource>> RANDOM =
            BIOME_SOURCES.register(
                    "random",
                    () -> CTBiomeSource.CODEC
            );

    public static final Codec<CTBiomeSource> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.unboundedMap(Biome.CODEC, Codec.INT).fieldOf("land_biomes").forGetter(src -> src.landBiomes),
                    Codec.unboundedMap(Biome.CODEC, Codec.INT).fieldOf("coast_biomes").forGetter(src -> src.coastBiomes),
                    Codec.unboundedMap(Biome.CODEC, Codec.INT).fieldOf("ocean_biomes").forGetter(src -> src.oceanBiomes),
                    Biome.CODEC.fieldOf("crater_biome").forGetter(src -> src.craterBiome),
                    MultiNoiseBiomeSourceParameterList.CODEC.fieldOf("preset").withLifecycle(Lifecycle.stable()).forGetter(src -> src.params),
                    Codec.INT.fieldOf("size").forGetter(src -> src.size)
            ).apply(instance, CTBiomeSource::new));

    private final Map<Holder<Biome>, Integer> landBiomes;
    private final Map<Holder<Biome>, Integer> coastBiomes;
    private final Map<Holder<Biome>, Integer> oceanBiomes;
    private final Holder<Biome> craterBiome;

    private final Holder<MultiNoiseBiomeSourceParameterList> params;
    private final int size;

    public CTBiomeSource(Map<Holder<Biome>, Integer> landBiomes, Map<Holder<Biome>, Integer> coastBiomes, Map<Holder<Biome>, Integer> oceanBiomes, Holder<Biome> craterBiome, Holder<MultiNoiseBiomeSourceParameterList> params, int size){
        super(Either.right(params));
        this.landBiomes = landBiomes;
        this.coastBiomes = coastBiomes;
        this.oceanBiomes = oceanBiomes;
        this.craterBiome = craterBiome;
        this.params = params;
        this.size = size;
    }

    @Override
    public Holder<Biome> getNoiseBiome(int qx, int qy, int qz, Climate.Sampler sampler){

        //Converting to quartpos: X >> 2
        //Converting FROM quartpos: X << 2
        Hex hex = Hex.blockToHex(qx << 2, qz << 2, size);
        //Make the 7 hexes completely within the crater their own biome.
        if(CRATER_HEXES.contains(Pair.of(hex.q(), hex.r()))) return craterBiome;
        BlockPos pos = hex.center();
        int x = pos.getX() >> 2;
        int y = 128 >> 2;
        int z = pos.getZ() >> 2;
        //So now, sampling anywhere within a hex, should return the sample at the middle of the hex.
        Climate.TargetPoint sample = sampler.sample(x,y,z);
        //Taking a sample with a baseline overworld sampler allows for detection of certain hex types.
        Holder<Biome> reference = super.getNoiseBiome(sample);

        //The only parameters that should get involved are the world seed and the hex location. Anything else risks adding inconsistency.
        long seed = getSeed();
        seed ^= (long) hex.q() * 0x9E3779B97F4A7C15L; //Numbers pulled from various searches on randomizing seeds.
        seed ^= (long) hex.r() * 0xC2B2AE3D27D4EB4FL;
        seed = RandomSupport.mixStafford13(seed);
        //RNG.setSeed(seed); //This is probably better than making a new RandomSource every time lmao
        //Edit: IT WAS NOT. DO NOT DO THAT.
        RandomSource RNG = RandomSource.create(seed);

        //This could also be a biometag check like the next one, but this continentalness check seems pretty reliable.
        if(OCEAN_RANGE.distance(sample.continentalness()) == 0) return pickWeightedBiome(oceanBiomes, RNG);
        //There is not going to be one singular check that does this perfectly. So this will do.
        if(reference.is(BiomeTags.IS_BEACH) || reference.is(BiomeTags.IS_RIVER)) return pickWeightedBiome(coastBiomes, RNG);
        return pickWeightedBiome(landBiomes, RNG);

    }


    private Holder<Biome> pickWeightedBiome(Map<Holder<Biome>, Integer> biomeMap, RandomSource random){
        AtomicInteger totalWeight = new AtomicInteger(0);
        biomeMap.forEach((biomeHolder, weight) -> totalWeight.set(totalWeight.get()+weight));
        int roll = random.nextInt(totalWeight.get());
        int accumulated = 0;
        for(Map.Entry<Holder<Biome>, Integer> entry : biomeMap.entrySet()){
            accumulated += entry.getValue();
            if(roll < accumulated) return entry.getKey();
        }
        //This is just to always have a return, this should never happen.
        return biomeMap.entrySet().stream().findFirst().get().getKey();
    }



    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    public Set<Holder<Biome>> possibleBiomes(){
        Set<Holder<Biome>> ret = new HashSet<>();
        ret.addAll(landBiomes.keySet());
        ret.addAll(coastBiomes.keySet());
        ret.addAll(oceanBiomes.keySet());
        return ret;
    }

    @Override
    protected Stream<Holder<Biome>> collectPossibleBiomes() {
        return possibleBiomes().stream();
    }

    @Override
    public void setSeed(long seed){
        this.worldSeed = seed;
    }

    @Override
    public long getSeed(){
        return worldSeed;
    }

}
