package tests.genetic;

import geneticalgorithm.crossover.SinglePointCross;
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
import neuralnetwork.transferfunction.ParametrizedSigmoidTransferFunction;
import neuralnetworktrain.NeuralNetworkCalibrator;
import neuralnetworktrain.backpropagation.BackpropagationTrainer;
import neuralnetworktrain.geneticalgorithm.RealListGeneticAlgorithmNetworkTrainer;
import org.junit.Test;
import tests.TestUtils;
import trainingtestsplit.TrainingTestSplitter;

import static org.fest.assertions.Assertions.assertThat;

public class RealListNetworkTrainAlgorithmTest {


    @Test
    public void testAdditionAlgorithm() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();


        GenerationManager<RealListIndividual> generationManager = new AdditionGenerationManager<>(new RouletteSelector(), new SinglePointCross(), new GaussianMutator(0.01),0.9);
        int[] layersSize = {10,3};
        RealListGeneticAlgorithmNetworkTrainer trainer = new RealListGeneticAlgorithmNetworkTrainer(
                5000, 30, 0.0001,generationManager, new MeanSquareErrorEvaluator(), layersSize,new ParametrizedBiasSigmoidTransferFunction());

        Network network = trainer.trainNetwork(splitter.getFullFeature(), splitter.getFullObserved());

        double error = new MeanSquareErrorEvaluator().evaluateError(network, splitter.getTestFeature(), splitter.getTestObserved());
        assertThat(error).isLessThan(0.08);
    }

    @Test
    public void testReplacementAlgorithm() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();


        GenerationManager<RealListIndividual> generationManager = new ReplacementGenerationManager<>(new RouletteSelector(), new SinglePointCross(), new GaussianMutator(0.01),0.9,4);
        int[] layersSize = {4,3};
        RealListGeneticAlgorithmNetworkTrainer trainer = new RealListGeneticAlgorithmNetworkTrainer(
                5000, 30, 0.0001,generationManager, new MeanSquareErrorEvaluator(), layersSize,new ParametrizedBiasSigmoidTransferFunction());

        Network network = trainer.trainNetwork(splitter.getTrainingFeature(), splitter.getTrainingObserved());

        double error = new MeanSquareErrorEvaluator().evaluateError(network, splitter.getTestFeature(), splitter.getTestObserved());
        assertThat(error).isLessThan(0.08);
    }

    @Test
    public void callibrate() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();


        GenerationManager<RealListIndividual> generationManager = new AdditionGenerationManager<>(new RouletteSelector(), new SinglePointCross(), new GaussianMutator(0.01),0.9);
        int[] layersSize = {4,3};
        RealListGeneticAlgorithmNetworkTrainer trainer = new RealListGeneticAlgorithmNetworkTrainer(
                5000, 30, 0.0001,generationManager, new MeanSquareErrorEvaluator(), layersSize,new ParametrizedBiasSigmoidTransferFunction());
        NeuralNetworkCalibrator calibrator = new NeuralNetworkCalibrator(trainer);

        calibrator.calibrateLayerSize(2000,25,splitter.getFullFeature(),splitter.getFullObserved(),2,5,2,15);
    }

    @Test
    public void testGeneticThenBackpropagation() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();
        GenerationManager<RealListIndividual> generationManager = new AdditionGenerationManager<>(new RouletteSelector(), new SinglePointCross(), new GaussianMutator(0.01),0.9);
        int[] layersSize = {10,3};
        RealListGeneticAlgorithmNetworkTrainer trainer = new RealListGeneticAlgorithmNetworkTrainer(
                5000, 30, 0.01,generationManager, new MeanSquareErrorEvaluator(), layersSize,new ParametrizedSigmoidTransferFunction());

        Network network = trainer.trainNetwork(splitter.getFullFeature(), splitter.getFullObserved());
        BackpropagationTrainer backpropagationTrainer = new BackpropagationTrainer();
        backpropagationTrainer.setWantedError(0.0001);
        backpropagationTrainer.setLayersSize(layersSize);
        backpropagationTrainer.continueTrainingNetwork(splitter.getFullFeature(), splitter.getFullObserved(),network);

    }

}
