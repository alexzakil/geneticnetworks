package geneticalgorithm;

import geneticalgorithm.crossover.BinaryOnePointCut;
import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.generation.ReplacementGenerationManager;
import geneticalgorithm.individual.BinaryIndividual;
import geneticalgorithm.individual.BinaryIndividualFactory;
import geneticalgorithm.mutation.BinaryMutator;
import geneticalgorithm.selection.RouletteSelector;
import org.junit.Test;


import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class StringMatchingGeneticAlgorithmTest {

    @Test
    public void testAlgorithm() throws Exception {

        StringMatchingFitnessFunction stringMatchingEvaluator = new StringMatchingFitnessFunction("abc");
        GenerationManager<BinaryIndividual> generationManager = new ReplacementGenerationManager<>(new RouletteSelector(),new BinaryOnePointCut(),new BinaryMutator(0.01), 0.6);
        GeneticAlgorithm<BinaryIndividual> geneticAlgorithm = new GeneticAlgorithm<>(new BinaryIndividualFactory(stringMatchingEvaluator.getNumberOfBitsPerIndividual()),stringMatchingEvaluator,generationManager);

        List<BinaryIndividual> finalPopulation = geneticAlgorithm.runAlgorithm(30, 500, 1);
        assertThat(finalPopulation).hasSize(30);
        BinaryIndividual best = finalPopulation.get(finalPopulation.size() - 1);
        assertThat(best.getFitness()).isEqualTo(1);
        assertThat(stringMatchingEvaluator.getFitness(best)).isEqualTo(1);

    }
}
