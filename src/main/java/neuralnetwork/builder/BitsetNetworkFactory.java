package neuralnetwork.builder;

import utils.NumberUtils;

import java.util.BitSet;

public class BitsetNetworkFactory extends AbstractNetworkFactory {
    private final int maxUnnormalizedWeight;
    private int genesForOneNumber;
    private double maxParam;
    private double minParam;
    BitSet bitset;
    int currBitIndex = 0;

    public BitsetNetworkFactory(BitSet bitset, double minParam,double maxParam, int genesForOneNumber) {
        this.maxParam = maxParam;
        this.minParam = minParam;
        this.bitset = bitset;
        this.genesForOneNumber = genesForOneNumber;
        maxUnnormalizedWeight = (int) (Math.pow(2,genesForOneNumber) - 1);
    }

    protected double[] getNeuronParams(int numberOfParams) {
        double[] params = new double[numberOfParams];
        for (int i = 0; i < params.length; i++) {
            double number = NumberUtils.extractNumber(bitset.get(currBitIndex, currBitIndex + genesForOneNumber));
            params[i] = (maxParam - minParam) * number / maxUnnormalizedWeight + minParam;
            currBitIndex += genesForOneNumber;
        }
        return params;
    }
}
