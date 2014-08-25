package geneticalgorithm;

import geneticalgorithm.evaluator.Evaluator;
import geneticalgorithm.generation.GenerationManager;
import geneticalgorithm.individual.Individual;
import geneticalgorithm.individual.IndividualFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneticAlgorithm <I extends Individual>{

    static final Logger logger = Logger.getLogger(GeneticAlgorithm.class);

    List<I> population;

    private IndividualFactory<I> individualFactory;
    private Evaluator<I> evaluator;
    private GenerationManager<I> generationManager;

    public GeneticAlgorithm() {
    }

    public GeneticAlgorithm(IndividualFactory<I> individualFactory, Evaluator<I> evaluator, GenerationManager<I> generationManager) {
        this.individualFactory = individualFactory;
        this.evaluator = evaluator;
        this.generationManager = generationManager;
    }

    public IndividualFactory<I> getIndividualFactory() {
        return individualFactory;
    }

    public void setIndividualFactory(IndividualFactory<I> individualFactory) {
        this.individualFactory = individualFactory;
    }

    public Evaluator<I> getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator<I> evaluator) {
        this.evaluator = evaluator;
    }

    public GenerationManager<I> getGenerationManager() {
        return generationManager;
    }

    public void setGenerationManager(GenerationManager<I> generationManager) {
        this.generationManager = generationManager;
    }



    public List<I> runAlgorithm(int populationSize, int maxGenerations, double requiredFitness) {
        population = new ArrayList<>(populationSize);
        for(int i =0; i<populationSize;i++){


            I individual = individualFactory.createIndividual();
            individual.setFitness(evaluator.evaluate(individual));
            population.add(individual);
        }

        int generation = 0;

        do {
            population = generationManager.nextGeneration(population,evaluator);
            Collections.sort(population);
            generation++;
            if(generation % 100 == 0){
                logger.info("Generation: " + generation + " Fitness: " + population.get(population.size()-1).getFitness());
            }
        }while (generation < maxGenerations && population.get(population.size()-1).getFitness() < requiredFitness);
        return population;
    }


}
