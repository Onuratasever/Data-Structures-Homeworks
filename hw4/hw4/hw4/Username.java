package hw4;

/**
 * Username class
 * It keeps user name and it controls validity
 * @author Onur
 *
 */
public class Username {
	
	private String username;
	
	/**
	 * It assigns username
	 * @param username
	 */
	Username(String username)
	{
		this.username = username;
	}
	
	/**
	 * It controls recusively
	 * @param username
	 * @return true if it is valid, false if it is null, empty or there is something different from letter
	 */
	public boolean checkIfValidUsername(String username)
	{
		if(username == null)
		{
			System.out.println("String is null");
			return false;
		}
		else if(username.isEmpty())
		{
			System.err.println("String is empty");
			return false;
		}
		//System.out.println("Gelen string: " + username + "\nFirst index to check: " + username.charAt(0));
		if(!Character.isLetter(username.charAt(0)))
		{
			System.out.println("NonLetter: " + username.charAt(0));
			return false;
		}
		if(username.length() != 1)
			return checkIfValidUsername(username.substring(1));
		return true;
	}
	
	/**
	 * It get username
	 * @return username
	 */
	public String getUsername()
	{
		return username;
	}
}
