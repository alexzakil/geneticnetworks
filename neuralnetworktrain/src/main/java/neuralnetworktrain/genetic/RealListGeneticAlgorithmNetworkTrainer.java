package neuralnetworktrain.genetic;


import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.individual.IndividualFactory;
import geneticalgorithm.individual.RealListIndividual;
import geneticalgorithm.individual.RealListIndividualFactory;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;
import neuralnetworktrain.genetic.evaluation.RealListNetworkFitnessFunction;

/**
 * An algorithm to build neural networks using a genetic algorithm where every individual is represented as a list of real numbers.
 */

public class RealListGeneticAlgorithmNetworkTrainer extends GeneticAlgorithmNetworkTrainer<RealListIndividual> {

    public RealListGeneticAlgorithmNetworkTrainer() {
    }

    public RealListGeneticAlgorithmNetworkTrainer(int maxGenerations, int populationSize, double wantedError, GenerationManager<RealListIndividual> generationManager, ErrorEvaluator errorEvaluator, int[] layersSize) {
        super(maxGenerations, populationSize, wantedError, generationManager,errorEvaluator, layersSize);


    }

    @Override
    protected RealListNetworkFitnessFunction createNetworkFitnessFunction(double[][] feature, double[][] observed, TransferFunction transferFunction) {
        return new RealListNetworkFitnessFunction(feature, observed, errorEvaluator,  transferFunction, layersSize);
    }

    @Override
    protected IndividualFactory<RealListIndividual> createIndividualFactory(int numberOfParamsNeeded) {
        return new RealListIndividualFactory(numberOfParamsNeeded,-1,1);
    }



}
