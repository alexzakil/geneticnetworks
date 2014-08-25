package neuralnetworktrain;

import neuralnetwork.Network;

public interface NeuralNetworkTrainer {

    Network trainNetwork(double[][] feature,double[][] observed);

    void continueTrainingNetwork(double[][] feature,double[][] observed, Network network) throws UnsupportedOperationException;


}
