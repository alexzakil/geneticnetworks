package neuralnetwork.builder;

import neuralnetwork.Network;
import neuralnetwork.transferfunction.TransferFunction;

/**
 * An interface for classes that can create neural networks
 */
public interface NetworkFactory {
    /**
     * Creates a new neural network
     * @param numberOfInputs number of inputs the network should be able to receive
     * @param transferFunction the transfer function the network should use
     * @param layersSize an array of the size of te layers that would be in the neural network
     * @return a new neural network
     */
    Network createNetwork(int numberOfInputs,TransferFunction transferFunction,int... layersSize);
}
