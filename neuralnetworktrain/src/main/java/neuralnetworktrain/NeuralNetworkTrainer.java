package neuralnetworktrain;

import neuralnetwork.Network;
import neuralnetwork.transferfunction.TransferFunction;

public interface NeuralNetworkTrainer {

    Network trainNetwork(double[][] feature, double[][] observed, TransferFunction transferFunction);

    void continueTrainingNetwork(double[][] feature,double[][] observed, Network network) throws UnsupportedOperationException;


}
