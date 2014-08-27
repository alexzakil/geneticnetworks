package utils.normalizer;

import java.util.HashMap;
import java.util.Map;

public class MinMaxNormalizer implements DataNormalizer {
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
