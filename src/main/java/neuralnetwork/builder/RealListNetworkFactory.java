package neuralnetwork.builder;

import utils.NumberUtils;

import java.util.Arrays;
import java.util.BitSet;

public class RealListNetworkFactory extends AbstractNetworkFactory {

    private double[] allParams;
    int paramIndex = 0;

    public RealListNetworkFactory(double[] params) {
        this.allParams = params;
     }

    protected double[] getNeuronParams(int numberOfParams) {
        double[] params = Arrays.copyOfRange(allParams,paramIndex,paramIndex+numberOfParams);
        paramIndex+=numberOfParams;
        return params;
    }
}
