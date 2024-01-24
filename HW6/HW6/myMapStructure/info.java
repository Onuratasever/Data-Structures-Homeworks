package myMapStructure;

import java.util.ArrayList;

/**
 * It keeps data for map value
 * @author Onur
 *
 */
public class info {

	private int count;
	private ArrayList<String> words;
	
	/**
	 * One parameter constructor
	 * @param str given input
	 */
	public info(String str)
	{
		count = 0;
		words = new ArrayList<String>();
		push(str);
	}
	
	/**
	 * It add new element to list
	 * @param word
	 */
	public void push(String word)
	{
		words.add(word);
		count++;
	}
	
	/**
	 * @return count
	 */
	public int getCount()
	{
		return count;
	}
	
	/**
	 * It prints all the elements of string array	
	 */
	public void getWords()
	{
		for(int i=0; i<count; i++)
		{
			System.out.print(words.get(i) + " ");
		}
	}
}
