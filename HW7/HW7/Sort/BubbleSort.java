package Sort;

import java.util.Map.Entry;

/**
 * Bubble sort class for map structure
 * @author Onur
 *
 */
public class BubbleSort {

	private myMap sortedMap;
	private myMap originalMap;
	
	/**
	 * It initializes variables and measure times
	 * @param map given map to sort
	 */
	public BubbleSort(myMap map)
	{
		sortedMap = copyMap(map, 0, map.size()-1);
		originalMap = copyMap(map, 0, map.size()-1);
		long startTime = System.nanoTime();
		sort();
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("Total execution time for Bubble Sort in nanoseconds: "
                + elapsedTime);

	}
	
	/**
	 * It compares adjacents and It sorts
	 */
	private void sort()
	{
		boolean exchange;
		int pass = 1;
		do
		{
			exchange = false;
			for(int j=0; j<sortedMap.size()-pass; j++)
			{
				if(sortedMap.getValue(j).getCount() > sortedMap.getValue(j+1).getCount())
				{
					sortedMap = swap(j, j+1);
					exchange = true;
				}		
			}
			pass++;
		}while(exchange);
		
	}
	
	/**
	 * It copies original map to other map given as parameter
	 * @param table original map
	 * @param begin beginning index to copy
	 * @param end ending index to copy
	 * @return It returns copied map
	 */
	private myMap copyMap(myMap table, int begin, int end)
	{
		return table.copyMap(begin, end);
	}
	
	/**
	 * It prints sortedMap
	 */
	public void printSorted()
	{
		System.out.println("Sorted with Bubble\n-------------------");
		sortedMap.printMap();
	}
	
	/**
	 * To swap given indexes It creates new map
	 * @param first_index first index to swap
	 * @param second_index second index to swap
	 * @return new swapped map
	 */
	private myMap swap(int first_index, int second_index)
	{
		myMap copiedMap = new myMap();
		int currentIndex = 0;
		
		for (Entry<String, info> entry : sortedMap.entryset()) 
		{
			if(currentIndex != first_index && currentIndex != second_index)
			{
				copiedMap.put(entry.getKey(), entry.getValue());
			}
			else if(currentIndex == first_index)
				copiedMap.put(sortedMap.getKey(second_index), sortedMap.getValue(second_index));
			else if(currentIndex == second_index)
				copiedMap.put(sortedMap.getKey(first_index), sortedMap.getValue(first_index));
			currentIndex++;
		}
		
		return copiedMap;
	}
}
