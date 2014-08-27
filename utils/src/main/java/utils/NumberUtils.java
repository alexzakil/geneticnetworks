package utils;

import java.util.BitSet;
import java.util.Random;

public class NumberUtils {

    public static double extractNumber(BitSet bitSet) {

        int value = 0;
        for (int i = 0; i < bitSet.length(); ++i) {
            value += bitSet.get(i) ? (1L << i) : 0L;
        }

        return value;

    }

    public static Random rnd = new Random();



}