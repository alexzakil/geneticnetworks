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

public class BackpropagationTrainer extends AbstractNeuralNetworkTrainer {

    static final Logger logger = Logger.getLogger(BackpropagationTrainer.class);

    double learningRate = 0.1;
    double momentum = 0.0;
    private double wantedError = 0.001;

    Map<Neuron, Double[]> oldChanges;
    private boolean withBias;

    public BackpropagationTrainer() {
        transferFunction = new BiasSigmoidTransferFunction();
        setMaxGenerations(Integer.MAX_VALUE);
    }

    public BackpropagationTrainer(double learningRate, double momentum, double wantedError, ErrorEvaluator errorEvaluator, TransferFunction transferFunction, int[] layersSize) {
        this.learningRate = learningRate;
        this.momentum = momentum;
        this.wantedError = wantedError;
        this.errorEvaluator = errorEvaluator;
        setTransferFunction(transferFunction);
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

    public void setMomentum(double momentum) {
        this.momentum = momentum;
    }

    public void setTransferFunction(TransferFunction transferFunction) {
        if (!(transferFunction instanceof SigmoidTransferFunction || transferFunction instanceof BiasSigmoidTransferFunction)) {
            throw new UnsupportedOperationException("transfer function must be either SigmoidTransferFunction or BiasSigmoidTransferFunction");
        }
        super.setTransferFunction(transferFunction);
    }

    @Override
    public Network trainNetwork(double[][] feature, double[][] observed) {
        Network network = new RandomNetworkFactory(-1, 1).createNetwork(feature[0].length, transferFunction, layersSize);
        continueTrainingNetwork(feature, observed, network);

        return network;
    }


    @Override
    public void continueTrainingNetwork(double[][] feature, double[][] observed, Network network) {
        long startTime = System.currentTimeMillis();

        withBias = transferFunction instanceof BiasSigmoidTransferFunction;
        oldChanges = new HashMap<>();

        double error;
        int generation = 0;
        do {
            learnStep(feature, observed, network);
            error = errorEvaluator.evaluateError(network, feature, observed);
            generation++;
            if (generation % 100 == 0) {
                logger.info("Generation: " + generation + " Fitness " + (1 - error));

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

                neuronError = neuronError * getOutputDerivative(neuron);
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

                    neuronError *= getOutputDerivative(neuron);

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

    private double getOutputDerivative(Neuron neuron) {
        if (transferFunction instanceof ParametrizedBiasSigmoidTransferFunction) {
            return neuron.getLastOutput() * (neuron.getParams()[neuron.getParams().length - 2] - neuron.getLastOutput()) / (neuron.getParams()[neuron.getParams().length - 1] * neuron.getParams()[neuron.getParams().length - 2]);
        }
        return neuron.getLastOutput() * (1 - neuron.getLastOutput());
    }
}