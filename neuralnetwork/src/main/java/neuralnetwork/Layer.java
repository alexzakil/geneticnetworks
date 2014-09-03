package neuralnetwork;

/**
 * A layer of neurons in a neural network
 */
public class Layer {

    Neuron [] neurons;

    public Neuron[] getNeurons() {
        return neurons;
    }

    public void setNeurons(Neuron[] neurons) {
        this.neurons = neurons;
    }
}
