package neuralnetwork.transferfunction;

/**
 * This is a sigmoid transfer function with a fixed bias and two extra parameters:
 * a1 to modify the form of the function and a2 to modify its scale.
 * Each neuron input is multiplied by the weight of the connection,
 * and the sum is added to the bias and the result is passed through a sigmoid function: a2/(1+e^(-x/a1))
 *
 * This function enables better accuracy than BiasSigmoidTransferFunction
 */
public class ParametrizedBiasSigmoidTransferFunction implements TransferFunction {
    @Override
    public double getNeuronOutput(double[] input, double[] params) {
        double sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i%input.length] * params[i];
        }
        //bias:
        sum += 1*params[params.length-3];

        return params[params.length-2]/(1+Math.pow(Math.E,-sum/params[params.length-1]));
    }

    /**
     *
     * @param numInputs number of inputs in a neuron
     * @return the number of inputs + 3 : one parameter for bias, and two for a1 and a2.
     */
    @Override
    public int getNumberOfParams(int numInputs) {
        return numInputs + 3;
    }
}
