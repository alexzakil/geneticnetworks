package neuralnetwork.builder;



import java.util.Arrays;

/**
 * A factory that creates neuron networks from a list of real numbers.
 */
public class RealListNetworkFactory extends AbstractNetworkFactory {

    private double[] allParams;
    int paramIndex = 0;

    /**
     *
     * @param params the number to use for network creation
     */
    public RealListNetworkFactory(double[] params) {
        this.allParams = params;
     }

    @Override
    protected void beforeNetworkCreation() {
        paramIndex = 0;
    }

    protected double[] getNeuronParams(int numberOfParams) {
        double[] params = Arrays.copyOfRange(allParams,paramIndex,paramIndex+numberOfParams);
        paramIndex+=numberOfParams;
        return params;
    }


}
