package utils.testutils;

import utils.dataprovider.CsvDataProvider;
import utils.normalizer.MinMaxNormalizer;
import utils.trainingtestsplit.RandomTrainingTestSplitter;
import utils.trainingtestsplit.TrainingTestSplitter;

import java.io.File;

public class TestUtils {

    public static TrainingTestSplitter getIrisData() throws Exception {
        CsvDataProvider csvDataProvider = new CsvDataProvider(new File(TestUtils.class.getResource("/iris.csv").getFile()), "i", "o");
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
