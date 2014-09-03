package utils.dataprovider;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * A data provider that reads information from a CSV file.
 * An input file should have a single header line.
 */
public class CsvDataProvider implements DataProvider {

    Set<Integer> featureIndexes = new HashSet<>();
    Set<Integer> observedIndexes = new HashSet<>();
    Set<Integer> allIndexes = new HashSet<>();


    private double[][] feature;
    private double[][] observed;

    /**
     * Creates a new CSV data provider.
     * @param file the file to read
     * @param featureHeader the value that should be in feature column headers of the input file
     * @param observedHeader the value that should be in the the observed column headers of the input file
     * @throws Exception
     */
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
