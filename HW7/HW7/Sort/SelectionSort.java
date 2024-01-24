package Sort;

import java.util.Map.Entry;
/**
 * Selection sort class for map structure
 * @author Onur
 *
 */
public class SelectionSort {

	private myMap sortedMap;
	private myMap originalMap;
	
	/**
	 * It initializes variables and measure times
	 * @param map given map to sort
	 */
	public SelectionSort(myMap map)
	{
		sortedMap = copyMap(map, 0, map.size()-1);
		originalMap = copyMap(map, 0, map.size()-1);
		long startTime = System.nanoTime();
		sort();
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("Total execution time for Selection Sort in nanoseconds: "
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
	 * It finds min value and It swaps with given index
	 */
	private void sort()
	{
		int minIndex;
		for(int i=0; i<sortedMap.size()-1; i++)
		{
			minIndex = findMin(i);
			sortedMap = swap(i, minIndex);
		}
	}
	
	/**
	 * It finds min value
	 * @param i It find min value after index i
	 * @return min value's index
	 */
	private int findMin(int i)
	{
		int minIndex=i, currentIndex = 0;
		for (Entry<String, info> entry : sortedMap.entryset()) 
		{
			if(entry.getValue().getCount() < sortedMap.getValue(minIndex).getCount() && currentIndex > i)
				minIndex = currentIndex;
			currentIndex++;
		}
		return minIndex;
	}
	
	/**
	 * It prints sortedMap
	 */
	public void printSorted()
	{
		System.out.println("Sorted with Selection\n--------------------");
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
