package neuralnetwork.errorevaluation;

import neuralnetwork.Network;

/**
 * Like MeanSquareErrorEvaluator, except the result as divided by 2, as defined in the article:
 * Evolutionary design of constructive multilayer feedforward neural network
 * by Ching-Han Chen, Tun-Kai Yao, Chia-Ming Kuo and Chen-Yuan Chen
 *
 * http://jvc.sagepub.com/content/early/2012/09/12/1077546312456726
 */
public class ChenErrorEvaluator implements ErrorEvaluator {
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
        return errorSum / ( feature.length*feature[0].length*2);


    }
}
