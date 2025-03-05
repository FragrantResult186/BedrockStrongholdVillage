package fragrant.generator;

import fragrant.utils.BE_Random;
import fragrant.utils.Position;
import nl.jellejurre.biomesampler.BiomeSampler;

public class BE_StrongholdGen {
    public static Position.BlockPos[] findStrongholds(long worldSeed, BiomeSampler biomeSampler, boolean checkBiomes) {
        Position.BlockPos[] posArr = new Position.BlockPos[3];
        int[] mt = BE_Random.genNums((int) worldSeed, 2);

        double angle = 6.2831855 * BE_Random.int2Float(mt[0]);
        int chunkDist = BE_Random.uMod(mt[1], 16) + 40;

        int count = 0;
        while (count < 3) {
            int cx = (int) Math.floor(Math.cos(angle) * chunkDist);
            int cz = (int) Math.floor(Math.sin(angle) * chunkDist);

            boolean found = false;
            outer:
            for (int x = cx - 8; x < cx + 8; x++) {
                for (int z = cz - 8; z < cz + 8; z++) {
                    Position.ChunkPos chunkPos = new Position.ChunkPos(x, z);
                    if (BE_VillageGen.isVillageChunk(worldSeed, chunkPos, biomeSampler, checkBiomes)) {
                        posArr[count++] = chunkPos.toBlockPos();
                        found = true;
                        break outer;
                    }
                }
            }

            if (found) {
                angle += 1.8849558;
                chunkDist += 8;
            } else {
                angle += 0.78539819;
                chunkDist += 4;
            }
        }

        return posArr;
    }
}