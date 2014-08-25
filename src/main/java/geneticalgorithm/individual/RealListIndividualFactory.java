package geneticalgorithm.individual;

import java.util.Random;

public class RealListIndividualFactory implements IndividualFactory<RealListIndividual> {
    private final Random rnd;
    private final double min,max;
    int numGenes;

    public RealListIndividualFactory(int numGenes, double min, double max) {
        this.numGenes = numGenes;
        rnd = new Random();
        this.min= min;
        this.max = max;
    }

    @Override
    public RealListIndividual createIndividual() {
        double[] genome = new double[numGenes];
        for(int j=0;j<numGenes;j++) {
            genome[j] = rnd.nextDouble()*(max-min) + min;
        }

        return new RealListIndividual(genome, min,max);
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
