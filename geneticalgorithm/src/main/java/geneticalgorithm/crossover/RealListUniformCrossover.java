package geneticalgorithm.crossover;


import geneticalgorithm.individual.BinaryIndividual;
import geneticalgorithm.individual.RealListIndividual;
import utils.NumberUtils;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;

/**
 * This algorithm always returns two children.
 * For every index i in the genome a number is selected with equal probability from either the first
 * individual genome or the second. It is set in the i-th place in the first child. The i-th number
 * from the non-chosen parent is set in the second child.
 */
public class RealListUniformCrossover implements CrossOver<RealListIndividual> {


    @Override
    public Collection<RealListIndividual> breed(RealListIndividual mate0, RealListIndividual mate1) {
        double[] genome0 = mate0.getGenome();
        double[] genome1 = mate1.getGenome();

        double[] newGenome01 = new double[genome0.length];
        double[] newGenome10 = new double[genome0.length];

        for (int i = 0; i < genome0.length; i++) {
            boolean randomBoolean = NumberUtils.rnd.nextBoolean();
            newGenome01[i]= randomBoolean? genome0[i] : genome1[i];
            newGenome10[i]= randomBoolean? genome1[i] : genome0[i];
        }

        return Arrays.asList(new RealListIndividual(newGenome01,mate0.getMin(),mate0.getMax()),new RealListIndividual(newGenome10,mate0.getMin(),mate0.getMax()));
    }
}
