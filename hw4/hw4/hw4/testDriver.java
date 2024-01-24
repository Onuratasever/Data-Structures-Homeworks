package hw4;

/**
 * Test driver
 * @author Onur
 *
 */
public class testDriver {
	/**
	 * main method
	 * @param argsa
	 */
	public static void main(String[] args) {
		tester("gizem", "{(abba)cac}", 75);
		
		tester("ahmet", "{(abba)cac}", 75);
		
		tester("ahmet", "{(abba)cac}", 35);
		
		tester("onur", "{(abbo)cac}", 54);

		tester("onur", "[a]bco(cb)a", 54);

		tester("onur", "{(abbo)cac", 54);

		tester("onur", "{(abbocac}", 54);

		tester("onur", "{(abbo)ca}", 54);

		tester("", "{(abbo)cac}", 54);

		tester("onur", "{(oo)}", 54);

		tester("onur", "abbocac", 54);

	}
	
	/**
	 * test method
	 * @param usernamea
	 * @param password1a
	 * @param password2a
	 */
	public static void tester(String username, String password1, int password2)
	{
		int[] denominations = {4, 17, 29};
		
		System.out.println("---------------\nUsername: " + username);
		Username user = new Username(username);
		Password1 pass1 = new Password1(password1);
		Password2 pass2 = new Password2(password2);
		
		if(
				user.checkIfValidUsername(user.getUsername()) &&
				pass1.control(user.getUsername()) &&
				pass2.control(denominations)
			)
		{
			System.out.println("The username and passwords are valid. The door is opening, please wait...");
		}
		
	}
}
