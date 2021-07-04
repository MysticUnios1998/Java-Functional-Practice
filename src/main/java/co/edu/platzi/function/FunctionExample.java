package co.edu.platzi.function;

import java.util.function.Function;

public class FunctionExample {
	
	public static void main(String[] args) {
		// Definición con operador lambda '->'
		Function<Integer, Boolean> isOdd = x -> x % 2 == 0;
		System.out.println(isOdd.apply(5));
		System.out.println(isOdd.apply(50));
		
		
		//Definición con clases funcionales
		var reducedIsOdd = new Function<Integer, Boolean>(){
			
			@Override
			public Boolean apply(Integer x) {
				return x % 2 == 0;
			}
			
		};
		
		System.out.println(reducedIsOdd.apply(5));
		System.out.println(reducedIsOdd.apply(50));
		
		//El código abajo no funcionará porque 'var' necesita algo con tipo definido para inferir el tipo de la variable. Lmabdas por definición, no poseen tipo definido.
		//var completeFunctionalIsOdd = x -> x % 2 == 0;
		
	}
	

}
