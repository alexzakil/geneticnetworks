package neuralnetwork.builder;


import utils.NumberUtils;

import java.util.BitSet;

/**
 * Creates a network from one bitset. Every N bits are converted to one number. The larger this N is the higher precision
 * is possible
 */
public class BitsetNetworkFactory extends AbstractNetworkFactory {
    private final int maxUnnormalizedWeight;
    private int genesForOneNumber;
    private double maxParam;
    private double minParam;
    BitSet bitset;
    int currBitIndex = 0;

    /**
     *
     * @param bitset the bitset to convert to a neural network
     * @param minParam minimum value of a parameter
     * @param maxParam maximum value of a parameter
     * @param genesForOneNumber number of genes to represent one number
     */
    public BitsetNetworkFactory(BitSet bitset, double minParam,double maxParam, int genesForOneNumber) {
        this.maxParam = maxParam;
        this.minParam = minParam;
        this.bitset = bitset;
        this.genesForOneNumber = genesForOneNumber;
        maxUnnormalizedWeight = (int) (Math.pow(2,genesForOneNumber) - 1);
    }

    @Override
    protected void beforeNetworkCreation() {
        currBitIndex = 0;
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
