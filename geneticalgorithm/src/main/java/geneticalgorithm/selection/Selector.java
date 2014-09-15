package geneticalgorithm.selection;

import geneticalgorithm.individual.Individual;

import java.util.Collection;
import java.util.List;

/**
 * An algorithm to select from a population of individuals a list of individuals that should mate
 * to create the next generation. Usually the fittest individuals are selected to mate.
 * The resulting list may have the same individual appear more then once.
 */
public interface Selector {
    public <T extends Individual> List<T> select(List<T> population);
}
