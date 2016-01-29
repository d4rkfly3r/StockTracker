package us.jfreedman.software.stocktracker;

import java.util.Arrays;

class Utils {

    private static final String[] prefixes = {"k", "M", "B"};

    public static long convertStrNumToNum(String stringNum) {
        long result = 0L;
        float tVal = Float.parseFloat(stringNum.substring(0, stringNum.length() - 1));
        // Retrieve the prefix index used
        int prefixIndex = Arrays.asList(prefixes).indexOf(stringNum.substring(stringNum.length() - 1)) + 1;
        // Multiply the input to the appropriate prefix used
        if (prefixIndex > 0)
            result = (long) (tVal * Math.pow(10, prefixIndex * 3));
        return result;

    }

}
