package homework2_2;

public class Test {

	public static void main(String[] args) {
		LDLinkedList<String> a = new LDLinkedList<>();
		
		a.add("Onur");
		a.add(new String("Deneme"));
		a.add(0,"Bilgisayar");
		a.add(0,"zort");
		a.add(1,"bire");
		
		System.out.println("------------------\nRemovedan önce\n");
		for(int i =0 ; i<a.size(); i++)
		{
			System.out.println(a.get(i));
		}
		
		a.remove(1);
		System.out.println("------------------\nİlk removedan sonra\n");
		for(int i =0 ; i<a.size(); i++)
		{
			System.out.println(a.get(i));
		}
		a.add(2,"hello");
		
		
		//a.remove(3);// laziliy deleted olanları  sayıyo onları atlamak lazım
		System.out.println("------------------\nİkinci removedan sonra\n");
		for(int i =0 ; i<a.size(); i++)
		{
			System.out.println(a.get(i));
		}
		/*
		a.set(2, "yakışıklı");
		System.out.println("------------------\nSetden sonra\n");
		for(int i =0 ; i<a.size(); i++)
		{
			System.out.println(a.get(i));
		}*/
	}

}
