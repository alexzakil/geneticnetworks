package neuralnetwork.transferfunction;

/**
 * This is the standard sigmoid transfer function. Each neuron input is multiplied by the weight of the connection,
 * and the sum is passed through a sigmoid function: 1/(1+e^(-x))
 */
public class SigmoidTransferFunction implements TransferFunction {
    @Override
    public double getNeuronOutput(double[] input, double[] params) {
        double sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i%input.length] * params[i];
        }

        return 1/(1+Math.pow(Math.E,-sum));
    }


    /**
     *
     * @param numInputs number of inputs in a neuron
     * @return the number of inputs. All the neuron needs is the weights.
     */
    @Override
    public int getNumberOfParams(int numInputs) {
        return numInputs;
    }
}
