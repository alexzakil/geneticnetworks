package neuralnetwork.transferfunction;

public class BiasSigmoidTransferFunction extends TransferFunction {
    @Override
    public double getNeuronOutput(double[] input, double[] params) {
        double sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i%input.length] * params[i];
        }
        //bias:
        sum += 1*params[params.length-1];

        return 1/(1+Math.pow(Math.E,-sum));
    }

    @Override
    public int getNumberOfParams(int numInputs) {
        return numInputs + 1;
    }
}
