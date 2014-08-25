package geneticalgorithm.generation;

import geneticalgorithm.crossover.CrossOver;
import geneticalgorithm.evaluator.Evaluator;
import geneticalgorithm.individual.Individual;
import geneticalgorithm.mutation.Mutator;
import geneticalgorithm.selection.Selector;
import utils.NumberUtils;

import java.util.*;

public class AdditionGenerationManager<I extends Individual> extends DefaultGenerationManager<I> {


    double pCross;


    public AdditionGenerationManager() {
    }

    public AdditionGenerationManager(Selector selector, CrossOver<I> crossOver, Mutator<I> mutator, double pCross) {
        super(selector, crossOver, mutator);
        this.pCross = pCross;
    }

    @Override
    public List<I> nextGeneration(List<I> oldGeneration,Evaluator<I> evaluator) {
        List<I> selection = selector.select(oldGeneration);

        Collections.shuffle(selection,NumberUtils.rnd);

        int mateIndex = -1;
        List<I> nextGeneration = new LinkedList<>();
        for(int i = 0;i<selection.size();i++) {
            if(NumberUtils.rnd.nextDouble() < pCross) {
                if(mateIndex == -1) {
                    mateIndex = i;
                } else {
                    nextGeneration.addAll(crossOver.breed(selection.get(i), selection.get(mateIndex)));
                    mateIndex = -1;
                }
            }

        }

        for (I newIndividual : nextGeneration) {
            mutator.mutate(newIndividual);
            newIndividual.setFitness(evaluator.evaluate(newIndividual));
        }
        nextGeneration.addAll(oldGeneration);
        Collections.sort(nextGeneration);
        if(nextGeneration.size() > oldGeneration.size()) {
            //There are too many individuals. Only the fittest survive
            nextGeneration = nextGeneration.subList(nextGeneration.size()-oldGeneration.size(),nextGeneration.size());
        }

        return nextGeneration;
    }

    public double getpCross() {
        return pCross;
    }

    public void setpCross(double pCross) {
        this.pCross = pCross;
    }

}
