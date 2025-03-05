package fragrant;

import nl.jellejurre.biomesampler.BiomeSampler;
import fragrant.generator.BE_StrongholdGen;
import fragrant.utils.Position;

public class BE_Stronghold {
    public static Position.BlockPos[] getSH(long worldSeed, BiomeSampler biomeSampler) {
        return BE_StrongholdGen.findStrongholds(worldSeed, biomeSampler, true);
    }

    public static Position.BlockPos[] getSH_NoBiome(long worldSeed) {
        return BE_StrongholdGen.findStrongholds(worldSeed, null, false);
    }
}
