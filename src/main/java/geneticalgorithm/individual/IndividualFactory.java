package geneticalgorithm.individual;

public interface IndividualFactory<T extends Individual> {
    public T createIndividual();
}
