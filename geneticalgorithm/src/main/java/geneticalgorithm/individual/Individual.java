package geneticalgorithm.individual;

/**
 * A representation of one individual in a genetic algorithm.
 */
public abstract class Individual implements Comparable<Individual>{

    /**
     * The fitness of the individual. The higher the fitness, the closer the individual to the solution of the problem
     * the algorithm tries to solve.
     */
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

    /**
     * @return a new individual with the same information (and therefore the same fitness)
     */
    public abstract Individual createClone();

}
