package geneticalgorithm.mutation;


import geneticalgorithm.individual.Individual;

/**
 * An algorithm to randomly change the genome of an individual
 * @param <I>
 */
public interface Mutator<I extends Individual> {
    public boolean mutate(I individual);

}
