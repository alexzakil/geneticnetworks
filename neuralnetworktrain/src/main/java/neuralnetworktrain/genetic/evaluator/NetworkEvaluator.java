package neuralnetworktrain.genetic.evaluator;

import geneticalgorithm.evaluation.Evaluator;
import geneticalgorithm.individual.Individual;
import neuralnetwork.Network;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;

public abstract class NetworkEvaluator<T extends Individual> implements Evaluator<T> {
    TransferFunction transferFunction;
    double[][] feature;
    double[][] observed;
    int[] layersSize;
    ErrorEvaluator errorEvaluator;

    public NetworkEvaluator() {
    }

    public NetworkEvaluator(double[][] feature,double[][] observed, ErrorEvaluator errorEvaluator, TransferFunction transferFunction,int... layersSize) {
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
//        if(feature[0].length > layersSize[0]) {
//            throw new RuntimeException("Provided feature with length "+feature[0].length+" while there are "+layersSize[0]+" neurons in first layer. Must be less or equal");
//        }
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
    public double evaluate(T individual) {

        Network networkFromIndividual = createNetworkFromIndividual(individual);
        return 1- errorEvaluator.evaluateError(networkFromIndividual,feature,observed);
    }

    public abstract Network createNetworkFromIndividual(T individual);
}
