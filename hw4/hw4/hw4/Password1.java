package hw4;

import java.util.Stack;

/**
 * It keeps password and there are validation functions.
 * @author Onur
 *
 */
public class Password1 {

	String password1;
	
	/**
	 * It assigns password
	 * @param password
	 */
	public Password1(String password)
	{
		this.password1 = password;
	}
	
	/**
	 * It checks whether password1 contains at least one letter from username by using stacks
	 * @param len_username length of username
	 * @param len_password length of password
	 * @param search the stack that we research on it
	 * @param target stack that we compare with search
	 * @param temp temp stack
	 * @return It returns true if it contains, else false
	 */
	public boolean checkUsernamePassword(int len_username, int len_password, Stack<Character> search,
			Stack<Character> target, Stack<Character> temp)
	{
		if(len_username == 0)
		{
			System.out.println("Password does not contain any letter of username");
			return false;
		}
		for(int i=0; i<len_password; i++)
		{
			char control = target.pop();
			if(control == search.peek())
				return true;
			else
				temp.push(control);
		}
		search.pop();
		return checkUsernamePassword(len_username - 1, len_password, search, temp, target);
	}
	
	/**
	 * It checks whether password1 contains at least one letter from username
	 * @param username
	 * @param password1
	 * @return If it contains it return true else false
	 */
	public boolean containsUsernameSpirit(String username, String password1)
	{
		Stack<Character> username_stack = new Stack<Character>();
		Stack<Character> password_stack = new Stack<Character>();
		Stack<Character> temp_stack = new Stack<Character>();
		
		int username_len=0, password_len=0;
		for(char c: username.toCharArray())
		{
			if(Character.isLetter(c))
			{
				//System.out.println("Usernameden pushlanan: " + c);
				username_stack.push(c);
				username_len++;
			}
		}
		
		for(char c: password1.toCharArray())
		{
			if(Character.isLetter(c))
			{
				//System.out.println("Passwordden pushlanan: " + c);
				password_stack.push(c);
				password_len++;
			}
		}
		
		return checkUsernamePassword(username_len, password_len, username_stack, password_stack, temp_stack);
	}
	
