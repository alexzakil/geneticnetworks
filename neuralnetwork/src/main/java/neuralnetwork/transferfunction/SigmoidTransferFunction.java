package neuralnetwork.transferfunction;

public class SigmoidTransferFunction extends TransferFunction {
    @Override
    public double getNeuronOutput(double[] input, double[] params) {
        double sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i%input.length] * params[i];
        }

        return 1/(1+Math.pow(Math.E,-sum));
    }

    @Override
    public int getNumberOfParams(int numInputs) {
        return numInputs;
    }
}
