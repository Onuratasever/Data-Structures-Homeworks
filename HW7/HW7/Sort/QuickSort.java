package Sort;

import java.util.Map.Entry;

/**
 * Quick sort class for map structure
 * @author Onur
 *
 */
public class QuickSort {

	private myMap sortedMap;
	private myMap originalMap;
	
	/**
	 * It initializes variables and measure times
	 * @param map given map to sort
	 */
	public QuickSort(myMap map)
	{
		sortedMap = copyMap(map, 0, map.size()-1);
		originalMap = copyMap(map, 0, map.size()-1);
		long startTime = System.nanoTime();
		sort(0, map.size()-1);
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("Total execution time for Quick Sort in nanoseconds: "
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
		System.out.println("Sorted with Quick\n--------------------");
		sortedMap.printMap();
	}
	
	/**
	 * It sorts according to pivot
	 * @param first beginning index of subarray
	 * @param last ending index of subarray
	 */
	private void sort(int first, int last)
	{
		if(first < last)
		{
			int pivotIndex = partition(first, last);
			
			sort(first, pivotIndex-1);
			
			sort(pivotIndex+1, last);
		}
	}
	
	/**
	 * According to pivot It collects smaller than pivot to left side and larger than pivot right side
	 * @param first beginning index of subarray
	 * @param last ending index of subarray
	 * @return pivot
	 */
	private int partition(int first, int last)
	{
		int pivot = sortedMap.getValue(first).getCount();
		
		int up = first;
		int down = last;
		
		do 
		{
			for(; up < last && pivot >= sortedMap.getValue(up).getCount(); up++)
			{
				
			}
			for(;pivot < sortedMap.getValue(down).getCount(); down --)
			{
				
			}
			if(up < down)
			{
				sortedMap = swap(up, down);
			}
			
		}while(up < down);
		
		sortedMap = swap(first, down);
		return down;
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
