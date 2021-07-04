package co.edu.platzi.defaults;

@FunctionalInterface
public interface DefaultTwo {

	void sayBye();
	
	default void speak() {
		sayBye();
	}
	
}
