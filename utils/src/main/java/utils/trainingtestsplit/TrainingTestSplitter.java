package utils.trainingtestsplit;

public interface TrainingTestSplitter {

    void split(double[][] feature, double[][] observed);

    public double[][] getTrainingFeature();

    public double[][] getTrainingObserved();

    public double[][] getTestFeature();

    public double[][] getTestObserved();

    public double[][] getFullFeature() ;

    public double[][] getFullObserved() ;

}
