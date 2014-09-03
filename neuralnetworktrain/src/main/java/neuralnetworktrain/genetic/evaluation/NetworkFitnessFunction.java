package neuralnetworktrain.genetic.evaluation;

import geneticalgorithm.evaluation.FitnessFunction;
import geneticalgorithm.individual.Individual;
import neuralnetwork.Network;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;

/**
 * A fitness function that rates individuals that represent neural networks.
 * The better the network is in classifying certain data the higher the fitness of the individual
 * @param <T> the class of the Individuals that can be evaluated.
 */
public abstract class NetworkFitnessFunction<T extends Individual> implements FitnessFunction<T> {
    /**
     * The transfer function to use in the network
     */
    TransferFunction transferFunction;
    /**
     * The input of the network
     */
    double[][] feature;
    /**
     * The expected output of the network
     */
    double[][] observed;

    /**
     * The size of the layers in the network
     */
    int[] layersSize;

    /**
     * The algorithm to evaluate the error of a network.
     */
    ErrorEvaluator errorEvaluator;

    public NetworkFitnessFunction() {
    }

    public NetworkFitnessFunction(double[][] feature, double[][] observed, ErrorEvaluator errorEvaluator, TransferFunction transferFunction, int... layersSize) {
        this.layersSize = layersSize;
        setFeature(feature);
        setObserved(observed);
        this.errorEvaluator = errorEvaluator;
        this.transferFunction = transferFunction;
    }

    public int[] getLayersSize() {
        return layersSize;
    }

    public void setLayersSize(int[] layersSize) {
        this.layersSize = layersSize;
    }

    public void setErrorEvaluator(ErrorEvaluator errorEvaluator) {
        this.errorEvaluator = errorEvaluator;
    }

    public void setFeature(double[][] feature) {
        this.feature = feature;
    }

    public void setObserved(double[][] observed) {
        if(feature.length != observed.length) {
            throw new RuntimeException("Provided "+feature.length+" input features and "+observed.length+" observed output. Must be equal");
        }
        if(observed[0].length > layersSize[layersSize.length-1]) {
            throw new RuntimeException("Provided observed data with length "+observed[0].length+" while there are "+layersSize[layersSize.length-1]+" neurons in output layer. Must be less than or equal");
        }

        this.observed = observed;
    }

    public double[][] getFeature() {
        return feature;
    }

    public double[][] getObserved() {
        return observed;
    }

    @Override
    public double getFitness(T individual) {

        Network networkFromIndividual = createNetworkFromIndividual(individual);
        return 1- errorEvaluator.evaluateError(networkFromIndividual,feature,observed);
    }

    /**
     * Any implementation must be able to translate an individual to a neural network
     * @param individual The individual to translate
     * @return a neural network that is represented by the individual
     */
    public abstract Network createNetworkFromIndividual(T individual);
}
