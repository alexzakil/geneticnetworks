package neuralnetworktrain;

import neuralnetwork.Network;
import neuralnetwork.transferfunction.ParametrizedBiasSigmoidTransferFunction;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * A tool for testing which configurations work best for training neural networks.
 */
public class NeuralNetworkCalibrator<T extends AbstractNeuralNetworkTrainer> {
    static final Logger logger = Logger.getLogger(NeuralNetworkCalibrator.class);


    /**
     * The trainer to use during the calibration
     */
    T neuralNetworkTrainer;

    public NeuralNetworkCalibrator(T neuralNetworkTrainer) {
        this.neuralNetworkTrainer = neuralNetworkTrainer;
    }

    public AbstractNeuralNetworkTrainer getNeuralNetworkTrainer() {
        return neuralNetworkTrainer;
    }

    public void setNeuralNetworkTrainer(T neuralNetworkTrainer) {
        this.neuralNetworkTrainer = neuralNetworkTrainer;
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
     */
    public void calibrateLayerSize(int maxGenerations, int numberOfRuns, double[][] feature, double[][] observed, int minNumberOfLayers, int maxNumberOfLayers, int minLayerSize, int maxLayerSize){
        List<List<Integer>> permutations = new ArrayList<>();
        for(int numLayers = minNumberOfLayers; numLayers<=maxNumberOfLayers; numLayers++) {
            addPermutationsForLayerSize(permutations, numLayers, minLayerSize, maxLayerSize, new ArrayList< Integer>());
        }
        neuralNetworkTrainer.setMaxGenerations(maxGenerations);
        for (List<Integer> layersSize : permutations) {
            if(layersSize.get(layersSize.size()-1) != observed[0].length) {
                continue;
            }
            neuralNetworkTrainer.setLayersSize(ArrayUtils.toPrimitive(layersSize.toArray(new Integer[layersSize.size()])));
            double sumError  =0;
            for(int i = 0; i< numberOfRuns; i++) {
                Network network = neuralNetworkTrainer.trainNetwork(feature, observed, new ParametrizedBiasSigmoidTransferFunction());
                sumError += neuralNetworkTrainer.getErrorEvaluator().evaluateError(network,feature,observed);
            }
            logger.info("Callibrated for layers size: "+ StringUtils.join(layersSize,',')+". Error: "+sumError/numberOfRuns);
        }
    }

    private void addPermutationsForLayerSize(List<List<Integer>> permutations, int numLayers, int minLayerSize, int maxLayerSize,List<Integer> prefix) {
        if(numLayers ==0) {
            permutations.add(prefix);
        } else {
            for(int layerSize = minLayerSize;layerSize<=maxLayerSize;layerSize++) {
                ArrayList<Integer> newPrefix = new ArrayList<>(prefix);
                newPrefix.add(layerSize);
                addPermutationsForLayerSize(permutations, numLayers-1, minLayerSize, maxLayerSize, newPrefix);
            }
        }
    }
}
