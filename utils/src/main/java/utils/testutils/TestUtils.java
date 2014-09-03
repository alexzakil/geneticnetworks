package utils.testutils;

import utils.dataprovider.CsvDataProvider;
import utils.normalizer.MinMaxNormalizer;
import utils.trainingtestsplit.RandomTrainingTestSplitter;
import utils.trainingtestsplit.TrainingTestSplitter;

import java.io.File;

/**
 * Utilities for testing machine learning
 */
public class TestUtils {

    public static TrainingTestSplitter getIrisData() throws Exception {
        return getCsvData("/iris.csv");
    }

    public static TrainingTestSplitter getWineData() throws Exception {
        return getCsvData("/DataWine.csv");
    }

    public static TrainingTestSplitter getGlassData() throws Exception {
        return getCsvData("/glass.csv");
    }

    public static TrainingTestSplitter getVehicleData() throws Exception {
        return getCsvData("/vehicle.csv");
    }

    private static TrainingTestSplitter getCsvData(String filename) throws Exception {
        CsvDataProvider csvDataProvider = new CsvDataProvider(new File(TestUtils.class.getResource(filename).getFile()), "i", "o");
        MinMaxNormalizer normalizer = new MinMaxNormalizer();
        double[][] feature = csvDataProvider.getFeature();
        normalizer.normalize(feature);
        double[][] observed = csvDataProvider.getObserved();
        normalizer.normalize(observed);


        RandomTrainingTestSplitter splitter = new RandomTrainingTestSplitter(0.2);
        splitter.split(feature, observed);
        return splitter;
    }

}
