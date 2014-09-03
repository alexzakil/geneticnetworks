package utils.normalizer;

import java.util.HashMap;
import java.util.Map;

/**
 * Normalizes all values to be in the [0,1] range using feature scaling (http://en.wikipedia.org/wiki/Feature_scaling)
 */
public class MinMaxNormalizer implements DataNormalizer {

    /**
     * For each array in the input, find the minimum and the maximum. Then normalize values in each array
     * using feature scaling.
     * @param input The input array
     */
    @Override
    public void normalize(double[][] input) {
        Map<Integer,Double[]> indexToMinMaxMap = new HashMap<>();
        for (double[] records : input) {
            for (int i=0;i<records.length;i++) {
                Double currValue = records[i];
                Double[] minMax = indexToMinMaxMap.get(i);
                if(minMax == null) {
                    indexToMinMaxMap.put(i,new Double[]{currValue,currValue});
                } else {
                    if(minMax[0] > currValue) {
                        minMax[0] = currValue;
                    }
                    if(minMax[1] < currValue) {
                        minMax[1] = currValue;
                    }
                }
            }
        }
        for (double[] records : input) {
            for (int i=0;i<records.length;i++) {
                Double currValue = records[i];
                Double[] minMax = indexToMinMaxMap.get(i);
                if(minMax[0].equals(minMax[1])) {
                    currValue = 0d;
                } else {
                    currValue = (currValue - minMax[0])/(minMax[1] - minMax[0]);
                }
                records[i] = currValue;
            }
        }

    }
}
