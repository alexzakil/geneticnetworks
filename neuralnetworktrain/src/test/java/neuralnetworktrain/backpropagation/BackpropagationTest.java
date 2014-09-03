package neuralnetworktrain.backpropagation;

import neuralnetwork.Network;
import neuralnetwork.errorevaluation.MeanSquareErrorEvaluator;
import neuralnetwork.transferfunction.BiasSigmoidTransferFunction;
import org.apache.log4j.Logger;
import org.junit.Test;
import utils.testutils.TestUtils;
import utils.trainingtestsplit.TrainingTestSplitter;

import static org.fest.assertions.Assertions.assertThat;

public class BackpropagationTest {

    static final Logger logger = Logger.getLogger(BackpropagationTest.class);

    @Test
    public void testBackpropagationIris() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();

        testBackpropagation(splitter, new int[]{10, 3}, 0.001);
    }

    @Test
    public void testBackpropagationWine() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getWineData();

        testBackpropagation(splitter, new int[]{10, 3}, 0.001);
    }

    @Test
    public void testBackpropagationGlass() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getGlassData();

        testBackpropagation(splitter, new int[]{10, 7}, 0.025);
    }
    @Test
    public void testBackpropagationVehicles() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getVehicleData();

        testBackpropagation(splitter, new int[]{10, 4}, 0.01);
    }
    private void testBackpropagation(TrainingTestSplitter splitter, int[] layerSizes, double wantedError) {
        BackpropagationTrainer backpropagationTrainer = createTrainer(layerSizes, wantedError);

        Network network = backpropagationTrainer.trainNetwork(splitter.getTrainingFeature(), splitter.getTrainingObserved(), new BiasSigmoidTransferFunction());

        double error = new MeanSquareErrorEvaluator().evaluateError(network, splitter.getTestFeature(), splitter.getTestObserved());
        logger.info("Error: "+error);
        assertThat(error).isLessThan(0.05);
    }


    private BackpropagationTrainer createTrainer(int[] layerSizes, double wantedError) {
        BackpropagationTrainer backpropagationTrainer = new BackpropagationTrainer();
        backpropagationTrainer.setWantedError(wantedError);
        backpropagationTrainer.setLayersSize(layerSizes);
        return backpropagationTrainer;
    }
}
