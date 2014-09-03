package geneticalgorithm.generation;

import geneticalgorithm.crossover.CrossOver;
import geneticalgorithm.individual.Individual;
import geneticalgorithm.mutation.Mutator;
import geneticalgorithm.selection.Selector;

/**
 * Most generation managers would have a selector algorithm, a crossover algorithm and a mutator algorithm.
 * This class could be their superclass.
 * @param <I>
 */
public abstract class DefaultGenerationManager<I extends Individual> implements GenerationManager<I> {

    Selector selector;
    CrossOver<I> crossOver;
    Mutator<I> mutator;

    public DefaultGenerationManager() {
    }

    public DefaultGenerationManager(Selector selector, CrossOver<I> crossOver, Mutator<I> mutator) {
       this.selector = selector;
        this.crossOver = crossOver;
        this.mutator = mutator;

    }


    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public CrossOver<I> getCrossOver() {
        return crossOver;
    }

    public void setCrossOver(CrossOver<I> crossOver) {
        this.crossOver = crossOver;
    }

    public Mutator<I> getMutator() {
        return mutator;
    }

    public void setMutator(Mutator<I> mutator) {
        this.mutator = mutator;
    }


}
