package neuralnetwork.transferfunction;

/**
 * A function that calculates an output of a neuron given its parameters and its input.
 * Each transfer function may have a different number of required parameters.
 */
public interface TransferFunction {

    /**
     * Calculates an output of a neuron given its parameters and its input.
     * @param input the input of a neuron
     * @param params the parameters of a neuron
     * @return the output of a neuron
     */
    public abstract double getNeuronOutput(double[] input, double[] params);

    /**
     * Calculates how many parameters does a neuron need for this transfer function to work
     * @param numInputs number of inputs in a neuron
     * @return number of parameters a neuron should have
     */
    public abstract int getNumberOfParams(int numInputs);
}
