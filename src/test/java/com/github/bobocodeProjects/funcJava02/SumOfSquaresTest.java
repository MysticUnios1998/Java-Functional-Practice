package com.github.bobocodeProjects.funcJava02;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.bobocodeProjects.exception.InvalidRangeException;
import com.github.bobocodeProjects.functionalJava02.SumOfSquares;

public class SumOfSquaresTest {

	@Test
	public void shouldCalculateSumOfSquares() {
		try {
			assertEquals(14, SumOfSquares.calculateSumOfSquaresInRange(1, 3));
			assertEquals(14, SumOfSquares.calculateSumOfSquaresInRange(0, 3));
			assertEquals(14, SumOfSquares.calculateSumOfSquaresInRange(-3, 0));
			assertEquals(SumOfSquares.calculateSumOfSquaresInRange(1, 3), SumOfSquares.calculateSumOfSquaresInRange(-3,-1));
		} catch (Exception ex) {
			fail("An exception has ocurred: "+ ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void shouldCalculateSquareIfRangeIsFixedPoint() {
		try {
			assertEquals(Math.pow(2, 2), SumOfSquares.calculateSumOfSquaresInRange(2, 2), 0);
			assertEquals(Math.pow(-2, 2), SumOfSquares.calculateSumOfSquaresInRange(-2, -2), 0);
			assertEquals(Math.pow(0, 2), SumOfSquares.calculateSumOfSquaresInRange(0, 0), 0);
		} catch (Exception ex) {
			fail("An exception has ocurred: "+ ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void shouldThrowInvalidRangeException() {
		try {
			assertThrows(InvalidRangeException.class, () -> {
						SumOfSquares.calculateSumOfSquaresInRange(0, -1);
					});
		} catch (Exception ex) {
			fail("An exception has ocurred: "+ ex.getLocalizedMessage());
		}
	}

}
