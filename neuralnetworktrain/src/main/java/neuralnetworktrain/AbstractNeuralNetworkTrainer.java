package neuralnetworktrain;

import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.errorevaluation.MeanSquareErrorEvaluator;

public abstract class AbstractNeuralNetworkTrainer implements NeuralNetworkTrainer{
    protected int maxGenerations;
    protected int[] layersSize;
    protected ErrorEvaluator errorEvaluator = new MeanSquareErrorEvaluator();
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
