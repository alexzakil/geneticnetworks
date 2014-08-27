package geneticalgorithm.selection;

import geneticalgorithm.individual.Individual;
import utils.NumberUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class RouletteSelector implements Selector {
    @Override
    public <T extends Individual> List<T> select(Collection<T> population) {

        int size = population.size();

        double sumFitness = 0;
        for (T individual : population) {
            sumFitness+=individual.getFitness();
        }
        double[] cumulativeFitnesses = new double[size];
        cumulativeFitnesses[0] = population.iterator().next().getFitness() / sumFitness;

        int i= 0;
        for (T individual : population) {
            if(i>0) {
                cumulativeFitnesses[i] = cumulativeFitnesses[i - 1] + individual.getFitness()/sumFitness;
            }
            i++;
        }


        List<T> selection = new ArrayList<>(size);
        for (i = 0; i < size; i++)
        {
            double randomFitness = NumberUtils.rnd.nextDouble();
            int j = 0;
            Iterator<T> iterator = population.iterator();
            T selected = iterator.next();
            while (randomFitness >= cumulativeFitnesses[j] && j< size) {
                j++;
                selected = iterator.next();
            }
            selection.add(selected);
        }
        return selection;
    }
}
