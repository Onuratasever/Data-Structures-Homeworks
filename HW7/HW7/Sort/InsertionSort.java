package Sort;

import java.util.Map.Entry;
/**
 * Insertion sort class for map structure
 * @author Onur
 *
 */
public class InsertionSort {

	private myMap sortedMap;
	private myMap originalMap;
	
	/**
	 * It initializes variables and measure times
	 * @param map given map to sort
	 */
	public InsertionSort(myMap map)
	{
		sortedMap = copyMap(map, 0, map.size()-1);
		originalMap = copyMap(map, 0, map.size()-1);
		long startTime = System.nanoTime();
		sort();
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("Total execution time for Insertion Sort in nanoseconds: "
                + elapsedTime);

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
		System.out.println("Sorted with Insertion\n-----------------");
		sortedMap.printMap();
	}
	
	/**
	 * It checks all indexex with previous. And It swaps if it is smaller.
	 */
	private void sort()
	{
		for(int i=1; i<sortedMap.size(); i++)
		{
			int control_value = sortedMap.getValue(i).getCount();
			
			for(int j= i-1;  j>= 0 && sortedMap.getValue(j).getCount() > control_value; j--)
			{
				sortedMap = swap(j, j+1);
			}
		}
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
