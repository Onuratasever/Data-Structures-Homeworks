package Sort;

/**
 * Merge sort class for map structure
 * @author Onur
 *
 */
public class mergeSort {

	private myMap originalMap;
	private myMap sortedMap;
	
	/**
	 * It initializes variables and it measures time
	 * @param map given map to sort
	 */
	public mergeSort(myMap map)
	{
		//System.out.println("Sorted Map");
		sortedMap = new myMap();
		originalMap = map;
		sortedMap = copyMap(map, 0, map.size()-1);
		long startTime = System.nanoTime();
		mergesort(sortedMap);
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("Total execution time for Merge Sort in nanoseconds: "
                + elapsedTime);

	}
	
	/**
	 * No parameter constructor
	 */
	public mergeSort()
	{
		System.out.println("Sorted Map");
		sortedMap = new myMap();
	}
	
	/**
	 * Recursive function.
	 * It seperates map two pieces for each time and after sort operation it merges
	 * @param table map to sort
	 */
	public void mergesort(myMap table)
	{
		if(table.size() > 1)
		{
			int halfSize = table.size() / 2;
			
			myMap leftTable = new myMap();
			myMap rightTable = new myMap();
			
			leftTable = copyMap(table, 0, halfSize - 1);
			rightTable = copyMap(table, halfSize, table.size());
			
			mergesort(leftTable);
			mergesort(rightTable);
			merge(table, leftTable, rightTable);
		}
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
		System.out.println("Sorted with Merge\n------------------");
		sortedMap.printMap();
	}
	
	/**
	 * It merges in out map given two map by sorting
	 * @param out output map
	 * @param leftTable map to merge
	 * @param rightTable map to merge
	 */
	private void merge(myMap out, myMap leftTable, myMap rightTable)
	{
		int i=0, j=0;
		out.clear();
		while(i < leftTable.size() && j <rightTable.size())
		{
			if(leftTable.getValue(i).getCount() <= rightTable.getValue(j).getCount())
			{
				out.put(leftTable.getKey(i), leftTable.getValue(i));
				i++;
			}
			else
			{
				out.put(rightTable.getKey(j), rightTable.getValue(j));
				j++;
			}
		}
		
		while(i < leftTable.size())
		{
			out.put(leftTable.getKey(i), leftTable.getValue(i));
			i++;
		}
		while(j <rightTable.size())
		{
			out.put(rightTable.getKey(j), rightTable.getValue(j));
			j++;
		}
	}
}
