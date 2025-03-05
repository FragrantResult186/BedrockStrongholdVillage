package fragrant.generator;

import java.util.Set;
import fragrant.utils.BE_Random;
import fragrant.utils.Position;
import nl.jellejurre.biomesampler.BiomeSampler;
import nl.jellejurre.biomesampler.minecraft.Biome;

public class BE_VillageGen {
    private static final Set<Biome> VILLAGE_BIOMES = Set.of(
            Biome.PLAINS,
            Biome.SUNFLOWER_PLAINS,
            Biome.SNOWY_PLAINS,
            Biome.DESERT,
            Biome.TAIGA,
            Biome.SNOWY_TAIGA,
            Biome.SAVANNA,
            Biome.MEADOW
    );

    public static boolean isVillageChunk(long worldSeed, Position.ChunkPos pos, BiomeSampler biomeSampler, boolean checkBiomes) {
        int adjX = pos.x(), adjZ = pos.z();
        if (adjX < 0) adjX -= 33;
        if (adjZ < 0) adjZ -= 33;

        int seed = 10387312 + (int) worldSeed - 245998635 * (adjZ / 34) - 1724254968 * (adjX / 34);
        int[] mt = BE_Random.genNums(seed, 4);

        int r1 = BE_Random.uMod(mt[0], 26), r2 = BE_Random.uMod(mt[1], 26);
        int r3 = BE_Random.uMod(mt[2], 26), r4 = BE_Random.uMod(mt[3], 26);

        int x_offset = pos.x() % 34, z_offset = pos.z() % 34;
        if (x_offset < 0) x_offset += 34;
        if (z_offset < 0) z_offset += 34;

        boolean isValidChunk = (r1 + r2) / 2 == x_offset && (r3 + r4) / 2 == z_offset;

        if (!checkBiomes) {
            return isValidChunk;
        }

        if (isValidChunk && biomeSampler != null) {
            Position.BlockPos blockPos = pos.toBlockPos();
            Biome biome = biomeSampler.getBiomeFromBlockPos(blockPos.x(), 0, blockPos.z());
            return VILLAGE_BIOMES.contains(biome);
        }

        return false;
    }
}