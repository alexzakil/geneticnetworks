package neuralnetworktrain.genetic;

import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.individual.BinaryIndividual;
import geneticalgorithm.individual.BinaryIndividualFactory;
import geneticalgorithm.individual.IndividualFactory;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;
import neuralnetworktrain.genetic.evaluation.BitsetNetworkFitnessFunction;

/**
 * An algorithm to build neural networks using a genetic algorithm where every individual is represented as a BitSet.
 */
public class BitsetGeneticAlgorithmNetworkTrainer extends GeneticAlgorithmNetworkTrainer<BinaryIndividual> {

    private int genesForOneNumber;


    public BitsetGeneticAlgorithmNetworkTrainer() {
    }

    public BitsetGeneticAlgorithmNetworkTrainer(int maxGenerations, int populationSize, double wantedError, GenerationManager<BinaryIndividual> generationManager, ErrorEvaluator errorEvaluator, int[] layersSize, int genesForOneNumber) {
        super(maxGenerations, populationSize, wantedError, generationManager, errorEvaluator, layersSize);
        this.genesForOneNumber = genesForOneNumber;

    }

    @Override
    protected BitsetNetworkFitnessFunction createNetworkFitnessFunction(double[][] feature, double[][] observed, TransferFunction transferFunction) {
        return new BitsetNetworkFitnessFunction(feature, observed, errorEvaluator, genesForOneNumber,  transferFunction, layersSize);
    }

    @Override
    protected IndividualFactory<BinaryIndividual> createIndividualFactory(int numberOfParamsNeeded) {
        return new BinaryIndividualFactory(numberOfParamsNeeded*genesForOneNumber);
    }


    public int getGenesForOneNumber() {
        return genesForOneNumber;
    }

    public void setGenesForOneNumber(int genesForOneNumber) {
        this.genesForOneNumber = genesForOneNumber;
    }

}
