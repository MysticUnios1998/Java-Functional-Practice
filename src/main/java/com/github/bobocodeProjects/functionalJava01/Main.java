package com.github.bobocodeProjects.functionalJava01;

public class Main {

	public static void main(String... args) {
		var funcMap = new FunctionMap<Integer, Integer>();
		
		//absolute value function
		funcMap.addFunction("abs", x -> (x >= 0) ? x : -x);
		
		//sign function -> return 1 if positive or -1 if negative
		funcMap.addFunction("sgn", x -> (x>=0) ? 1:-1);
		
		//increment value function
		funcMap.addFunction("inc", x -> ++x);
		
		//decrement value function
		funcMap.addFunction("dec", x -> --x);
		
		//square function
		funcMap.addFunction("sq", x -> x*x);
		
		
		System.out.println(funcMap.executeFunction("abs", -12));
		System.out.println(funcMap.executeFunction("abs", 12));
		
		System.out.println(funcMap.executeFunction("sgn", -12));
		System.out.println(funcMap.executeFunction("sgn", 12));
		
		System.out.println(funcMap.executeFunction("inc", -12));
		System.out.println(funcMap.executeFunction("inc", 12));
		
		System.out.println(funcMap.executeFunction("dec", -12));
		System.out.println(funcMap.executeFunction("dec", 12));
		
		System.out.println(funcMap.executeFunction("sq", -12));
		System.out.println(funcMap.executeFunction("sq", 12));
	}
	
}
