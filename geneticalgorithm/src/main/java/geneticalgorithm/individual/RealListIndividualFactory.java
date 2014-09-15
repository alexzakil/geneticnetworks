package geneticalgorithm.individual;

import java.util.Random;

/**
 * A factory to create individuals represented by real numbers selected randomly from the range provided
 */
public class RealListIndividualFactory implements IndividualFactory<RealListIndividual> {
    private final Random rnd;
    private final double min,max;
    int numBits;

    public RealListIndividualFactory(int numBits, double min, double max) {
        this.numBits = numBits;
        rnd = new Random();
        this.min= min;
        this.max = max;
    }

    @Override
    public RealListIndividual createIndividual() {
        double[] genome = new double[numBits];
        for(int j=0;j< numBits;j++) {
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
