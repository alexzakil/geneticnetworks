package geneticalgorithm.individual;

public abstract class Individual implements Comparable<Individual>{

    double fitness;

    public Double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public int compareTo(Individual o) {
        return getFitness().compareTo(o.getFitness());
    }

    public abstract Individual createClone();

}
