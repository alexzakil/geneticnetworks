package geneticalgorithm.individual;

import java.util.BitSet;
import java.util.Random;

/**
 * A factory to create individuals represented by a randomly generated binary string
 */
public class BinaryIndividualFactory implements IndividualFactory<BinaryIndividual> {
    private final Random rnd;
    int numBits;

    public BinaryIndividualFactory(int numBits) {
        this.numBits = numBits;
        rnd = new Random();
    }

    @Override
    public BinaryIndividual createIndividual() {
        BitSet genome = new BitSet(numBits);
        for(int j=0;j< numBits;j++) {
            genome.set(j,rnd.nextBoolean());
        }

        return new BinaryIndividual(genome, numBits);
    }
}
