package geneticalgorithm.individual;

import java.util.BitSet;

/**
 * An individual that is represented by a binary string.
 */
public class BinaryIndividual extends Individual{

    /**
     * Why is it called a set when it is obviously a list or an array? Java knows.
     */
    BitSet genome;

    int numBits;


    public BinaryIndividual(BitSet genome, int numBits) {
        this.genome = genome;
        this.numBits = numBits;
    }


    public BitSet getGenome() {
        return genome;
    }


    public int getNumBits() {
        return numBits;
    }

    @Override
    public Individual createClone() {
        BinaryIndividual individual = new BinaryIndividual((BitSet) genome.clone(), numBits);
        individual.setFitness(getFitness());
        return individual;
    }
}
