package utils.trainingtestsplit;

/**
 * Utility to split feature and observed data to training and testing arrays
 */
public interface TrainingTestSplitter {

    /**
     * Splits feature and observed data
     * @param feature the feature data
     * @param observed the observed data
     */
    void split(double[][] feature, double[][] observed);

    /**
     * @return the feature data to be used for training
     */
    public double[][] getTrainingFeature();

    /**
     * @return the observed data to be used for training
     */
    public double[][] getTrainingObserved();

    /**
     * @return the feature data to be used for testing
     */
    public double[][] getTestFeature();

    /**
     * @return the observed data to be used for testing
     */
    public double[][] getTestObserved();

    /**
     * @return the whole feature data
     */
    public double[][] getFullFeature() ;

    /**
     * @return the whole observed data
     */
    public double[][] getFullObserved() ;

}
