package myMapStructure;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * This is a class which contains LinkedHashMap
 * @author Onur
 *
 */
public class myMap {

	private LinkedHashMap <String, info> map;
	private int mapSize;
	private String str;
	
	/**
	 * One parameter constructor
	 * it initializes variables and it splits given string
	 * @param word string input from user
	 */
	public myMap(String word)
	{
		map = new LinkedHashMap<>();
		mapSize = 0;
		splitString(word);
	}
	
	/**
	 * No parameter constructor
	 * it initializes variables and it splits given string
	 */
	public myMap()
	{
		map = new LinkedHashMap<>();
		mapSize = 0;
	}
	
	/**
	 * It checks possible errors
	 * If there is no error it convert all letters to lower case
	 * It removes all characters except letters
	 * It splits string
	 * @param word
	 */
	private void splitString(String word)
	{
		if(word == null)
			System.err.println("String is null");
		else if(word.length() == 0)
			System.err.println("String is empty");
		else
		{

			System.out.println("Original String: " + word);
			word = word.toLowerCase();
			str = word.replaceAll("[^a-zA-Z ]", "");
			System.out.println("Preprocessed String: " + str);
			System.out.println();
			System.out.println("Original (unsorted) map: ");
			String[] split = str.split(" ");
			for(int i=0; i<split.length; i++)
			{
				buildMap(split[i]);
			}
		}
	}
	
	/**
	 * According to given  parameter, It creates map
	 * @param word given input
	 */
	public void buildMap(String word)
	{
		for(int i=0; i<word.length(); i++)
		{
			if(map.containsKey(String.valueOf(word.charAt(i))))
			{
				map.get(String.valueOf(word.charAt(i))).push(word);
			}
			else
			{
				map.put(String.valueOf(word.charAt(i)), new info(word));
				mapSize++;
			}
		}
	}
	
	/**
	 * Prints the map
	 */
	public void printMap()
	{
		for (Entry<String, info> entry : map.entrySet()) 
		{
            System.out.print(entry.getKey() + " : " + "counts: " + entry.getValue().getCount() + " Words: [");
            entry.getValue().getWords();
            System.out.println("]");
        }
		System.out.println();
	}
	
	/**
	 * @return number of element of map
	 */
	public int size()
	{
		return mapSize;
	}

	/**
	 * It copies map
	 * @param begin begginning index to copy
	 * @param end ending index to copy
	 * @return copied map
	 */
	public myMap copyMap(int begin, int end)
	{
		myMap copiedMap = new myMap();
		int currentIndex = 0;
		
		for (Entry<String, info> entry : map.entrySet()) 
		{
			if(currentIndex >= begin && currentIndex <= end)
			{
				copiedMap.put(entry.getKey(), entry.getValue());
			}
			currentIndex++;
		}
		return copiedMap;
	}
		
	/**
	 * put funciton for map structure
	 * @param str first arguman of (key) of put method
	 * @param newInfo second arguman of (value) of put method
	 * @return what put method of LinkedHashMap returns
	 */
	public info put(String str, info newInfo)
	{
		mapSize++;
		return map.put(str, newInfo);
	}
	
	/**
	 * It removes all element from map
	 */
	public void clear()
	{
		map.clear();
		mapSize = 0;
	}
	
	/**
	 * It get key according to given index
	 * @param index index value for map
	 * @return key if index is found, else it returns null
	 */
	public String getKey(int index)
	{
		int i=0;
		if(index > mapSize)
		{
			System.out.println("Out of bounds");
			return null;
		}
		for (Entry<String, info> entry : map.entrySet())
		{
			if(i == index)
			{
				return entry.getKey();
			}
			i++;
        }
		return null;
	}
	
	/**
	 * It get value according to given index
	 * @param index index value for map
	 * @return key if index is found, else it returns null
	 */
	public info getValue(int index)
	{
		int i=0;
		if(index > mapSize)
		{
			System.out.println("Out of bounds");
			return null;
		}
		for (Entry<String, info> entry : map.entrySet())
		{
			if(i == index)
			{
				return entry.getValue();
			}
			i++;
        }
		return null;
	}
}
