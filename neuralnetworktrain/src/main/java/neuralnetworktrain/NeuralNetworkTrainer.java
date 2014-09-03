package neuralnetworktrain;

import neuralnetwork.Network;
import neuralnetwork.transferfunction.TransferFunction;

/**
 * An algorithm to build a neural network for a specific classification problem.
 */
public interface NeuralNetworkTrainer {

    /**
     * Builds a new network
     * @param feature the training feature data (that the network should use in prediction)
     * @param observed the training observed data (that the network must learn to predict)
     * @param transferFunction the transfer function to use in the neurons
     * @return a network that was trained to predict the observed data using feature data
     */
    Network trainNetwork(double[][] feature, double[][] observed, TransferFunction transferFunction);

    /**
     * Continues training an existing network
     * @param feature the training feature data (that the network should use in prediction)
     * @param observed the training observed data (that the network must learn to predict)
     * @param network the network to train
     * @throws UnsupportedOperationException
     */
    void continueTrainingNetwork(double[][] feature,double[][] observed, Network network) throws UnsupportedOperationException;


}
