package utils.dataprovider;

import org.junit.Test;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;

public class CsvDataProviderTest {

    @Test
    public void testRead() throws Exception {
        CsvDataProvider csvDataProvider = new CsvDataProvider(new File(getClass().getResource("/iris.csv").getFile()), "i", "o");
        double[][] feature = csvDataProvider.getFeature();
        double[][] observed = csvDataProvider.getObserved();
        assertThat(feature).hasSize(150);
        assertThat(observed).hasSize(150);
        assertThat(feature[1]).containsOnly(4.9,3,1.4,0.2);
        assertThat(observed[1]).containsOnly(1,0,0);

    }
}
