package dataprovider;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.*;

public class CsvDataProvider implements DataProvider {

    Set<Integer> featureIndexes = new HashSet<>();
    Set<Integer> observedIndexes = new HashSet<>();
    Set<Integer> allIndexes = new HashSet<>();


    private double[][] feature;
    private double[][] observed;

    public CsvDataProvider(File file, String featureHeader, String observedHeader) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(file));
        List<String[]> all = reader.readAll();
        String[] header = all.get(0);
        int i = 0;
        for (String s : header) {
            if (s.equalsIgnoreCase(featureHeader)) {
                featureIndexes.add(i);
            }
            if (s.equalsIgnoreCase(observedHeader)) {
                observedIndexes.add(i);
            }
            i++;
        }

        allIndexes = new HashSet<>(featureIndexes);
        allIndexes.addAll(observedIndexes);

        all = all.subList(1,all.size());

        feature = new double[all.size()][];
        observed = new double[all.size()][];

        int row = 0;
        for (String[] records : all) {
            feature[row] = new double[featureIndexes.size()];
            observed[row] = new double[observedIndexes.size()];
            int featureIndex = 0;
            int observedIndex = 0;
            for (Integer index : allIndexes) {
                if(featureIndexes.contains(index)){
                    feature[row][featureIndex] = Double.parseDouble(records[index]);
                    featureIndex ++;
                }
                if(observedIndexes.contains(index)){
                    observed[row][observedIndex] = Double.parseDouble(records[index]);
                    observedIndex ++;
                }
            }
            row++;
        }

    }

    @Override
    public double[][] getFeature() {

        return feature;
    }

    @Override
    public double[][] getObserved() {
        return observed;
    }


}
