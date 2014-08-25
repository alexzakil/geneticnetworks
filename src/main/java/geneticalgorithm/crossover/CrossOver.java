package geneticalgorithm.crossover;

import geneticalgorithm.individual.Individual;

import java.util.Collection;

public interface CrossOver<T extends Individual>{
    public Collection<T> breed(T mate0, T mate1);
}
