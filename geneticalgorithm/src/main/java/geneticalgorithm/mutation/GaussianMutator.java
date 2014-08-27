package geneticalgorithm.mutation;

import geneticalgorithm.individual.RealListIndividual;
import utils.NumberUtils;

public class GaussianMutator implements Mutator<RealListIndividual> {

    double mutationProbability;



    public GaussianMutator(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    @Override
    public boolean mutate(RealListIndividual individual) {
        if(mutationProbability <= 0) {
            return false;
        }

        boolean mutated = false;
        double[] genome = individual.getGenome();
        for (int i = 0; i < genome.length; i++) {
            if(NumberUtils.rnd.nextFloat() < mutationProbability) {
                double gaussian = NumberUtils.rnd.nextGaussian();
                genome[i] = Math.max(individual.getMin(),Math.min(individual.getMax(),genome[i] + gaussian));
                mutated = true;
            }
        }

        return mutated;
    }

}
