package tests;

import neuralnetworktrain.backpropagation.BackpropagationTrainer;
import neuralnetwork.Network;
import neuralnetwork.errorevaluation.MeanSquareErrorEvaluator;
import org.apache.log4j.Logger;
import org.junit.Test;
import trainingtestsplit.TrainingTestSplitter;

import static org.fest.assertions.Assertions.assertThat;

public class BackpropagationTest {

    static final Logger logger = Logger.getLogger(BackpropagationTest.class);

    @Test
    public void testBackpropagation() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();

        BackpropagationTrainer backpropagationTrainer = new BackpropagationTrainer();
        backpropagationTrainer.setWantedError(0.01);
        backpropagationTrainer.setLayersSize(10,3);

        Network network = backpropagationTrainer.trainNetwork(splitter.getTrainingFeature(), splitter.getTrainingObserved());

        double error = new MeanSquareErrorEvaluator().evaluateError(network, splitter.getTestFeature(), splitter.getTestObserved());
        logger.info("Error: "+error);
        assertThat(error).isLessThan(0.05);

    }
}
