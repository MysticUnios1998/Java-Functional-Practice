package co.edu.platzi.predicate;

import java.util.function.Function;
import java.util.function.Predicate;

public class PredicateExample {

	public static void main(String[] args) {
		
		Function<Integer, Boolean> isPrime = p -> {
			for (int i=2; i<p/2; i++) {
				if (p % i == 0) return false;
			}
			return true;
		};
		
		System.out.println(isPrime.apply(5));
		System.out.println(isPrime.apply(15));
		System.out.println(isPrime.apply(12434125));
		
		/*
		 * Un Predicate es la clase funcional que se comporta como una función que retorna siempre un booleano.
		 */
		Predicate<Integer> predicateIsPrime = p -> {
			for (int i=2; i<Math.sqrt(p); i++) {
				if (p%i == 0) return false;
			}
			return true;
		};
		
		
		System.out.println(predicateIsPrime.test(5));
		System.out.println(predicateIsPrime.test(15));
		System.out.println(predicateIsPrime.test(12434125));
		
	}

}
