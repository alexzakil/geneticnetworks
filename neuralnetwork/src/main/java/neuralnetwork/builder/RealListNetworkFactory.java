package neuralnetwork.builder;



import java.util.Arrays;

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
