package neuralnetworktrain.genetic;

import geneticalgorithm.crossover.RealListOnePointCut;
import geneticalgorithm.generation.AdditionGenerationManager;
import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.generation.ReplacementGenerationManager;
import geneticalgorithm.individual.RealListIndividual;
import geneticalgorithm.mutation.GaussianMutator;
import geneticalgorithm.selection.RouletteSelector;
import neuralnetwork.Network;
import neuralnetwork.errorevaluation.MeanSquareErrorEvaluator;
import neuralnetwork.transferfunction.BiasSigmoidTransferFunction;
import neuralnetwork.transferfunction.ParametrizedBiasSigmoidTransferFunction;
import neuralnetworktrain.NeuralNetworkCalibrator;
import neuralnetworktrain.backpropagation.BackpropagationTrainer;
import org.junit.Ignore;
import org.junit.Test;
import utils.testutils.TestUtils;
import utils.trainingtestsplit.TrainingTestSplitter;

import static org.fest.assertions.Assertions.assertThat;

public class RealListNetworkTrainAlgorithmTest {


    @Test
         public void testAdditionAlgorithm() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();


        testAddition(splitter, 0.01);
    }

    @Test
         public void testAdditionAlgorithmWine() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getWineData();


        testAddition(splitter, 0.01);
    }

    @Test
    public void testAdditionAlgorithmGlass() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getGlassData();


        testAddition(splitter, 0.06);
    }

    @Test
    public void testAdditionAlgorithmVehicles() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getVehicleData();


        testAddition(splitter, 0.05);
    }

    private void testAddition(TrainingTestSplitter splitter, double wantedError) {
        GenerationManager<RealListIndividual> generationManager = new AdditionGenerationManager<>(new RouletteSelector(), new RealListOnePointCut(), new GaussianMutator(0.01),0.9);
        int[] layersSize = {10,splitter.getFullObserved()[0].length};
        RealListGeneticAlgorithmNetworkTrainer trainer = new RealListGeneticAlgorithmNetworkTrainer(
                5000, 30, wantedError,generationManager, new MeanSquareErrorEvaluator(), layersSize);

        Network network = trainer.trainNetwork(splitter.getFullFeature(), splitter.getFullObserved(), new ParametrizedBiasSigmoidTransferFunction());

        double error = new MeanSquareErrorEvaluator().evaluateError(network, splitter.getTestFeature(), splitter.getTestObserved());
        assertThat(error).isLessThan(0.08);
    }

    @Test
    public void testReplacementAlgorithm() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();


        GenerationManager<RealListIndividual> generationManager = new ReplacementGenerationManager<>(new RouletteSelector(), new RealListOnePointCut(), new GaussianMutator(0.01),0.9,4);
        int[] layersSize = {4,3};
        RealListGeneticAlgorithmNetworkTrainer trainer = new RealListGeneticAlgorithmNetworkTrainer(
                5000, 30, 0.01,generationManager, new MeanSquareErrorEvaluator(), layersSize);

        Network network = trainer.trainNetwork(splitter.getTrainingFeature(), splitter.getTrainingObserved(), new ParametrizedBiasSigmoidTransferFunction());

        double error = new MeanSquareErrorEvaluator().evaluateError(network, splitter.getTestFeature(), splitter.getTestObserved());
        assertThat(error).isLessThan(0.08);
    }

    @Test
    @Ignore
    public void callibrate() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();


        GenerationManager<RealListIndividual> generationManager = new AdditionGenerationManager<>(new RouletteSelector(), new RealListOnePointCut(), new GaussianMutator(0.01),0.9);
        int[] layersSize = {4,3};
        RealListGeneticAlgorithmNetworkTrainer trainer = new RealListGeneticAlgorithmNetworkTrainer(
                5000, 30, 0.0001,generationManager, new MeanSquareErrorEvaluator(), layersSize);
        NeuralNetworkCalibrator calibrator = new NeuralNetworkCalibrator(trainer);

        calibrator.calibrateLayerSize(2000,25,splitter.getFullFeature(),splitter.getFullObserved(),2,5,2,15);
    }

    @Test
    public void testGeneticThenBackpropagation() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();
        GenerationManager<RealListIndividual> generationManager = new AdditionGenerationManager<>(new RouletteSelector(), new RealListOnePointCut(), new GaussianMutator(0.01),0.9);
        int[] layersSize = {10,3};
        RealListGeneticAlgorithmNetworkTrainer trainer = new RealListGeneticAlgorithmNetworkTrainer(
                5000, 30, 0.1,generationManager, new MeanSquareErrorEvaluator(), layersSize);

        Network network = trainer.trainNetwork(splitter.getFullFeature(), splitter.getFullObserved(), new ParametrizedBiasSigmoidTransferFunction());
        BackpropagationTrainer backpropagationTrainer = new BackpropagationTrainer();
        backpropagationTrainer.setWantedError(0.01);
        backpropagationTrainer.setLayersSize(layersSize);
        backpropagationTrainer.continueTrainingNetwork(splitter.getFullFeature(), splitter.getFullObserved(),network);

    }

}
