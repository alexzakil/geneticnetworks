package geneticalgorithm.crossover;


import geneticalgorithm.individual.BinaryIndividual;
import utils.NumberUtils;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;

/**
 * This algorithm always returns two children.
 * For every index i in the genome a bit is selected with equal probability from either the first
 * individual genome or the second. It is set in the i-th place in the first child. The i-th bit
 * from the non-chosen parent is set in the second child.
 */
public class BinaryUniformCrossover implements CrossOver<BinaryIndividual> {


    @Override
    public Collection<BinaryIndividual> breed(BinaryIndividual mate0, BinaryIndividual mate1) {
        BitSet genome0 = mate0.getGenome();
        BitSet genome1 = mate1.getGenome();

        BitSet genome01 = new BitSet();
        BitSet genome10 = new BitSet();

        int numGenes = mate0.getNumBits();


        for (int i = 0; i < numGenes; i++) {
            boolean randomBoolean = NumberUtils.rnd.nextBoolean();
            genome01.set(i, randomBoolean? genome0.get(i) : genome1.get(i));
            genome10.set(i, randomBoolean? genome1.get(i) : genome0.get(i));
        }

        return Arrays.asList(new BinaryIndividual(genome01, numGenes), new BinaryIndividual(genome10, numGenes));
    }
}
