package fragrant;

import fragrant.utils.Position;
import nl.jellejurre.biomesampler.BiomeSampler;

public class BE_StrongholdTest {
    public static void main(String[] args) {
        long worldSeed = 12345L;

        System.out.println("Without Biome");
        Position.BlockPos[] withoutBiomeSHs = BE_Stronghold.getSH_NoBiome(worldSeed);
        for (Position.BlockPos SH : withoutBiomeSHs) {
            System.out.printf("%s%n", SH);
        }

        System.out.println("\nWith Biome");
        BiomeSampler biomeSampler = new BiomeSampler(worldSeed);
        Position.BlockPos[] withBiomeSHs = BE_Stronghold.getSH(worldSeed, biomeSampler);
        for (Position.BlockPos SH : withBiomeSHs) {
            System.out.printf("%s%n", SH);
        }
    }
}