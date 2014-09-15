package neuralnetworktrain.backpropagation;

import neuralnetwork.Layer;
import neuralnetwork.Network;
import neuralnetwork.Neuron;
import neuralnetwork.builder.RandomNetworkFactory;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.BiasSigmoidTransferFunction;
import neuralnetwork.transferfunction.ParametrizedBiasSigmoidTransferFunction;
import neuralnetwork.transferfunction.SigmoidTransferFunction;
import neuralnetwork.transferfunction.TransferFunction;
import neuralnetworktrain.AbstractNeuralNetworkTrainer;
import org.apache.log4j.Logger;
import utils.TimeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * An algorithm to build a neural network using backpropagation. Requires that the network transfer funciton be
 * SigmoidTransferFunction or BiasSigmoidTransferFunction.
 * For more information: http://en.wikipedia.org/wiki/Backpropagation
 *
 */
public class BackpropagationTrainer extends AbstractNeuralNetworkTrainer {

    static final Logger logger = Logger.getLogger(BackpropagationTrainer.class);

    /**
     * The learning rate of the algorithm. The greater the ratio, the faster the neuron trains; the lower the ratio, the more accurate the training is.
     */
    double learningRate = 0.1;
    /**
     * The momentum of the algorithm - how much previous weight changes affect current weight change.
     * For more information: http://en.wikiversity.org/wiki/Learning_and_neural_networks#Momentum_learning
     */
    double momentum = 0.0;

    Map<Neuron, Double[]> oldChanges;
    private boolean withBias;

    public BackpropagationTrainer() {
        setMaxGenerations(Integer.MAX_VALUE);
    }

    /**
     *
     * @param learningRate  The learning rate of the algorithm. The greater the ratio, the faster the neuron trains; the lower the ratio, the more accurate the training is.
     * @param momentum The momentum of the algorithm - how much previous weight changes affect current weight change.
     * For more information: http://en.wikiversity.org/wiki/Learning_and_neural_networks#Momentum_learning
     * @param wantedError
     * @param errorEvaluator
     * @param layersSize
     */
    public BackpropagationTrainer(double learningRate, double momentum, double wantedError, ErrorEvaluator errorEvaluator, int[] layersSize) {
        this.learningRate = learningRate;
        this.momentum = momentum;
        this.wantedError = wantedError;
        this.errorEvaluator = errorEvaluator;

        this.layersSize = layersSize;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public double getMomentum() {
        return momentum;
    }

    /**
     *
     * @param momentum The momentum of the algorithm - how much previous weight changes affect current weight change.
     * For more information: http://en.wikiversity.org/wiki/Learning_and_neural_networks#Momentum_learning
     */
    public void setMomentum(double momentum) {
        this.momentum = momentum;
    }

    @Override
    public Network trainNetwork(double[][] feature, double[][] observed, TransferFunction transferFunction) {
        Network network = new RandomNetworkFactory(-1, 1).createNetwork(feature[0].length, transferFunction, layersSize);
        continueTrainingNetwork(feature, observed, network);

        return network;
    }


    @Override
    public void continueTrainingNetwork(double[][] feature, double[][] observed, Network network) {

        if (!(network.getTransferFunction() instanceof SigmoidTransferFunction || network.getTransferFunction() instanceof BiasSigmoidTransferFunction)) {
            throw new UnsupportedOperationException("transfer function must be either SigmoidTransferFunction or BiasSigmoidTransferFunction");
        }

        long startTime = System.currentTimeMillis();

        withBias = network.getTransferFunction() instanceof BiasSigmoidTransferFunction || network.getTransferFunction() instanceof ParametrizedBiasSigmoidTransferFunction;
        oldChanges = new HashMap<>();

        double error;
        int generation = 0;
        do {
            learnStep(feature, observed, network);
            error = errorEvaluator.evaluateError(network, feature, observed);
            generation++;
            if (generation % 100 == 0) {
                logger.debug("Generation: " + generation + " Fitness " + (1 - error));

            }

        } while (error > wantedError && generation < maxGenerations);
        logger.info("Finished training network, time to train: " + TimeUtils.formatTimePeriod(System.currentTimeMillis() - startTime));
    }

    private void learnStep(double[][] feature, double[][] observed, Network network) {
        Map<Neuron, Double> neuronErrors = new HashMap<>();

        for (int featureIndex = 0; featureIndex < feature.length; featureIndex++) {
            double[] currFeature = feature[featureIndex];
            double[] currObserved = observed[featureIndex];
            network.getOutput(currFeature);

            Layer outputLayer = network.getLayers()[network.getLayers().length - 1];

            for (int neuronIndex = 0; neuronIndex < outputLayer.getNeurons().length; neuronIndex++) {
                Neuron neuron = outputLayer.getNeurons()[neuronIndex];
                double[] neuronInput = neuron.getLastInput();


                double neuronOutput = neuron.getLastOutput();
                double neuronError = neuronIndex >= currObserved.length ? 0 : currObserved[neuronIndex] - neuronOutput;

                neuronError = neuronError * getOutputDerivative(neuron, network.getTransferFunction());
                neuronErrors.put(neuron, neuronError);

                applyChanges(neuron, neuronInput, neuronError);
            }

            Layer nextLayer = outputLayer;
            if (network.getLayers().length > 1) {


                Layer hiddenLayer = network.getLayers()[network.getLayers().length - 2];

                for (int neuronIndex = 0; neuronIndex < hiddenLayer.getNeurons().length; neuronIndex++) {
                    Neuron neuron = hiddenLayer.getNeurons()[neuronIndex];
                    double[] neuronInput = neuron.getLastInput();

                    double neuronError = 0;
                    for (int i = 0; i < nextLayer.getNeurons().length; i++) {
                        Neuron nextLayerNeuron = nextLayer.getNeurons()[i];
                        neuronError += nextLayerNeuron.getParams()[neuronIndex] * neuronErrors.get(nextLayerNeuron);
                    }

                    neuronError *= getOutputDerivative(neuron, network.getTransferFunction());

                    neuronErrors.put(neuron, neuronError);


                    applyChanges(neuron, neuronInput, neuronError);
                }
            }

        }

    }

    private void applyChanges(Neuron neuron, double[] neuronInput, double neuronError) {
        Double[] weightChange = new Double[neuronInput.length + (withBias ? 1 : 0)];
        Double[] neuronOldChanges = oldChanges.get(neuron);
        for (int i = 0; i < neuronInput.length; i++) {
            weightChange[i] = learningRate * neuronError * neuronInput[i] + (neuronOldChanges == null ? 0 : neuronOldChanges[i] * momentum);
            neuron.getParams()[i] += weightChange[i];
        }
        if (withBias) {
            int biasIndex = neuronInput.length;
            weightChange[biasIndex] = learningRate * neuronError * 1 + (neuronOldChanges == null ? 0 : neuronOldChanges[biasIndex] * momentum);
            neuron.getParams()[biasIndex] += weightChange[biasIndex];
        }
        oldChanges.put(neuron, weightChange);
    }

    private double getOutputDerivative(Neuron neuron, TransferFunction transferFunction) {
        if (transferFunction instanceof ParametrizedBiasSigmoidTransferFunction) {
            return neuron.getLastOutput() * (neuron.getParams()[neuron.getParams().length - 2] - neuron.getLastOutput()) / (neuron.getParams()[neuron.getParams().length - 1] * neuron.getParams()[neuron.getParams().length - 2]);
        }
        return neuron.getLastOutput() * (1 - neuron.getLastOutput());
    }
}
