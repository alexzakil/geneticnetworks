package geneticalgorithm.evaluation;


import geneticalgorithm.individual.BitSetIndividual;

public interface BitsetEvaluator<T extends BitSetIndividual> extends Evaluator<T>{
    public double evaluate(T individual);

}
