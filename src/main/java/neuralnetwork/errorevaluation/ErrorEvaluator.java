package neuralnetwork.errorevaluation;

import neuralnetwork.Network;

public interface ErrorEvaluator {
    double evaluateError(Network network,double[][] feature, double[][] observed);
}
