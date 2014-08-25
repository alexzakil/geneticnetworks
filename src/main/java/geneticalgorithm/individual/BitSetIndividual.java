package geneticalgorithm.individual;

import java.util.BitSet;

public class BitSetIndividual extends Individual{

    BitSet genome;
    int numGenes;


    public BitSetIndividual(BitSet genome, int numGenes) {
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
        BitSetIndividual individual = new BitSetIndividual((BitSet) genome.clone(), numGenes);
        individual.setFitness(getFitness());
        return individual;
    }
}
