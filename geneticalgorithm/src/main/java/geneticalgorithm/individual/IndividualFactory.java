package geneticalgorithm.individual;

/**
 * A factory to create Individuals
 * @param <T>
 */
public interface IndividualFactory<T extends Individual> {
    public T createIndividual();
}
