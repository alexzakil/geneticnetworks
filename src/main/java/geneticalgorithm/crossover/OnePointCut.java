package geneticalgorithm.crossover;



import geneticalgorithm.individual.BitSetIndividual;
import utils.NumberUtils;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;

public class OnePointCut implements CrossOver<BitSetIndividual>{


    @Override
    public Collection<BitSetIndividual> breed(BitSetIndividual mate0, BitSetIndividual mate1) {
            BitSet genome0 = mate0.getGenome();
            BitSet genome1 = mate1.getGenome();

            BitSet genome01 = (BitSet) genome0.clone();
            BitSet genome10 = (BitSet) genome1.clone();

            int numGenes = mate0.getNumGenes();

            for(int i = NumberUtils.rnd.nextInt(numGenes);i<numGenes;i++) {
                genome01.set(i, genome1.get(i));
                genome10.set(i, genome0.get(i));
            }

            return Arrays.asList(new BitSetIndividual(genome01, numGenes),new BitSetIndividual(genome10, numGenes));
        }
}
