package neuralnetworktrain.genetic.evaluation;

import geneticalgorithm.evaluation.FitnessFunction;
import geneticalgorithm.individual.RealListIndividual;
import neuralnetwork.Network;
import neuralnetwork.builder.RealListNetworkFactory;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;

/**
 * The fitness function of RealListIndividuals that represent a neural network
 */
public class RealListNetworkFitnessFunction extends NetworkFitnessFunction<RealListIndividual> implements FitnessFunction<RealListIndividual> {


    public RealListNetworkFitnessFunction() {
    }

    @Override
    public Network createNetworkFromIndividual(RealListIndividual individual) {
        return new RealListNetworkFactory(individual.getGenome()).createNetwork(feature[0].length,transferFunction,layersSize);
    }

    public RealListNetworkFitnessFunction(double[][] feature, double[][] observed, ErrorEvaluator errorEvaluator, TransferFunction transferFunction, int... layersSize) {
        super(feature, observed, errorEvaluator, transferFunction,layersSize);
    }



}
