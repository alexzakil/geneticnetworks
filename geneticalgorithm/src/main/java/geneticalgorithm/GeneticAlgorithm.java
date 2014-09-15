package geneticalgorithm;

import geneticalgorithm.evaluation.FitnessFunction;
import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.individual.Individual;
import geneticalgorithm.individual.IndividualFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An algorithm to find a solution to a problem using evolutionary methods.
 * A population of individuals (each representing a solution) is generated (usually randomly) and then pass through
 * several generations. Individuals have fitness that measures how good a solution they represent.
 * Each generation should create a population where the individuals represent better solutions.
 * The algorithm stops either after a fixed number of generations or after an individual in the population has fitness
 * that is higher than the required fitness.
 * The algorithm returns the final population. The last individual of this population represents the best solution
 *
 * @param <I>
 */
public class GeneticAlgorithm <I extends Individual>{

    static final Logger logger = Logger.getLogger(GeneticAlgorithm.class);

    List<I> population;

    private IndividualFactory<I> individualFactory;
    private FitnessFunction<I> fitnessFunction;
    private GenerationManager<I> generationManager;

    public GeneticAlgorithm() {
    }

    /**
     * Creates a new genetic algorithm
     * @param individualFactory a factory to create the initial population
     * @param fitnessFunction a fitness funciton to evaluate individuals
     * @param generationManager the generation manager
     */
    public GeneticAlgorithm(IndividualFactory<I> individualFactory, FitnessFunction<I> fitnessFunction, GenerationManager<I> generationManager) {
        this.individualFactory = individualFactory;
        this.fitnessFunction = fitnessFunction;
        this.generationManager = generationManager;
    }

    public IndividualFactory<I> getIndividualFactory() {
        return individualFactory;
    }

    /**
     *
     * @param individualFactory a factory to create the initial population
     */
    public void setIndividualFactory(IndividualFactory<I> individualFactory) {
        this.individualFactory = individualFactory;
    }

    public FitnessFunction<I> getFitnessFunction() {
        return fitnessFunction;
    }

    /**
     *  a fitness funciton to evaluate individuals
     * @param fitnessFunction
     */
    public void setFitnessFunction(FitnessFunction<I> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public GenerationManager<I> getGenerationManager() {
        return generationManager;
    }

    /**
     * the generation manager
     * @param generationManager
     */
    public void setGenerationManager(GenerationManager<I> generationManager) {
        this.generationManager = generationManager;
    }


    /**
     * Runs the genetic algorithm
     * @param populationSize poplation size to use. A large population will usually give better results, but the algorithm will take more time.
     * @param maxGenerations maximum number of generations. The algorithm will stop once this generation is reached.
     * @param requiredFitness wanted fitness of the algorithm. The algorithm will stop once an individual has this fitness.
     * @return the final population. The last individual of this population represents the best solution
     */
    public List<I> runAlgorithm(int populationSize, int maxGenerations, double requiredFitness) {
        population = new ArrayList<>(populationSize);
        for(int i =0; i<populationSize;i++){


            I individual = individualFactory.createIndividual();
            individual.setFitness(fitnessFunction.getFitness(individual));
            population.add(individual);
        }

        int generation = 0;

        do {
            population = generationManager.nextGeneration(population, fitnessFunction);
            Collections.sort(population);
            generation++;
            if(generation % 100 == 0){
                logger.debug("Generation: " + generation + " Fitness: " + population.get(population.size()-1).getFitness());
            }
        }while (generation < maxGenerations && population.get(population.size()-1).getFitness() < requiredFitness);
        return population;
    }


}
