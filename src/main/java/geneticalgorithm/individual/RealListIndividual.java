package geneticalgorithm.individual;

public class RealListIndividual extends Individual{

    double[] genome;
    private final double min,max;

    public RealListIndividual(double[] genome, double min, double max) {
        this.genome = genome;
        this.min= min;
        this.max = max;

    }


    public double[] getGenome() {
        return genome;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    @Override
    public Individual createClone() {
        RealListIndividual individual = new RealListIndividual(genome.clone(), min, max);
        individual.setFitness(getFitness());
        return individual;
    }
}
