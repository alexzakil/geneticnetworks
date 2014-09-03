package neuralnetwork;

import neuralnetwork.transferfunction.TransferFunction;

/**
 * Represents a feedforward neural network made of several layers of neurons.
 * Each neuron in one layer has a connection to every neuron in the next layer.
 * Input the the network receives is passed to every neuron in the first layer.
 * The output of a network is the output of the neurons in the last layer.
 */
public class Network {

    Layer[] layers;

    /**
     * The transfer function used in the network.
     */
    TransferFunction transferFunction;

    /**
     * Constructs an empty neural network.
     */
    public Network() {
    }

    /**
     * Constructs a neural network with the specified parameters, where each layer is represented as a two dimensional
     * array. In a layer, each neuron is represented as a one dimensional array of the neuron parameters.
     * @param params an array of layer parameters
     */
    public Network(double[][][] params) {
        layers = new Layer[params.length];
        for (int i = 0; i < params.length; i++) {
            double[][] layerParams = params[i];
            layers[i] = new Layer();
            Neuron[] neurons = new Neuron[layerParams.length];
            for (int j = 0; j < layerParams.length; j++) {
                double[] neuronParams = layerParams[j];
                Neuron neuron = new Neuron();
                neuron.setParams(neuronParams);
                neurons[j] = neuron;
            }
            layers[i].setNeurons(neurons);

        }
    }

    /**
     * Constructs a neural network with the specified layers
     * @param layers layers to use in the neural network
     * @param transferFunction the transfer function
     */
    public Network(Layer[] layers, TransferFunction transferFunction){
        this.layers = layers;
        this.transferFunction = transferFunction;
    }

    /**
     * This method is used to activate the neural network with a particular input to get the output.
     * @param input the network input. It will be passed to every neuron in the first layer.
     * @return the output of the network. It's size will be equal to the number of neurons in the last layer.
     */
    public double[] getOutput(double [] input){
        if(transferFunction == null){
            throw new RuntimeException("Must set transfer function!");
        }

        double[] output = null;
        for (Layer layer : layers) {
            Neuron[] currentLayerNeurons = layer.getNeurons();
            output = new double[currentLayerNeurons.length];
            for (int i = 0; i < currentLayerNeurons.length; i++) {
                Neuron currentNeuron = currentLayerNeurons[i];
                double neuronOutput = transferFunction.getNeuronOutput(input, currentNeuron.getParams());
                output[i] = neuronOutput;
                currentNeuron.setLastInput(input);
                currentNeuron.setLastOutput(neuronOutput);
            }
            input = output;

        }
        return output;

    }

    public Layer[] getLayers() {
        return layers;
    }

    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }

    public TransferFunction getTransferFunction() {
        return transferFunction;
    }

    /**
     * Set the transfer function to be used in the network neurons.
     * @param transferFunction the transfer function.
     */
    public void setTransferFunction(TransferFunction transferFunction) {
        this.transferFunction = transferFunction;
    }
}
