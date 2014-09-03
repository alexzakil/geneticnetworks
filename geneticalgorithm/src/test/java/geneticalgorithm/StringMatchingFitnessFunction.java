package geneticalgorithm;


import geneticalgorithm.evaluation.FitnessFunction;
import geneticalgorithm.individual.BinaryIndividual;

public class StringMatchingFitnessFunction implements FitnessFunction<BinaryIndividual> {

    String wanted;

    public StringMatchingFitnessFunction(String wanted) {
        this.wanted = wanted;
    }


    @Override
    public double getFitness(BinaryIndividual individual) {
        int count = 0;
        String s = new String(individual.getGenome().toByteArray());
        int i =0;
        for (char c : s.toCharArray()) {
            if (c == wanted.charAt(i)) {
                count++;
            }
            i++;
        }
        return count;
    }

    public int getNumberOfBitsPerIndividual() {
        return 8*wanted.length();
    }
}
