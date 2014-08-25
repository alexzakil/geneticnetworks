package tests.genetic;

import geneticalgorithm.crossover.OnePointCut;
import geneticalgorithm.generation.AdditionGenerationManager;
import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.generation.ReplacementGenerationManager;
import geneticalgorithm.individual.BitSetIndividual;
import geneticalgorithm.mutation.BitSetMutator;
import geneticalgorithm.selection.RouletteSelector;
import neuralnetwork.errorevaluation.ChenErrorEvaluator;
import neuralnetwork.transferfunction.ParametrizedBiasSigmoidTransferFunction;
import neuralnetworktrain.NeuralNetworkCalibrator;
import org.apache.log4j.Logger;
import tests.TestUtils;
import neuralnetwork.Network;
import neuralnetworktrain.geneticalgorithm.BitsetGeneticAlgorithmNetworkTrainer;
import org.junit.Test;
import trainingtestsplit.TrainingTestSplitter;

import static org.fest.assertions.Assertions.assertThat;

public class NetworkTrainAlgorithmTest {
    static final Logger logger = Logger.getLogger(NetworkTrainAlgorithmTest.class);

    @Test
    public void testAlgorithm() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();


        GenerationManager<BitSetIndividual> generationManager = new ReplacementGenerationManager<>(new RouletteSelector(), new OnePointCut(), new BitSetMutator(0.01),0.6,4);
        int[] layersSize = {10, 3};
        BitsetGeneticAlgorithmNetworkTrainer trainer = new BitsetGeneticAlgorithmNetworkTrainer(
                        50000, 50, 0.01,generationManager, new ChenErrorEvaluator(), layersSize,new ParametrizedBiasSigmoidTransferFunction(), 4);

        Network network = trainer.trainNetwork(splitter.getTrainingFeature(), splitter.getTrainingObserved());

        double error = new ChenErrorEvaluator().evaluateError(network, splitter.getTestFeature(), splitter.getTestObserved());
        assertThat(error).isLessThan(0.05);
    }

    @Test
    public void testAdditionAlgorithm() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();


        GenerationManager<BitSetIndividual> generationManager = new AdditionGenerationManager<>(new RouletteSelector(), new OnePointCut(), new BitSetMutator(0.01),0.6);
        int[] layersSize = {4,3};
        BitsetGeneticAlgorithmNetworkTrainer trainer = new BitsetGeneticAlgorithmNetworkTrainer(
                5000, 50, 0.0001,generationManager, new ChenErrorEvaluator(), layersSize,new ParametrizedBiasSigmoidTransferFunction(), 4);

        Network network = trainer.trainNetwork(splitter.getTrainingFeature(), splitter.getTrainingObserved());

        double error = new ChenErrorEvaluator().evaluateError(network, splitter.getTestFeature(), splitter.getTestObserved());
        assertThat(error).isLessThan(0.08);
    }

    @Test
    public void callibrate() throws Exception {
        TrainingTestSplitter splitter = TestUtils.getIrisData();


        GenerationManager<BitSetIndividual> generationManager = new ReplacementGenerationManager<>(new RouletteSelector(), new OnePointCut(), new BitSetMutator(0.01),0.6,4);
        int[] layersSize = {4,3};
        BitsetGeneticAlgorithmNetworkTrainer trainer = new BitsetGeneticAlgorithmNetworkTrainer(
                600, 50, 0.0001,generationManager, new ChenErrorEvaluator(), layersSize,new ParametrizedBiasSigmoidTransferFunction(), 4);
        NeuralNetworkCalibrator calibrator = new NeuralNetworkCalibrator(trainer);

        calibrator.calibrateLayerSize(250,15,splitter.getFullFeature(),splitter.getFullObserved(),2,5,2,8);
    }
}
