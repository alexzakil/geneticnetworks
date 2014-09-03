package geneticalgorithm.individual;

import java.util.BitSet;
import java.util.Random;

/**
 * A factory to create individuals represented by a randomly generated binary string
 */
public class BinaryIndividualFactory implements IndividualFactory<BinaryIndividual> {
    private final Random rnd;
    int numGenes;

    public BinaryIndividualFactory(int numGenes) {
        this.numGenes = numGenes;
        rnd = new Random();
    }

    @Override
    public BinaryIndividual createIndividual() {
        BitSet genome = new BitSet(numGenes);
        for(int j=0;j<numGenes;j++) {
            genome.set(j,rnd.nextBoolean());
        }

        return new BinaryIndividual(genome, numGenes);
    }
}
