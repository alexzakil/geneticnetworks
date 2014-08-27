package neuralnetworktrain.genetic.evaluator;

import geneticalgorithm.evaluation.BitsetEvaluator;
import geneticalgorithm.individual.BitSetIndividual;
import neuralnetwork.Network;
import neuralnetwork.builder.BitsetNetworkFactory;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;

public class BitsetNetworkEvaluator extends NetworkEvaluator<BitSetIndividual> implements BitsetEvaluator<BitSetIndividual> {


    int genesForOneNumber = 4;

    public BitsetNetworkEvaluator() {
    }

    @Override
    public Network createNetworkFromIndividual(BitSetIndividual individual) {
        return new BitsetNetworkFactory(individual.getGenome(),-1,1,genesForOneNumber).createNetwork(feature[0].length,transferFunction,layersSize);
    }

    public BitsetNetworkEvaluator(double[][] feature, double[][] observed, ErrorEvaluator errorEvaluator, int genesForOneNumber, TransferFunction transferFunction, int... layersSize) {
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
