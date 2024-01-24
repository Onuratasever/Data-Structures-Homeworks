package hw4;

/**
 * It keeps password2 which is integer and there are validation functions
 * @author Onur
 *
 */
public class Password2 {
	
	int password2;
	
	/**
	 * It assigns
	 * @param password
	 */
	public Password2(int password)
	{
		this.password2 = password;
	}
	
	/**
	 * It controls whether password is valid
	 * @return true if it is valid else false
	 */
	public boolean isValid()
	{
		if(password2 <= 10 || password2 >=10000)
		{
			System.out.println("Password: " + password2 + " at least: 11 most: 9999");
		}
		return true;
	}
	
	/**
	 * It determines whether sum can be write by summing of denominations
	 * @param password
	 * @param dominations
	 * @param sum result (sum of denominations)
	 * @return true if it can be write, else false
	 */
	public boolean checkPossibilities(int password, int[] dominations, int sum)
	{
		if (sum > password)
		{
			return false;
		}
		
		boolean bool;
		
		if(sum == password)
		{
			return true;
		}
		
		/*return (checkPossibilities(password, dominations, sum + dominations[0]) || 
				checkPossibilities(password, dominations, sum + dominations[1]) ||
				checkPossibilities(password, dominations, sum + dominations[2]));
	*/
		for(int i=0; i<dominations.length; i++)
		{
			bool = checkPossibilities(password, dominations, sum + dominations[i]);
			if(bool)
				return true;
		}
		return false;
	}
	
	/**
	 * It calls checkPossibilities function
	 * @param password2 password of user
	 * @param denominations given denominaitons
	 * @return true if it is exact division else false
	 */
	public boolean isExactDivision(int   password2,   int   []   denominations)
	{
		if(checkPossibilities(password2, denominations, 0))
			return true;
		else
		{
			System.out.println("Password can not create with this denominations");
			return false;
		}
	}
	
	/**
	 * It calls all validation functions and return their values
	 * @param denominations 
	 * @return return value of validation methods
	 */
	public boolean control(int[] denominations)
	{
		return(
				isValid() &&
				isExactDivision(password2, denominations)
				);
	}
}
