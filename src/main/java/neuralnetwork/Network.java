package neuralnetwork;

import neuralnetwork.transferfunction.TransferFunction;

public class Network {

    Layer[] layers;
    TransferFunction transferFunction;

    public Network() {
    }

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

    public void setTransferFunction(TransferFunction transferFunction) {
        this.transferFunction = transferFunction;
    }
}
