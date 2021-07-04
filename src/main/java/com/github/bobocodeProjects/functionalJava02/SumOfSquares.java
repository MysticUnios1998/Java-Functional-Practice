package com.github.bobocodeProjects.functionalJava02;

import java.util.stream.IntStream;

import com.github.bobocodeProjects.exception.InvalidRangeException;

/**
 * This class allow to calculate a sum of squares of integer number in a certain range. It was implemented using
 * OO approach. Your job is to refactor it using functional approach. E.g. avoid using mutable variables
 */
public class SumOfSquares {

    /**
     * This method calculates the sum of squares of integer in the range
     *
     * @param startInclusive first element in range
     * @param endInclusive   last element in range
     * @return the sum of squares of each element in the range
     * @throws InvalidRangeException if end is lesser than start
     */
    public static int calculateSumOfSquaresInRange(int startInclusive, int endInclusive) {
        if (endInclusive < startInclusive) throw new InvalidRangeException();

        int sumOfSquares = IntStream.rangeClosed(startInclusive, endInclusive)
        							.map(num -> num*num)
        							.sum();
        
        return sumOfSquares;
    }
}
