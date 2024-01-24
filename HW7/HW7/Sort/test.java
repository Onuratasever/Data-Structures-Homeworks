package Sort;

/**
 * test class
 * @author Onur
 *
 */
public class test {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		myMap dene = new myMap("xxxaayyyyycbbbb");
		dene.printMap();		
		BubbleSort sort = new BubbleSort(dene);
		sort.printSorted();
		
		mergeSort soort = new mergeSort(dene);
		soort.printSorted();
		
		SelectionSort sort3 = new SelectionSort(dene);
		sort3.printSorted();
		
		InsertionSort sort4 = new InsertionSort(dene);
		sort4.printSorted();
		
		QuickSort sort5 = new QuickSort(dene);
		sort5.printSorted();
	}

}
