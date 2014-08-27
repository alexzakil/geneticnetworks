package geneticalgorithm.evaluation;


import geneticalgorithm.individual.Individual;

public interface Evaluator<T extends Individual>{
    public double evaluate(T individual);
}
