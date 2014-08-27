package geneticalgorithm;


import geneticalgorithm.evaluation.BitsetEvaluator;
import geneticalgorithm.individual.BitSetIndividual;

public class StringMatchingEvaluator implements BitsetEvaluator<BitSetIndividual> {

    String wanted;

    public StringMatchingEvaluator(String wanted) {
        this.wanted = wanted;
    }


    @Override
    public double evaluate(BitSetIndividual individual) {
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
