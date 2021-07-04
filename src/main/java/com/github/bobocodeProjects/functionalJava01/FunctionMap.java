package com.github.bobocodeProjects.functionalJava01;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FunctionMap <T,R>{
	
	private Map< String, Function<T,R> > functions;
	
	public FunctionMap () {
		functions = new HashMap< String, Function<T,R> >();
	}
	
	public void addFunction(String name, Function<T,R> newFunc) {
		functions.put(name, newFunc);
	}
	
	public R executeFunction(String name, T param) {
		return functions.get(name).apply(param);
	}

}
