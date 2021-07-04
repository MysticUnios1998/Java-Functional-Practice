package co.edu.platzi.defaults;

@FunctionalInterface
public interface DefaultOne {
	
	void sayHi();
	
	default void speak() {
		sayHi();
	}

}
