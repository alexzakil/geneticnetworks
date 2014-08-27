package geneticalgorithm.generation;


import geneticalgorithm.evaluation.Evaluator;
import geneticalgorithm.individual.Individual;

import java.util.List;

public interface GenerationManager<I extends Individual> {

    public List<I> nextGeneration(List<I> oldGeneration, Evaluator<I> evaluator);
}
