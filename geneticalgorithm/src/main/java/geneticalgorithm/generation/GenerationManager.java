package geneticalgorithm.generation;


import geneticalgorithm.evaluation.FitnessFunction;
import geneticalgorithm.individual.Individual;

import java.util.List;

/**
 * An algorithm responsible for creating the next generation from a given population
 * @param <I>
 */
public interface GenerationManager<I extends Individual> {

    /**
     *
     * @param oldGeneration the previous generation
     * @param fitnessFunction a function to measure fitness of individuals
     * @return the next generation
     */
    public List<I> nextGeneration(List<I> oldGeneration, FitnessFunction<I> fitnessFunction);
}
