package neuralnetwork;

/**
 * A single neuron
 */
public class Neuron {
    double [] params;
    double[] lastInput;
    double lastOutput;

    public double[] getParams() {
        return params;
    }

    public void setParams(double[] params) {
        this.params = params;
    }

    public double[] getLastInput() {
        return lastInput;
    }

    public void setLastInput(double[] lastInput) {
        this.lastInput = lastInput;
    }

    public double getLastOutput() {
        return lastOutput;
    }

    public void setLastOutput(double lastOutput) {
        this.lastOutput = lastOutput;
    }
}
