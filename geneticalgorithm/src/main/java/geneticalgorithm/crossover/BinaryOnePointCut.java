package geneticalgorithm.crossover;



import geneticalgorithm.individual.BinaryIndividual;
import utils.NumberUtils;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;

/**
 * This algorithm always returns two children.
 * A single crossover point on both parents' organism bitsets is selected.
 * All data beyond that point in either organism string is swapped between the two parent organisms.
 * The resulting organisms are the children.
 * As described here:
 * http://en.wikipedia.org/wiki/Crossover_(genetic_algorithm)#One-point_crossover
 */
public class BinaryOnePointCut implements CrossOver<BinaryIndividual>{


    @Override
    public Collection<BinaryIndividual> breed(BinaryIndividual mate0, BinaryIndividual mate1) {
            BitSet genome0 = mate0.getGenome();
            BitSet genome1 = mate1.getGenome();

            BitSet genome01 = (BitSet) genome0.clone();
            BitSet genome10 = (BitSet) genome1.clone();

            int numGenes = mate0.getNumBits();

            for(int i = NumberUtils.rnd.nextInt(numGenes);i<numGenes;i++) {
                genome01.set(i, genome1.get(i));
                genome10.set(i, genome0.get(i));
            }

            return Arrays.asList(new BinaryIndividual(genome01, numGenes),new BinaryIndividual(genome10, numGenes));
        }
}
