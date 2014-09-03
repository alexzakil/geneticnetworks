package neuralnetwork.transferfunction;

/**
 * This is a sigmoid transfer function with a fixed bias. Each neuron input is multiplied by the weight of the connection,
 * and the sum is added to the bias and the result is passed through a sigmoid function: 1/(1+e^(-x))
 */
public class BiasSigmoidTransferFunction implements TransferFunction {
    @Override
    public double getNeuronOutput(double[] input, double[] params) {
        double sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i%input.length] * params[i];
        }
        //bias:
        sum += params[params.length-1];

        return 1/(1+Math.pow(Math.E,-sum));
    }

    @Override
    /**
     *
     * @param numInputs number of inputs in a neuron
     * @return the number of inputs + 1. All the neuron needs is the weights and one parameter for the bias.
     */
    public int getNumberOfParams(int numInputs) {
        return numInputs + 1;
    }
}
