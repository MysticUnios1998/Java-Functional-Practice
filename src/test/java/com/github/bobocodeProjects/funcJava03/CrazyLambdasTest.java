package com.github.bobocodeProjects.funcJava03;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.function.IntSupplier;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.bobocodeProjects.functionalJava03.CrazyLambdas;

public class CrazyLambdasTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

//	@Test
//	public void test() {
//		try {
//			
//		} catch (Exception ex) {
//			fail("An Exception has ocurred: "+ex.getLocalizedMessage());
//		}
//	}
	
	@Test
	public void shouldReturnHelloSupplier() {
		try {
			assertEquals("Hello", CrazyLambdas.helloSupplier().get());
		} catch (Exception ex) {
			fail("An Exception has ocurred: "+ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void shouldReturnEmptyStringPredicate() {
		try {
			assertTrue(CrazyLambdas.isEmptyPredicate().test(""));
			assertFalse(CrazyLambdas.isEmptyPredicate().test("Hello World!"));
		} catch (Exception ex) {
			fail("An Exception has ocurred: "+ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void shouldReturnStringMultiplierBifunction() {
		try {
			assertEquals("HelloHello", CrazyLambdas.stringMultiplier().apply("Hello", 2));
			assertEquals("HelloHelloHelloHello", CrazyLambdas.stringMultiplier().apply("HelloHello", 2));
			assertEquals("", CrazyLambdas.stringMultiplier().apply("Hello", 0));
			assertEquals("", CrazyLambdas.stringMultiplier().apply("", 0));
			assertEquals("", CrazyLambdas.stringMultiplier().apply("", 10));
			assertThrows(IllegalArgumentException.class, () -> CrazyLambdas.stringMultiplier().apply("", -1));
			assertThrows(IllegalArgumentException.class, () -> CrazyLambdas.stringMultiplier().apply("Hello", -10));
		} catch (Exception ex) {
			fail("An Exception has ocurred: "+ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void shouldReturnDoolarStringFunction() {
		try {
			assertEquals("$0", CrazyLambdas.toDollarStringFunction().apply(new BigDecimal(0)));
			assertEquals("$100000", CrazyLambdas.toDollarStringFunction().apply(new BigDecimal(100000)));
			assertEquals("$240000000", CrazyLambdas.toDollarStringFunction().apply(new BigDecimal(240000000)));
			
			assertThrows(IllegalArgumentException.class, () -> CrazyLambdas.toDollarStringFunction().apply(new BigDecimal(-100000)));
			assertThrows(IllegalArgumentException.class, () -> CrazyLambdas.toDollarStringFunction().apply(new BigDecimal(-1)));
		} catch (Exception ex) {
			fail("An Exception has ocurred: "+ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void shouldReturnStringLengthInRangePredicate() {
		try {
			assertTrue(CrazyLambdas.lengthInRangePredicate(1,  10).test("Hello"));
			assertTrue(CrazyLambdas.lengthInRangePredicate(1,  10).test("HelloHello"));
			assertTrue(CrazyLambdas.lengthInRangePredicate(1,  10).test("H"));
			assertFalse(CrazyLambdas.lengthInRangePredicate(1,  10).test("Hello World!"));
			assertFalse(CrazyLambdas.lengthInRangePredicate(1,  10).test(""));

			assertThrows(IllegalArgumentException.class, () -> CrazyLambdas.lengthInRangePredicate(-1,100).test(""));
			assertThrows(IllegalArgumentException.class, () -> CrazyLambdas.lengthInRangePredicate(10, 9).test(""));
			
			assertThrows(NullPointerException.class, () -> CrazyLambdas.lengthInRangePredicate(0, 1000).test(null));
			
		} catch (Exception ex) {
			fail("An Exception has ocurred: "+ex.getLocalizedMessage());
		}
	}
	
//	@Test
//	public void shouldReturnRandomIntegerSupplier() {
//		try {
//			IntSupplier supp = Mockito.mock(CrazyLambdas.randomIntSupplier().getClass());
//			Mockito.when(supp.getAsInt()).thenReturn(5);
//			
//			assertEquals(5, supp.getAsInt());
//			
//		} catch (Exception ex) {
//			fail("An Exception has ocurred: "+ex.getLocalizedMessage());
//		}
//	}
	
	@Test
	public void shouldReturnAnIntegerSquareOperator() {
		try {
			assertEquals(25, CrazyLambdas.intSquareOperation().applyAsInt(5));
			assertEquals(25, CrazyLambdas.intSquareOperation().applyAsInt(-5));
			assertEquals(0, CrazyLambdas.intSquareOperation().applyAsInt(0));
		} catch (Exception ex) {
			fail("An Exception has ocurred: "+ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void shouldReturnALongBinarySumOperator() {
		try {
			assertEquals(10, CrazyLambdas.longSumOperation().applyAsLong(6, 4));
			assertEquals(10000000, CrazyLambdas.longSumOperation().applyAsLong(6000000, 4000000));
			assertEquals(0, CrazyLambdas.longSumOperation().applyAsLong(70000000, -70000000));
		} catch (Exception ex) {
			fail("An Exception has ocurred: "+ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void testshouldReturnStringToIntFunction() {
		try {
			assertEquals(10, CrazyLambdas.stringToIntConverter().applyAsInt("10"));
			assertEquals(245000000, CrazyLambdas.stringToIntConverter().applyAsInt("245000000"));
			assertThrows(NumberFormatException.class, () -> CrazyLambdas.stringToIntConverter().applyAsInt("hello"));
			assertThrows(NumberFormatException.class, () -> CrazyLambdas.stringToIntConverter().applyAsInt(""));
			assertThrows(NumberFormatException.class, () -> CrazyLambdas.stringToIntConverter().applyAsInt(null));
		} catch (Exception ex) {
			fail("An Exception has ocurred: "+ex.getLocalizedMessage());
		}
	}
	
	@Test
	public void shouldReturnSupplierWithIntOperatorInside() {
		try {
			assertEquals(10, CrazyLambdas.nMultiplyFunctionSupplier(5).get().applyAsInt(2));
			assertEquals(5, CrazyLambdas.nMultiplyFunctionSupplier(5).get().applyAsInt(1));
			assertEquals(-4, CrazyLambdas.nMultiplyFunctionSupplier(-2).get().applyAsInt(2));
			assertEquals(0, CrazyLambdas.nMultiplyFunctionSupplier(5).get().applyAsInt(0));
			assertEquals(0, CrazyLambdas.nMultiplyFunctionSupplier(0).get().applyAsInt(2));
		} catch (Exception ex) {
			fail("An Exception has ocurred: "+ex.getLocalizedMessage());
		}
	}
	

}
