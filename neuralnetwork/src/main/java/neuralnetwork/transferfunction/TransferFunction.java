package neuralnetwork.transferfunction;

public abstract class TransferFunction {

    public abstract double getNeuronOutput(double[] input, double[] params);

    public abstract int getNumberOfParams(int numInputs);
}
