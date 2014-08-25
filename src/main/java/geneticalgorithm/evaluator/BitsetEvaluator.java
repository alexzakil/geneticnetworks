package geneticalgorithm.evaluator;

import geneticalgorithm.individual.BitSetIndividual;
import geneticalgorithm.individual.Individual;

public interface BitsetEvaluator<T extends BitSetIndividual> extends Evaluator<T>{
    public double evaluate(T individual);

}
