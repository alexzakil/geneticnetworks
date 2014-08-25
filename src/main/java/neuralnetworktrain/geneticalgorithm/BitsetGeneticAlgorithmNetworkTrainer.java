package neuralnetworktrain.geneticalgorithm;

import geneticalgorithm.evaluator.BitsetNetworkEvaluator;
import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.individual.BitSetIndividual;
import geneticalgorithm.individual.BitSetIndividualFactory;
import geneticalgorithm.individual.IndividualFactory;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;

public class BitsetGeneticAlgorithmNetworkTrainer extends GeneticAlgorithmNetworkTrainer<BitSetIndividual> {

    private int genesForOneNumber;


    public BitsetGeneticAlgorithmNetworkTrainer() {
    }

    public BitsetGeneticAlgorithmNetworkTrainer(int maxGenerations, int populationSize, double wantedError, GenerationManager<BitSetIndividual> generationManager, ErrorEvaluator errorEvaluator, int[] layersSize, TransferFunction transferFunction, int genesForOneNumber) {
        super(maxGenerations, populationSize, wantedError, generationManager, errorEvaluator, layersSize, transferFunction);
        this.genesForOneNumber = genesForOneNumber;

    }

    @Override
    protected BitsetNetworkEvaluator createNetworkEvaluator(double[][] feature, double[][] observed) {
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
