package geneticalgorithm.selection;

import geneticalgorithm.individual.Individual;

import java.util.Collection;
import java.util.List;

public interface Selector {
    public <T extends Individual> List<T> select(Collection<T> population);
}
