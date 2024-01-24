package Graph;

public class main {

	public static void main(String[] args) {
		
		new Thread(new TestCases("map01", 500, 500)).start();
		new Thread(new TestCases("map02", 500, 500)).start();
		new Thread(new TestCases("map03", 500, 500)).start();
		new Thread(new TestCases("map04", 500, 500)).start();
		new Thread(new TestCases("map05", 500, 500)).start();
		new Thread(new TestCases("map06", 500, 500)).start();
		new Thread(new TestCases("map07", 500, 500)).start();
		new Thread(new TestCases("map08", 500, 500)).start();
		new Thread(new TestCases("map09", 500, 500)).start();
		new Thread(new TestCases("map10", 500, 500)).start();
		
		
		/*
		new Thread(new TestCases("tokyo", 1000, 1000)).start();
		new Thread(new TestCases("vatican", 1000, 1000)).start();
		new Thread(new TestCases("triumph", 1000, 1000)).start();
		new Thread(new TestCases("pisa", 1000, 1000)).start();*/

	}

}
