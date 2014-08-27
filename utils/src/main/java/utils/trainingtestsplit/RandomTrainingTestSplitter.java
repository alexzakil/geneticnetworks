package utils.trainingtestsplit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomTrainingTestSplitter implements TrainingTestSplitter{

    double testPart;

    private double[][] trainingFeature,testFeature,fullFeature;
    private double[][] trainingObserved, testObserved, fullObserved;

    public RandomTrainingTestSplitter(double testPart) {
        this.testPart = testPart;
    }

    @Override
    public void split(double[][] feature, double[][] observed) {
        List<Integer> indexes = new ArrayList<>();
        fullFeature = feature;
        fullObserved = observed;
        for(int i=0;i<feature.length;i++) {
            indexes.add(i);
        }
        Collections.shuffle(indexes);

        int numToTest = (int) (feature.length * testPart);

        testFeature = new double[ numToTest][];
        testObserved = new double[ numToTest][];

        trainingFeature = new double[feature.length-testFeature.length][];
        trainingObserved = new double[feature.length - testFeature.length][];

        for(int i=0;i<feature.length;i++) {
            Integer currIndex = indexes.get(i);
            if(i<numToTest) {
                testFeature[i] = feature[currIndex];
                testObserved[i] = observed[currIndex];
            } else {
                trainingFeature[i-numToTest] = feature[currIndex];
                trainingObserved[i-numToTest] = observed[currIndex];
            }
        }
    }

    @Override
    public double[][] getTrainingFeature() {

        return trainingFeature;
    }

    @Override
    public double[][] getTrainingObserved() {
        return trainingObserved;
    }

    @Override
    public double[][] getTestFeature() {
        return testFeature;
    }

    @Override
    public double[][] getTestObserved() {
        return testObserved;
    }

    public double[][] getFullFeature() {
        return fullFeature;
    }

    public double[][] getFullObserved() {
        return fullObserved;
    }
}
