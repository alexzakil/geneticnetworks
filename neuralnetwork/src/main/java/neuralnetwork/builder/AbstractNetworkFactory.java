package neuralnetwork.builder;

import neuralnetwork.Network;
import neuralnetwork.transferfunction.TransferFunction;

/**
 * An abstract network factory that creates a new network. The implementation of this class should be able to create new
 * neurons with a required number of parameters.
 */
public abstract class AbstractNetworkFactory implements NetworkFactory{

    @Override
    public Network createNetwork(int numberOfInputs,TransferFunction transferFunction,int... layersSize) {
        beforeNetworkCreation();
        double[][][] paramArray = new double[layersSize.length][][];
        paramArray[0] = createLayer(numberOfInputs,layersSize[0], transferFunction);
        for(int layerIndex = 1; layerIndex<layersSize.length; layerIndex++) {
            paramArray[layerIndex] = createLayer(paramArray[layerIndex-1].length,layersSize[layerIndex],transferFunction);
        }

        Network network = new Network(paramArray);
        network.setTransferFunction(transferFunction);
        return network;
    }

    /**
     * Override this if something should be done before network is created.
     */
    protected abstract void beforeNetworkCreation();

    private double[][] createLayer(int numberOfInputs, int numberOfNeurons, TransferFunction transferFunction) {
        double[][] layerArray = new double[numberOfNeurons][];
        for (int i = 0; i < layerArray.length; i++) {
            layerArray[i] = getNeuronParams(transferFunction.getNumberOfParams(numberOfInputs));
        }
        return layerArray;
    }

    /**
     * Returns parameters for a single neuron
     * @param numberOfParams number of parameters needed.
     * @return
     */
    protected abstract double[] getNeuronParams(int numberOfParams);


}
