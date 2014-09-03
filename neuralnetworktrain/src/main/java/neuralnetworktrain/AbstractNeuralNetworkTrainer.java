package neuralnetworktrain;

import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.errorevaluation.MeanSquareErrorEvaluator;

/**
 * An abstract implementation of NeuralNetworkTrainer with some common fields.
 */
public abstract class AbstractNeuralNetworkTrainer implements NeuralNetworkTrainer{
    /**
     * Maximum number of generations to run for the network. May mean different things for different algorithms
     */
    protected int maxGenerations;

    /**
     * The number of neurons of each layer that should be in newly created networks.
     */
    protected int[] layersSize;

    /**
     * How the error should be evaluated in the network
     */
    protected ErrorEvaluator errorEvaluator = new MeanSquareErrorEvaluator();

    /**
     * The maximum error allowed. The training should stop when the network has this error.
     */
    protected double wantedError;

    public int[] getLayersSize() {
        return layersSize;
    }

    public void setLayersSize(int... layersSize) {
        this.layersSize = layersSize;
    }

    public ErrorEvaluator getErrorEvaluator() {
        return errorEvaluator;
    }

    public void setErrorEvaluator(ErrorEvaluator errorEvaluator) {
        this.errorEvaluator = errorEvaluator;
    }

    public double getWantedError() {
        return wantedError;
    }

    public void setWantedError(double wantedError) {
        this.wantedError = wantedError;
    }

    public int getMaxGenerations() {
        return maxGenerations;
    }

    public void setMaxGenerations(int maxGenerations) {
        this.maxGenerations = maxGenerations;
    }
}