	/**
	 * It checks whether control is open bracket
	 * @param control
	 * @return It returns true if it is open bracket, else false
	 */
	public boolean isOpen(char control)
	{
		switch (control)
		{
			case '(':
				return true;
			case '[':
				return true;
			case '{':
				return true;
			default:
				return false;
		}
	}
	
	
	/**
	 * It checks whether control is closed bracket
	 * @param control
	 * @return It returns tru if it is closed bracket, else false
	 */
	public boolean isClosed(char control)
	{
		switch (control)
		{
			case ')':
				return true;
			case ']':
				return true;
			case '}':
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * It compares two char
	 * @param compare1
	 * @param compare2
	 * @return If one of them open and other is closed it returns true else false
	 */
	public boolean isMatch(char compare1, char compare2)
	{
		if(compare1 == '(' && compare2 == ')')
			return true;
		else if(compare1 == '[' && compare2 == ']')
				return true;
		else if(compare1 == '{' && compare2 == '}')
				return true;
		return false;
	}
	
	/**
	 * checks if each open brackets has a corresponding closed brackets
	 * @param password1
	 * @return true if each open brackets has a corresponding closed brackets else false
	 */
	public boolean isBalancedPassword(String password1)
	{
		Stack<Character> characters = new Stack<Character>();
		
		for(int i=0; i<password1.length(); i++)
		{
			if(isOpen(password1.charAt(i)))
			{
				characters.push(password1.charAt(i));
			}
			else if(isClosed(password1.charAt(i)))
			{
				if(characters.empty())
				{
					System.out.println("There is not enough open bracket");
					return false;
				}
				char compare = characters.pop();
				if(!isMatch(compare, password1.charAt(i)))
				{
					System.out.println("Brackets did not match");
					return false;
				}
			}
		}
		if(characters.empty())
			return true;
		else
		{
			System.out.println("There are unmatched open brackets");
			return false;
		}
	}
	
	/**
	 * It rewrite password without brackets
	 * @param password
	 * @return new string which is cleaned from brackets
	 */
	private String clean_password(String password)
	{
		String new_password = "";
		StringBuilder new_pass = new StringBuilder(new_password);
		for(int i=0; i<password.length(); i++)
		{
			if(Character.isLetter(password.charAt(i)))
			{
				new_pass.append(password.charAt(i));
			}
		}
		new_password = new_pass.toString();
		return new_password;
	}
	
	/**
	 * It swap given indexes
	 * @param password
	 * @param index
	 * @param target
	 * @return swapped string
	 */
	private String swapIndex(String password, int index, int target)
	{
		char[] swap = password.toCharArray();
		
		char temp = swap[index];
		swap[index] = swap[target];
		swap[target] = temp;
		return new String(swap);
	}
	
	
	/**
	 * It checks whether it is possible to create a polindrome
	 * @param password sequnce that we want to rearrange for polindrome
	 * @param target compared index
	 * @param index	compared index
	 * @param res polindrome string
	 * @return true if it can be polindrome else false
	 */
	public boolean isPolindromeCreateable(String password, int target, int index, String res)
	{
		if(target == password.length() && password.length() > 1)
		{
			System.out.println("String can not be polindrome");
			return false;
		}
		if(password.length() == 1)
		{
			StringBuilder sb = new StringBuilder(res);
			sb.append(password.charAt(0));
			res = sb.toString();
			//System.out.println("res: " + res);
			return true;
		}
		if(index == password.length()) //boolean koymaya gerek var mı isFound diye
		{
			return isPolindromeCreateable(password, target+1, 0,res);
		}
		
		if(target != index && password.charAt(index) == password.charAt(target))
		{
			if(password.length() == 2)
			{
				StringBuilder sb = new StringBuilder(res);
				sb.append(password.charAt(0));
				res = sb.toString();
				//System.out.println("res: " + res);
				return true;
			}
			password = swapIndex(password, target, 0);
			password = swapIndex(password, index, password.length() -1);
			StringBuilder sb = new StringBuilder(res);
			sb.append(password.charAt(0));
			res = sb.toString();
			return isPolindromeCreateable(password.substring(1, password.length()-1), 0, 0, res);
		}
		else
		{
			return isPolindromeCreateable(password, target, ++index, res);
		}
		
	}
	
	/**
	 * To determine whether creating polindrome possible, it calls isPolindromeCreateable function
	 * @param password1
	 * @return true if it can be polindrome else false
	 */
	public boolean  isPalindromePossible(String  password1)
	{
		//System.out.println("gelen pass:" + password1);
		String new_password = clean_password(password1);
		String str = "";
		
		if(isPolindromeCreateable(new_password, 0, 0, str))
		{
			//System.out.println("res: " + str);
			return true;
		}
		else
			return false;
	}
	
	
	/**
	 * It controls lenght of string and controls whether at least two brackets
	 * @return if it is true for conditions it returns true else false
	 */
	public boolean isValid()
	{
		int count_bracket = 0;
		for(int i=0; i<password1.length(); i++)
		{
			if(isClosed(password1.charAt(i)) || isOpen(password1.charAt(i)))
				count_bracket++;
		}
		
		if(count_bracket >= 2 && password1.length() >=8)
			return true;
		else if(count_bracket < 2)
			System.out.println("There is not enough bracket");
		else
			System.out.println("Size of password should be at least 8");
		return false;
	}
	
	/**
	 * It calls all validation functions
	 * @param username
	 * @return return value of validation methods
	 */
	public boolean control(String username)
	{
		return(
				isValid() &&
				containsUsernameSpirit(username, password1) &&
				isBalancedPassword(password1) &&
				isPalindromePossible(password1)
				);
	}
	
}
