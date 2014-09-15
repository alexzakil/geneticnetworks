package geneticalgorithm.selection;

import geneticalgorithm.individual.Individual;
import utils.NumberUtils;

import java.util.*;

/**
 * A selector that returns a mating list equal to the size of the population.
 * Each slot in the list is drawn between all individuals of the population so that the change to win
 * is proportional to the rank of the individual in the fitness scale.
 *
 * It is probable that individuals with high fitness
 * would appear more than once in the mating list, while individuals with low fitness would not appear at all
 */
public class RankRouletteSelector implements Selector {
    @Override
    public <T extends Individual> List<T> select(List<T> population) {
        Collections.sort(population);

        int size = population.size();

        double[] cumulativeRank = new double[size];
        cumulativeRank[0] = 1;

        int i= 0;
        for (T individual : population) {
            if(i>0) {
                cumulativeRank[i] = cumulativeRank[i - 1] + i + 1;
            }
            i++;
        }
        double sumRank = cumulativeRank[cumulativeRank.length-1];

        List<T> selection = new ArrayList<>(size);
        for (i = 0; i < size; i++)
        {
            double randomRank = NumberUtils.rnd.nextDouble()*sumRank;
            int j = 0;
            Iterator<T> iterator = population.iterator();
            T selected = iterator.next();
            while (randomRank >= cumulativeRank[j] && j< size) {
                j++;
                selected = iterator.next();
            }
            selection.add(selected);
        }
        return selection;
    }
}
