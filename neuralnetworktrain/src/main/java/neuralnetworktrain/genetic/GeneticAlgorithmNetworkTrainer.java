package neuralnetworktrain.genetic;

import geneticalgorithm.GeneticAlgorithm;
import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.individual.Individual;
import geneticalgorithm.individual.IndividualFactory;
import neuralnetwork.Network;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;
import neuralnetworktrain.AbstractNeuralNetworkTrainer;
import neuralnetworktrain.genetic.evaluation.NetworkFitnessFunction;

import java.util.List;

/**
 * An algorithm to build neural networks using a genetic algorithm.
 * @param <I>
 */
public abstract class GeneticAlgorithmNetworkTrainer<I extends Individual> extends AbstractNeuralNetworkTrainer{

    /**
     * The population size to use.
     */
    int populationSize;

    /**
     * The GenerationManager of the algorithm
     */
    GenerationManager<I> generationManager;

    protected GeneticAlgorithmNetworkTrainer() {
    }

    /**
     *
     * @param maxGenerations maximum generations to run the algorithm
     * @param populationSize the starting number of individuals
     * @param wantedError When the error is smaller that this number the algorithm will stop
     * @param generationManager the GenerationManager of the algorithm
     * @param errorEvaluator the algorithm to evaluate error of a neural network
     * @param layersSize the sizes of the layers of the network
     */
    protected GeneticAlgorithmNetworkTrainer(int maxGenerations, int populationSize, double wantedError, GenerationManager<I> generationManager, ErrorEvaluator errorEvaluator, int[] layersSize) {
        this.maxGenerations = maxGenerations;
        this.populationSize = populationSize;
        this.wantedError = wantedError;
        this.generationManager = generationManager;

        this.errorEvaluator = errorEvaluator;
        this.layersSize = layersSize;
    }

    @Override
    public Network trainNetwork(double[][] feature, double[][] observed, TransferFunction transferFunction) {
        GeneticAlgorithm<I> geneticAlgorithm = createGeneticAlgorithm(feature,observed,transferFunction);
        List<I> list = geneticAlgorithm.runAlgorithm(populationSize, maxGenerations, 1 - wantedError);
        return ((NetworkFitnessFunction<I>)geneticAlgorithm.getFitnessFunction()).createNetworkFromIndividual(list.get(list.size()-1));
    }


    private GeneticAlgorithm<I> createGeneticAlgorithm(double[][] feature, double[][] observed, TransferFunction transferFunction) {
        NetworkFitnessFunction<I> networkEvaluator = createNetworkFitnessFunction(feature, observed, transferFunction);

        return new GeneticAlgorithm<>(createIndividualFactory(getNumberOfNeuronParamsNeeded(feature,observed,transferFunction)),networkEvaluator,generationManager);
    }

    private int getNumberOfNeuronParamsNeeded(double[][] feature, double[][] observed, TransferFunction transferFunction){
        if(layersSize == null) {
            throw new RuntimeException("Trainer still uninitialized. Set layersSize please;");
        }
        int numberOfParams = 0;
        int currentLayerInputs = feature[0].length;
        for (int neuronsInLayer : layersSize) {
            numberOfParams+=neuronsInLayer*transferFunction.getNumberOfParams(currentLayerInputs);
            currentLayerInputs=neuronsInLayer;
        }
        return numberOfParams;
    }

    /**
     * A template method for creating a FitnessFunction of the genetic algorithm.
     * @param feature the training feature data (that the network should use in prediction)
     * @param observed the training observed data (that the network must learn to predict)
     * @param transferFunction the transfer function of the network
     * @return a FitnessFunction to evaluate individuals that represent NeuralNetworks
     */
    protected abstract NetworkFitnessFunction<I> createNetworkFitnessFunction(double[][] feature, double[][] observed, TransferFunction transferFunction);

    /**
     * A template method to create a factory for creating individuals for the initial population
     * @param numberOfParamsNeeded how many numeric parameters are required to instantiate the network. Equals to [number of neurons]*[number of parameter per neuron]
     * @return  a factory for creating individuals for the initial population
     */
    protected abstract IndividualFactory<I> createIndividualFactory(int numberOfParamsNeeded);

    @Override
    public void continueTrainingNetwork(double[][] feature, double[][] observed, Network network) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public GenerationManager<I> getGenerationManager() {
        return generationManager;
    }

    public void setGenerationManager(GenerationManager<I> generationManager) {
        this.generationManager = generationManager;
    }



}
