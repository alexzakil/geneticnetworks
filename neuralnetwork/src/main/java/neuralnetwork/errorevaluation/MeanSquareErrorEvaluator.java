package neuralnetwork.errorevaluation;

import neuralnetwork.Network;

/**
 * A standard method of measuring network error. For every output calculate the square of the difference between the expected
 * value and the actual value. Return the average of those squares.
 */
public class MeanSquareErrorEvaluator implements ErrorEvaluator {
    @Override
    public double evaluateError(Network network, double[][] feature, double[][] observed) {
        double errorSum = 0;
        for (int i = 0; i < feature.length; i++) {
            double currSum = 0;
            double[] featureVector = feature[i];
            double[] output = network.getOutput(featureVector);
            for (int j = 0; j < observed[i].length; j++) {
                double outValue = output[j];
                double obsValue = observed[i][j];

                currSum += Math.pow(outValue - obsValue, 2);
            }
            errorSum += currSum;
        }
        return errorSum / ( feature.length*feature[0].length);


    }
}
