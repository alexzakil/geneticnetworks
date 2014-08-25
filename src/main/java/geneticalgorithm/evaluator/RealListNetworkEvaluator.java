package geneticalgorithm.evaluator;

import geneticalgorithm.individual.BitSetIndividual;
import geneticalgorithm.individual.RealListIndividual;
import neuralnetwork.Network;
import neuralnetwork.builder.BitsetNetworkFactory;
import neuralnetwork.builder.RealListNetworkFactory;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;

public class RealListNetworkEvaluator extends NetworkEvaluator<RealListIndividual> implements  Evaluator<RealListIndividual>{


    public RealListNetworkEvaluator() {
    }

    @Override
    public Network createNetworkFromIndividual(RealListIndividual individual) {
        return new RealListNetworkFactory(individual.getGenome()).createNetwork(feature[0].length,transferFunction,layersSize);
    }

    public RealListNetworkEvaluator(double[][] feature, double[][] observed, ErrorEvaluator errorEvaluator, TransferFunction transferFunction, int... layersSize) {
        super(feature, observed, errorEvaluator, transferFunction,layersSize);
    }



}
