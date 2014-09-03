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

    int numGenes;


    public BinaryIndividual(BitSet genome, int numGenes) {
        this.genome = genome;
        this.numGenes = numGenes;
    }


    public BitSet getGenome() {
        return genome;
    }


    public int getNumGenes() {
        return numGenes;
    }

    @Override
    public Individual createClone() {
        BinaryIndividual individual = new BinaryIndividual((BitSet) genome.clone(), numGenes);
        individual.setFitness(getFitness());
        return individual;
    }
}
