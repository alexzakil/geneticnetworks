package neuralnetworktrain.genetic;

import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.individual.BitSetIndividual;
import geneticalgorithm.individual.BitSetIndividualFactory;
import geneticalgorithm.individual.IndividualFactory;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;
import neuralnetworktrain.genetic.evaluator.BitsetNetworkEvaluator;

public class BitsetGeneticAlgorithmNetworkTrainer extends GeneticAlgorithmNetworkTrainer<BitSetIndividual> {

    private int genesForOneNumber;


    public BitsetGeneticAlgorithmNetworkTrainer() {
    }

    public BitsetGeneticAlgorithmNetworkTrainer(int maxGenerations, int populationSize, double wantedError, GenerationManager<BitSetIndividual> generationManager, ErrorEvaluator errorEvaluator, int[] layersSize, int genesForOneNumber) {
        super(maxGenerations, populationSize, wantedError, generationManager, errorEvaluator, layersSize);
        this.genesForOneNumber = genesForOneNumber;

    }

    @Override
    protected BitsetNetworkEvaluator createNetworkEvaluator(double[][] feature, double[][] observed, TransferFunction transferFunction) {
        return new BitsetNetworkEvaluator(feature, observed, errorEvaluator, genesForOneNumber,  transferFunction, layersSize);
    }

    @Override
    protected IndividualFactory<BitSetIndividual> createIndividualFactory(int numberOfParamsNeeded) {
        return new BitSetIndividualFactory(numberOfParamsNeeded*genesForOneNumber);
    }


    public int getGenesForOneNumber() {
        return genesForOneNumber;
    }

    public void setGenesForOneNumber(int genesForOneNumber) {
        this.genesForOneNumber = genesForOneNumber;
    }

}
