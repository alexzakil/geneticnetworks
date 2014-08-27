package geneticalgorithm.mutation;


import geneticalgorithm.individual.Individual;

public interface Mutator<I extends Individual> {
    public boolean mutate(I individual);

}
