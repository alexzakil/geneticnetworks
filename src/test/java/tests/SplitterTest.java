package tests;

import org.junit.Test;
import trainingtestsplit.RandomTrainingTestSplitter;

import static org.fest.assertions.Assertions.assertThat;

public class SplitterTest {

    @Test
    public void testSplit() throws Exception {
        RandomTrainingTestSplitter splitter = new RandomTrainingTestSplitter(0.25);

        double[][] feature = new double[][]{
                {40.0,0.5},
                {-10.0,1.0},
                {20,0.5},
                {-10.0,1.0},
                {40.0,0.5},
                {-10.0,1.0},
                {40.0,0.5},
                {0.0,1.0}
        };
        double[][] observed = new double[][]{
                {40.0},
                {-10.0},
                {40.0},
                {-10.0},
                {40.0},
                {-10.0},
                {40.0},
                {-10.0}
        };
        splitter.split(feature,observed);
        assertThat(splitter.getTestFeature()).hasSize(2);
        assertThat(splitter.getTestObserved()).hasSize(2);
        assertThat(splitter.getTrainingFeature()).hasSize(6);
        assertThat(splitter.getTrainingObserved()).hasSize(6);
    }
}
