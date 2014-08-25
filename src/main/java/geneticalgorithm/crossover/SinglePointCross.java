package geneticalgorithm.crossover;

import geneticalgorithm.individual.RealListIndividual;
import utils.NumberUtils;

import java.util.Arrays;
import java.util.Collection;

public class SinglePointCross implements CrossOver<RealListIndividual> {
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
