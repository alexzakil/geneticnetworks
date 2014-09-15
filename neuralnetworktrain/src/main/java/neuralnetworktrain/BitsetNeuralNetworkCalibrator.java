package neuralnetworktrain;

import neuralnetworktrain.genetic.BitsetGeneticAlgorithmNetworkTrainer;
import org.apache.log4j.Logger;

public class BitsetNeuralNetworkCalibrator extends NeuralNetworkCalibrator<BitsetGeneticAlgorithmNetworkTrainer>{
    static final Logger logger = Logger.getLogger(BitsetNeuralNetworkCalibrator.class);


    public BitsetNeuralNetworkCalibrator(BitsetGeneticAlgorithmNetworkTrainer neuralNetworkTrainer) {
        super(neuralNetworkTrainer);
    }

    /**
     * Try the training with different layer sizes
     * @param maxGenerations number of generations to run the training
     * @param numberOfRuns number of times to run for each layer size permutation. The average score per permutation will be found. More runs means less random variation but much longer run time. At least 3 is recommended
     * @param feature the input to train the network to recognize
     * @param observed the output to train the network to return
     * @param minNumberOfLayers the minimum number of layers to try
     * @param maxNumberOfLayers the maximum number of layers to try
     * @param minLayerSize the minimum neurons per layer to try
     * @param maxLayerSize the maximum neurons per layer to try
     * @param minBitsPerNumber minimum bits per number
     * @param maxBitsPerNumber minimum bits per number
     */
    public void calibrateLayerSizeAndBitsPerNumber(int maxGenerations, int numberOfRuns, double[][] feature, double[][] observed, int minNumberOfLayers, int maxNumberOfLayers, int minLayerSize, int maxLayerSize, int minBitsPerNumber, int maxBitsPerNumber){
        for(int bitsPerNumber = minBitsPerNumber; bitsPerNumber <= maxBitsPerNumber; bitsPerNumber++) {
            logger.info("Calibrating for "+bitsPerNumber+" bits per number.");
            neuralNetworkTrainer.setBitsForOneNumber(bitsPerNumber);
            calibrateLayerSize(maxGenerations,numberOfRuns,feature,observed,minNumberOfLayers,maxNumberOfLayers,minLayerSize,maxLayerSize);
        }
    }
}