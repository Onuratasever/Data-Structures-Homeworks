package homework2;

import java.util.ArrayList;

public class deneme {
	public static void main(String[] args) {
		
		ArrayList<dog> asd = new ArrayList<dog>();
		
		//asd.add(new animal("animalke"));
		asd.add(new dog("dogke"));
		//a(asd);
	}
	
	public static void a(ArrayList<animal> b)
	{
		
		
		b.get(0).say();
		//b.get(1).say();
	}

}

class animal{
	public String name;
	
	animal(String name)
	{
		this.name = name;
	}
	
	void say()
	{
		System.out.println("animal is called");
	}
}

class dog extends animal{
	
	dog(String name)
	{
		super(name);
	}
	
	void say()
	{
		System.out.println("dog is called");
	}
}
