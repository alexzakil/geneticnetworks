package neuralnetworktrain.geneticalgorithm;

import geneticalgorithm.evaluator.RealListNetworkEvaluator;
import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.individual.*;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;

public class RealListGeneticAlgorithmNetworkTrainer extends GeneticAlgorithmNetworkTrainer<RealListIndividual> {

    public RealListGeneticAlgorithmNetworkTrainer() {
    }

    public RealListGeneticAlgorithmNetworkTrainer(int maxGenerations, int populationSize, double wantedError, GenerationManager<RealListIndividual> generationManager, ErrorEvaluator errorEvaluator, int[] layersSize, TransferFunction transferFunction) {
        super(maxGenerations, populationSize, wantedError, generationManager,errorEvaluator, layersSize, transferFunction);


    }

    @Override
    protected RealListNetworkEvaluator createNetworkEvaluator(double[][] feature, double[][] observed) {
        return new RealListNetworkEvaluator(feature, observed, errorEvaluator,  transferFunction, layersSize);
    }

    @Override
    protected IndividualFactory<RealListIndividual> createIndividualFactory(int numberOfParamsNeeded) {
        return new RealListIndividualFactory(numberOfParamsNeeded,-1,1);
    }



}
