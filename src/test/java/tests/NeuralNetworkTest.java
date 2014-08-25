package tests;

import neuralnetwork.Network;
import neuralnetwork.errorevaluation.MeanSquareErrorEvaluator;
import neuralnetwork.transferfunction.SigmoidTransferFunction;
import org.fest.assertions.Delta;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class NeuralNetworkTest {
    @Test
    public void testNetwork() throws Exception{
        Network network = new Network(new double[][][]{
                {{0.5,0},{1,0}},
                {{0.5,1}}
        });
        network.setTransferFunction(new SigmoidTransferFunction());
        double[] output = network.getOutput(new double[]{0, 0});
        assertThat(output).hasSize(1);
        double error = new MeanSquareErrorEvaluator().evaluateError(network, new double[][]{{0, 0}, {0, 1}}, new double[][]{{1}, {0}});
        assertThat(error).isEqualTo(0.141, Delta.delta(0.1));
    }

}
