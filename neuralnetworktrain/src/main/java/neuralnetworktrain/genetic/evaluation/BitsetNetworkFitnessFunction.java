package neuralnetworktrain.genetic.evaluation;

import geneticalgorithm.evaluation.FitnessFunction;
import geneticalgorithm.individual.BinaryIndividual;
import neuralnetwork.Network;
import neuralnetwork.builder.BitsetNetworkFactory;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;

/**
 * The fitness function of BitSetIndividuals that represent a neural network.
 * The individuals bitset must have length that is equal to genesForOneNumber*[number of neurons needed for the network]*[number of parameters needed per neuron]
 */
public class BitsetNetworkFitnessFunction extends NetworkFitnessFunction<BinaryIndividual> implements FitnessFunction<BinaryIndividual> {

    /**
     * How many bits represent one number.
     */
    int genesForOneNumber = 4;

    public BitsetNetworkFitnessFunction() {
    }

    @Override
    public Network createNetworkFromIndividual(BinaryIndividual individual) {
        return new BitsetNetworkFactory(individual.getGenome(),-1,1,genesForOneNumber).createNetwork(feature[0].length,transferFunction,layersSize);
    }

    public BitsetNetworkFitnessFunction(double[][] feature, double[][] observed, ErrorEvaluator errorEvaluator, int genesForOneNumber, TransferFunction transferFunction, int... layersSize) {
        super(feature, observed, errorEvaluator, transferFunction,layersSize);
        this.genesForOneNumber = genesForOneNumber;
    }

    public int getGenesForOneNumber() {
        return genesForOneNumber;
    }

    public void setGenesForOneNumber(int genesForOneNumber) {
        this.genesForOneNumber = genesForOneNumber;
    }

}
