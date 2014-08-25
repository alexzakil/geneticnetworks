package neuralnetwork.builder;

import neuralnetwork.Network;
import neuralnetwork.transferfunction.TransferFunction;

public interface NetworkFactory {
    Network createNetwork(int numberOfInputs,TransferFunction transferFunction,int... layersSize);
}
