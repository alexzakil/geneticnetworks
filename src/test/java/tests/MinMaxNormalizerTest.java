package tests;

import normalizer.MinMaxNormalizer;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class MinMaxNormalizerTest {

    @Test
    public void testNormalizer() throws Exception {
        double[][] input = new double[][]{
                {40.0,0.5},
                {-10.0,1.0},
                {10.0,1.5}
        };
        new MinMaxNormalizer().normalize(input);

        double[][] expected = new double[][]{
                {1.0,0.0},
                {0.0,0.5},
                {0.4,1.0}
        };
        assertThat(input).isEqualTo(expected);

    }
}
