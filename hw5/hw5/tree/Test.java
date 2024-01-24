package tree;

import java.util.Scanner;

/**
 * Test class
 * @author Onur
 *
 */
public class Test {
	public static void main(String[] args) {
		Tree asd = new Tree();
		asd.bfs("CSE232");
		System.out.println("");
		asd.bfs("CSE2332");
		System.out.println("");
		asd.dfs("CSE232");
		System.out.println("");
		asd.dfs("CSE2332");
		System.out.println("");
		asd.postOrderTraversal("CSE232");
		System.out.println("");
		asd.postOrderTraversal("CSE2332");
		
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Enter data for BFS");
		String data =  myObj.nextLine();
		asd.bfs(data);
		
		System.out.println("Enter data for DFS");
		data =  myObj.nextLine();
		asd.dfs(data);
		
		System.out.println("Enter data for postOrderTraversal");
		data =  myObj.nextLine();
		asd.postOrderTraversal(data);
		
	    System.out.println("Enter path move from:");
	    String from = myObj.nextLine();
	    System.out.println("Enter path move to:");
	    String to = myObj.nextLine();
		asd.move(from, to);
	}
}
