package myMapStructure;

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
		myMap dene = new myMap("'Hush, hush!' whispered the rushing wind.");
		dene.printMap();
		mergeSort sort = new mergeSort(dene);
		sort.printSorted();
	}

}
