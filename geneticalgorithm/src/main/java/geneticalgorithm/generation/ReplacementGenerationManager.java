package geneticalgorithm.generation;

import geneticalgorithm.crossover.CrossOver;
import geneticalgorithm.evaluation.FitnessFunction;
import geneticalgorithm.individual.Individual;
import geneticalgorithm.mutation.Mutator;
import geneticalgorithm.selection.Selector;
import utils.NumberUtils;

import java.util.*;

/**
 * In this algorithm, the population size stays the same after each generation.
 * In this algorithm, children replace the individuals of the old generation.
 * In the elitism defined is larger than 0, then that number of the most fit individuals of the old generation are
 * moved as they are to the new generation.
 * @param <I>
 */
public class ReplacementGenerationManager<I extends Individual> extends DefaultGenerationManager<I> {

    int elitism=0;
    double pCross;


    public ReplacementGenerationManager() {
    }

    /**
     *
     * @param selector the algorithm to select individuals that should mate
     * @param crossOver the algorithm to create new individuals from mating individuals. Must always return two children.
     * @param mutator the algorithm to mutete new individuals
     * @param pCross the probability that an individual selected for mating would be allowed to mate.
     */
    public ReplacementGenerationManager(Selector selector, CrossOver<I> crossOver, Mutator<I> mutator, double pCross) {
        super(selector, crossOver, mutator);
        this.pCross = pCross;
    }

    /**
     *
     * @param selector the algorithm to select individuals that should mate
     * @param crossOver the algorithm to create new individuals from mating individuals
     * @param mutator the algorithm to mutete new individuals
     * @param pCross the probability that an individual selected for mating would be allowed to mate.
     * @param elitism the number of most fit individuals that should be retained from the old population to the new.
     */
    public ReplacementGenerationManager(Selector selector, CrossOver<I> crossOver, Mutator<I> mutator, double pCross,int elitism) {
        super(selector, crossOver, mutator);
        this.elitism = elitism;
        this.pCross = pCross;
    }

    @Override
    public List<I> nextGeneration(List<I> oldGeneration,FitnessFunction<I> fitnessFunction) {
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
            newIndividual.setFitness(fitnessFunction.getFitness(newIndividual));
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
