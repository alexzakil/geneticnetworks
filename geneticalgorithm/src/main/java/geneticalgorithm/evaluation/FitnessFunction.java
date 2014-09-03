package geneticalgorithm.evaluation;


import geneticalgorithm.individual.Individual;

/**
 * A function to evaluate the fitness of a individuals.
 * @param <T>
 */
public interface FitnessFunction<T extends Individual>{

    /**
     *
     * @param individual the individual to evaluate
     * @return the fitness of the individual. The more suited the individual is to the needs of the algorithm, the higher the fitness should be.
     */
    public double getFitness(T individual);
}
