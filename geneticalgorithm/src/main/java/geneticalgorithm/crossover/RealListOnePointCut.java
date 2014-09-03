package geneticalgorithm.crossover;

import geneticalgorithm.individual.RealListIndividual;
import utils.NumberUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 * This algorithm always returns two children.
 * A single crossover point on both parents' organism lists is selected.
 * All data beyond that point in either organism string is swapped between the two parent organisms.
 * The resulting organisms are the children.
 * As described here:
 * http://en.wikipedia.org/wiki/Crossover_(genetic_algorithm)#One-point_crossover
 */
public class RealListOnePointCut implements CrossOver<RealListIndividual> {
    @Override
    public Collection<RealListIndividual> breed(RealListIndividual mate0, RealListIndividual mate1) {
        double[] genome0 = mate0.getGenome();
        double[] genome1 = mate1.getGenome();

        double[] newGenome01 = Arrays.copyOf(genome0, genome0.length);
        double[] newGenome10 = Arrays.copyOf(genome1, genome1.length);

        for(int i = NumberUtils.rnd.nextInt(newGenome01.length);i<newGenome01.length;i++) {
            newGenome01[i] = genome1[i];
            newGenome10[i] = genome0[i];
        }
        return Arrays.asList(new RealListIndividual(newGenome01,mate0.getMin(),mate0.getMax()),new RealListIndividual(newGenome10,mate0.getMin(),mate0.getMax()));
    }
}
