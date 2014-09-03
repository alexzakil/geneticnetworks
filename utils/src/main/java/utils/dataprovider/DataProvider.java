package utils.dataprovider;

/**
 * A class that provides data for classification problems.
 */
public interface DataProvider {

    /**
     *
     * @return an array of feature arrays. Each array represents the different known features of one example
     */
    public double[][] getFeature();

    /**
     *
     * @return an array of correctly identified output values. Each array represents the correct output values of one example.
     */
    public double[][] getObserved();

}
