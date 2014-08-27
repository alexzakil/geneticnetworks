package neuralnetwork.builder;

import neuralnetwork.Network;
import neuralnetwork.transferfunction.TransferFunction;

public abstract class AbstractNetworkFactory implements NetworkFactory{

    @Override
    public Network createNetwork(int numberOfInputs,TransferFunction transferFunction,int... layersSize) {
        double[][][] paramArray = new double[layersSize.length][][];
        paramArray[0] = createLayer(numberOfInputs,layersSize[0], transferFunction);
        for(int layerIndex = 1; layerIndex<layersSize.length; layerIndex++) {
            paramArray[layerIndex] = createLayer(paramArray[layerIndex-1].length,layersSize[layerIndex],transferFunction);
        }

        Network network = new Network(paramArray);
        network.setTransferFunction(transferFunction);
        return network;
    }

    private double[][] createLayer(int numberOfInputs, int numberOfNeurons, TransferFunction transferFunction) {
        double[][] layerArray = new double[numberOfNeurons][];
        for (int i = 0; i < layerArray.length; i++) {
            layerArray[i] = getNeuronParams(transferFunction.getNumberOfParams(numberOfInputs));
        }
        return layerArray;
    }

    protected abstract double[] getNeuronParams(int numberOfParams);


}
