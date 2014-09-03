package neuralnetwork.errorevaluation;

import neuralnetwork.Network;

/**
 * A function to evaluate the error of the neural network given a certain input (feature) and expected output (observed)
 */
public interface ErrorEvaluator {
    /**
     *
     * @param network The network that should be evaluated
     * @param feature an array of input arrays.
     * @param observed an array of expected output arrays
     * @return the aggregated error of the network. The lower the error the closer was the output of the network to the expected output
     */
    double evaluateError(Network network,double[][] feature, double[][] observed);
}
