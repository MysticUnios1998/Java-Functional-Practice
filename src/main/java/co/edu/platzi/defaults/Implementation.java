package co.edu.platzi.defaults;

/*
 * This class is the implementation part of the interfaces defined. Both DefaultOnw and DefaultTwo have one default method
 * called 'speak()' which cause the following compiltation error: 
 * 'Duplicate default methods named speak with the parameters () and () are inherited from the types DefaultTwo and DefaultOne'
 * 
 * That is would happend if multi-inheritance was allowed in java, but this can be fixed overriding the 'speak()' method completely; due to the fact that this error
 * was caused by interfaces, which are a more abstract definition of behavior only.
 */
public class Implementation implements DefaultOne, DefaultTwo {

	@Override
	public void sayBye() {
		System.out.println("Bye!");
	}

	@Override
	public void sayHi() {
		System.out.println("Hello!");
	}

	@Override
	public void speak() {
		
		//This is the way to call method defined in a 'parent interface'
		DefaultOne.super.speak();
		DefaultTwo.super.speak();
	}

}
