package geneticalgorithm.mutation;

import geneticalgorithm.individual.BinaryIndividual;
import utils.NumberUtils;

/**
 * An algorithm to mutate a individual represented as a binary string.
 * The mutator receives a mutationProbability value, and each bit in the string
 * has this probability to be flipped (0 becomes 1 and 1 becomes 0).
 */
public class BinaryMutator implements Mutator<BinaryIndividual> {

    double mutationProbability;



    public BinaryMutator(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    @Override
    public boolean mutate(BinaryIndividual individual) {
        boolean mutated = false;
        for(int i=0; i<individual.getNumBits(); i++) {
            if(NumberUtils.rnd.nextFloat() < mutationProbability) {
                individual.getGenome().flip(i);
                mutated = true;
            }
        }
        return mutated;
    }

}
