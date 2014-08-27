package neuralnetwork.transferfunction;

public class ParametrizedSigmoidTransferFunction extends TransferFunction {
    @Override
    public double getNeuronOutput(double[] input, double[] params) {
        double sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i%input.length] * params[i];
        }


        return params[params.length-2]/(1+Math.pow(Math.E,-sum/params[params.length-1]));
    }

    @Override
    public int getNumberOfParams(int numInputs) {
        return numInputs + 2;
    }
}
