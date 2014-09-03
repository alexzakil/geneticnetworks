package geneticalgorithm.generation;

import geneticalgorithm.crossover.CrossOver;

import geneticalgorithm.evaluation.FitnessFunction;
import geneticalgorithm.individual.Individual;
import geneticalgorithm.mutation.Mutator;
import geneticalgorithm.selection.Selector;
import utils.NumberUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * In this algorithm, the population size stays the same after each generation.
 * A mating list is selected from the old generation.
 * Members of this list mate with other members of the list, and their children are mutated.
 * The children are added to the population.
 * Because the population size should stay the same, the least fit individuals are removed until the size
 * of the population reaches the size of the original population.
 * @param <I>
 */
public class AdditionGenerationManager<I extends Individual> extends DefaultGenerationManager<I> {


    double pCross;


    public AdditionGenerationManager() {
    }

    /**
     *
     * @param selector the algorithm to select individuals that should mate
     * @param crossOver the algorithm to create new individuals from mating individuals
     * @param mutator the algorithm to mutete new individuals
     * @param pCross the probability that an individual selected for mating would be allowed to mate.
     */
    public AdditionGenerationManager(Selector selector, CrossOver<I> crossOver, Mutator<I> mutator, double pCross) {
        super(selector, crossOver, mutator);
        this.pCross = pCross;
    }

    @Override
    public List<I> nextGeneration(List<I> oldGeneration,FitnessFunction<I> fitnessFunction) {
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
            newIndividual.setFitness(fitnessFunction.getFitness(newIndividual));
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
