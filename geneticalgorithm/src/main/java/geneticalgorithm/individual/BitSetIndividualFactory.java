package geneticalgorithm.individual;

import java.util.BitSet;
import java.util.Random;

public class BitSetIndividualFactory implements IndividualFactory<BitSetIndividual> {
    private final Random rnd;
    int numGenes;

    public BitSetIndividualFactory(int numGenes) {
        this.numGenes = numGenes;
        rnd = new Random();
    }

    @Override
    public BitSetIndividual createIndividual() {
        BitSet genome = new BitSet(numGenes);
        for(int j=0;j<numGenes;j++) {
            genome.set(j,rnd.nextBoolean());
        }

        return new BitSetIndividual(genome, numGenes);
    }
}
