package geneticalgorithm.mutation;

import geneticalgorithm.individual.BitSetIndividual;
import utils.NumberUtils;

public class BitSetMutator implements Mutator<BitSetIndividual> {

    double mutationProbability;



    public BitSetMutator(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    @Override
    public boolean mutate(BitSetIndividual individual) {
        boolean mutated = false;
        for(int i=0; i<individual.getNumGenes(); i++) {
            if(NumberUtils.rnd.nextFloat() < mutationProbability) {
                individual.getGenome().flip(i);
                mutated = true;
            }
        }
        return mutated;
    }

}
