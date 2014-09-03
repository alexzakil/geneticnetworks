package geneticalgorithm.crossover;

import geneticalgorithm.individual.Individual;

import java.util.Collection;

/**
 * An algorithm for creating children from two individuals
 * @param <T>
 */
public interface CrossOver<T extends Individual>{
    /**
     *
     * @param mate0 the first individual to mate
     * @param mate1 the second individual to mate
     * @return a list of children of the two individuals
     */
    public Collection<T> breed(T mate0, T mate1);
}
