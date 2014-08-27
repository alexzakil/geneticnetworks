package geneticalgorithm.generation;

import geneticalgorithm.crossover.CrossOver;
import geneticalgorithm.evaluation.Evaluator;
import geneticalgorithm.individual.Individual;
import geneticalgorithm.mutation.Mutator;
import geneticalgorithm.selection.Selector;
import utils.NumberUtils;

import java.util.*;

public class ReplacementGenerationManager<I extends Individual> extends DefaultGenerationManager<I> {

    int elitism=0;
    double pCross;


    public ReplacementGenerationManager() {
    }

    public ReplacementGenerationManager(Selector selector, CrossOver<I> crossOver, Mutator<I> mutator, double pCross) {
        super(selector, crossOver, mutator);
        this.pCross = pCross;
    }

    public ReplacementGenerationManager(Selector selector, CrossOver<I> crossOver, Mutator<I> mutator, double pCross,int elitism) {
        super(selector, crossOver, mutator);
        this.elitism = elitism;
        this.pCross = pCross;
    }

    @Override
    public List<I> nextGeneration(List<I> oldGeneration,Evaluator<I> evaluator) {
        List<I> elite = null;

        List<I> selection = selector.select(oldGeneration);
        if(elitism > 0) {
            elite = new ArrayList<>(oldGeneration.subList(oldGeneration.size() - elitism, oldGeneration.size()));
            for(int i =0;i<elite.size();i++) {
                elite.set(i, (I) elite.get(i).createClone());
            }
            selection = selection.subList(elitism, selection.size());
        }
        Collections.shuffle(selection,NumberUtils.rnd);

        int mateIndex = -1;
        for(int i = 0;i<selection.size();i++) {
            if(NumberUtils.rnd.nextDouble() < pCross) {
                if(mateIndex == -1) {
                    mateIndex = i;
                } else {
                    Collection<I> children = crossOver.breed(selection.get(i), selection.get(mateIndex));
                    if(children.size()!= 2) {
                        throw new UnsupportedOperationException("CrossOver for this generation manager must return 2 children exactly");
                    }
                    Iterator<I> childrenIterator = children.iterator();
                    selection.set(i, childrenIterator.next());
                    selection.set(mateIndex, childrenIterator.next());
                    mateIndex = -1;
                }
            }

        }

        for (I newIndividual : selection) {
            mutator.mutate(newIndividual);
            newIndividual.setFitness(evaluator.evaluate(newIndividual));
        }

        if(elitism > 0) {
            selection.addAll(elite);
        }
        return selection;
    }

    public double getpCross() {
        return pCross;
    }

    public void setpCross(double pCross) {
        this.pCross = pCross;
    }

    public int getElitism() {
        return elitism;
    }

    public void setElitism(int elitism) {
        this.elitism = elitism;
    }
}
