package neuralnetworktrain;

import neuralnetwork.Network;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeuralNetworkCalibrator {
    static final Logger logger = Logger.getLogger(NeuralNetworkCalibrator.class);


    AbstractNeuralNetworkTrainer neuralNetworkTrainer;

    public NeuralNetworkCalibrator(AbstractNeuralNetworkTrainer neuralNetworkTrainer) {
        this.neuralNetworkTrainer = neuralNetworkTrainer;
    }

    public AbstractNeuralNetworkTrainer getNeuralNetworkTrainer() {
        return neuralNetworkTrainer;
    }

    public void setNeuralNetworkTrainer(AbstractNeuralNetworkTrainer neuralNetworkTrainer) {
        this.neuralNetworkTrainer = neuralNetworkTrainer;
    }

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
                Network network = neuralNetworkTrainer.trainNetwork(feature, observed);
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
