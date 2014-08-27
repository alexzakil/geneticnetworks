package neuralnetworktrain.genetic;


import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.individual.IndividualFactory;
import geneticalgorithm.individual.RealListIndividual;
import geneticalgorithm.individual.RealListIndividualFactory;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;
import neuralnetworktrain.genetic.evaluator.RealListNetworkEvaluator;

public class RealListGeneticAlgorithmNetworkTrainer extends GeneticAlgorithmNetworkTrainer<RealListIndividual> {

    public RealListGeneticAlgorithmNetworkTrainer() {
    }

    public RealListGeneticAlgorithmNetworkTrainer(int maxGenerations, int populationSize, double wantedError, GenerationManager<RealListIndividual> generationManager, ErrorEvaluator errorEvaluator, int[] layersSize) {
        super(maxGenerations, populationSize, wantedError, generationManager,errorEvaluator, layersSize);


    }

    @Override
    protected RealListNetworkEvaluator createNetworkEvaluator(double[][] feature, double[][] observed, TransferFunction transferFunction) {
        return new RealListNetworkEvaluator(feature, observed, errorEvaluator,  transferFunction, layersSize);
    }

    @Override
    protected IndividualFactory<RealListIndividual> createIndividualFactory(int numberOfParamsNeeded) {
        return new RealListIndividualFactory(numberOfParamsNeeded,-1,1);
    }



}
