package neuralnetwork.builder;

public class RandomNetworkFactory extends AbstractNetworkFactory {
    private double maxParam;
    private double minParam;

    public RandomNetworkFactory(double minParam,double maxParam) {
        this.maxParam = maxParam;
        this.minParam = minParam;
    }

    protected double[] getNeuronParams(int numberOfParams) {
        double[] params = new double[numberOfParams];
        for (int i = 0; i < params.length; i++) {
            params[i] = Math.random()*(maxParam - minParam) + minParam;;

        }
        return params;
    }
}
