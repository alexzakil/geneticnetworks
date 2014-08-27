package geneticalgorithm;

import geneticalgorithm.crossover.OnePointCut;
import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.generation.ReplacementGenerationManager;
import geneticalgorithm.individual.BitSetIndividual;
import geneticalgorithm.individual.BitSetIndividualFactory;
import geneticalgorithm.mutation.BitSetMutator;
import geneticalgorithm.selection.RouletteSelector;
import org.junit.Test;


import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class StringMatchingGeneticAlgorithmTest {

    @Test
    public void testAlgorithm() throws Exception {

        StringMatchingEvaluator stringMatchingEvaluator = new StringMatchingEvaluator("abc");
        GenerationManager<BitSetIndividual> generationManager = new ReplacementGenerationManager<>(new RouletteSelector(),new OnePointCut(),new BitSetMutator(0.01), 0.6);
        GeneticAlgorithm<BitSetIndividual> geneticAlgorithm = new GeneticAlgorithm<>(new BitSetIndividualFactory(stringMatchingEvaluator.getNumberOfBitsPerIndividual()),stringMatchingEvaluator,generationManager);

        List<BitSetIndividual> finalPopulation = geneticAlgorithm.runAlgorithm(30, 500, 1);
        assertThat(finalPopulation).hasSize(30);
        BitSetIndividual best = finalPopulation.get(finalPopulation.size() - 1);
        assertThat(best.getFitness()).isEqualTo(1);
        assertThat(stringMatchingEvaluator.evaluate(best)).isEqualTo(1);

    }
}
