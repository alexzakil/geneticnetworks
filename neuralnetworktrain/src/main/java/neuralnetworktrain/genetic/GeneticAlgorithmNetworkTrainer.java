package neuralnetworktrain.genetic;

import geneticalgorithm.GeneticAlgorithm;
import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.individual.Individual;
import geneticalgorithm.individual.IndividualFactory;
import neuralnetwork.Network;
import neuralnetwork.errorevaluation.ErrorEvaluator;
import neuralnetwork.transferfunction.TransferFunction;
import neuralnetworktrain.AbstractNeuralNetworkTrainer;
import neuralnetworktrain.genetic.evaluator.NetworkEvaluator;

import java.util.List;

public abstract class GeneticAlgorithmNetworkTrainer<I extends Individual> extends AbstractNeuralNetworkTrainer{

    int populationSize;
    GenerationManager<I> generationManager;

    protected GeneticAlgorithmNetworkTrainer() {
    }

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
        return ((NetworkEvaluator<I>)geneticAlgorithm.getEvaluator()).createNetworkFromIndividual(list.get(list.size()-1));
    }


    private GeneticAlgorithm<I> createGeneticAlgorithm(double[][] feature, double[][] observed, TransferFunction transferFunction) {
        NetworkEvaluator<I> networkEvaluator = createNetworkEvaluator(feature,observed,transferFunction);

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
    protected abstract NetworkEvaluator<I> createNetworkEvaluator(double[][] feature, double[][] observed, TransferFunction transferFunction);

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
