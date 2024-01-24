package tree;

import  java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;  

/**
 * Generic tree class
 * @author Onur
 *
 */
public class Tree{

	private ArrayList<ArrayList<String>> data;
	private JFrame frame;
	private DefaultMutableTreeNode root;
	
	/**
	 * It checks if given node is already exist in tree
	 * @param localRoot
	 * @param newNode
	 * @return If node that we want to add is already exist in tree, it returns node object, If it is not in tree it returns null 
	 */
	public DefaultMutableTreeNode isAlreadyExist(DefaultMutableTreeNode localRoot, DefaultMutableTreeNode newNode)
	{
		for(int i=0; i<localRoot.getChildCount(); i++)
		{
			if(((DefaultMutableTreeNode)localRoot.getChildAt(i)).toString().compareTo(newNode.toString()) == 0)
			{
				return (DefaultMutableTreeNode)localRoot.getChildAt(i);
			}	
		}
		return null;
	}
	
	/**
	 * It creates proper tree with given parameters with recursive calls.
	 * @param line has string values to create node
	 * @param localRoot node that we want to add
	 * @param index for recusive calls, index of string arraylist which is wanted to be added as node
	 */
	public void createChildren(ArrayList<String> line, DefaultMutableTreeNode localRoot, int index)
	{
		//comparision of children
		DefaultMutableTreeNode new_root = new DefaultMutableTreeNode(line.get(index));
		DefaultMutableTreeNode temp = isAlreadyExist(localRoot, new_root);
		boolean isDuplicated = false;
		
		if(temp != null)
		{
			new_root = temp;
			isDuplicated = true;
		}
		
		if(!isDuplicated)
		{
			localRoot.add(new_root);
			for(int i=1+index; i<line.size(); i++)
			{
				//System.out.println("geliyomu");
				DefaultMutableTreeNode new_node = new DefaultMutableTreeNode(line.get(i));
				new_root.add(new_node);
				new_root =	new_node;
			}
		}
		else
		{
			createChildren(line, new_root, ++index);
		}
	}
	
	/**
	 * It control each level of tree from leftmost to rightmost and it search given value
	 * @param data which is wanted to be found
	 * @return If value is found in any level, it returns true if it is not found it returns false
	 */
	public boolean bfs(String data)
	{
		System.out.println("Using BFS to find '" + data + "' in the tree.");
		Queue<DefaultMutableTreeNode> queue = new LinkedList<>();
		int j=1;
		System.out.printf("Step 0 -> %s",  root.getUserObject().toString());
		if(root.toString().compareTo(data) == 0)
		{
			System.out.println("(Found!)");
			return true;
		}
		else
		{
			System.out.println("");
			queue.add(root);
		}
		while(!queue.isEmpty())
		{
			for(int i = 0; i < queue.peek().getChildCount(); i++)
			{
				System.out.printf("Step %d -> %s", j++, queue.peek().getChildAt(i).toString());
				if(queue.peek().getChildAt(i).toString().compareTo(data) == 0)
				{
					System.out.println("(Found!)");
					return true;
				}
				else
				{
					System.out.println("");
					queue.add((DefaultMutableTreeNode)queue.peek().getChildAt(i));
				}
			}
			queue.poll();
		}
		System.out.println("Not found.");
		return false;
	}
	
	/**
	 * It search if data is exist in tree with recursive calls.
	 * @param data which is wanted to find
	 * @param localRoot parent root
	 * @param step 
	 * @return True if data is found, false if it is not found
	 */
	private boolean dfs_recursive(String data, DefaultMutableTreeNode localRoot, int[] step)
	{
		System.out.println("Step " + step[0]++ + " -> " + localRoot.toString());
		if(localRoot.toString().compareTo(data) == 0)
		{
			System.out.println("(Found!)");
			return true;
		}
		else
		{
			for(int i=localRoot.getChildCount() - 1; i>=0; i--)
			{
				 if(dfs_recursive(data, (DefaultMutableTreeNode) localRoot.getChildAt(i), step))
					 return true;
			}
		}
		return false;
	}
	
	/**
	 * dfs method calls recursive one
	 * @param data which is wanted to find
	 * @return according to recursive dfs method it returns true or false
	 */
	public boolean dfs(String data)
	{
		System.out.println("Using DFS to find '" + data + "' in the tree.");
		int step[] = {1};
		if(!dfs_recursive(data, root, step))
		{
			System.out.println("Not found.");
			return false;
		}
		return true;
	}
	
	/**
	 * It search given data by using post order traversal algorithm
	 * @param data which is wanted to be found
	 * @param localRoot node that we compare
	 * @param step
	 * @return true if it is found else false
	 */
	private boolean postOrderTraversal_recursive(String data,  DefaultMutableTreeNode localRoot, int[] step)
	{
		if(localRoot == null)
		{
			System.out.println("Not found.");
			return false;
		}
		for(int i=0; i<localRoot.getChildCount(); i++)
		{
			if(postOrderTraversal_recursive(data, ( DefaultMutableTreeNode)localRoot.getChildAt(i), step))
				return true;
		}
		System.out.println("Step " + step[0]++ + " -> " + localRoot.toString());
		if(localRoot.toString().compareTo(data) == 0)
		{
			System.out.println("(Found!)");
			return true;
		}
		else
			return false;
	}
	
	/**
	 * It search given data by using post order traversal algorithm with recursive method
	 * @param data which is wanted to be found
	 * @return True if it is found false if it is not found
	 */
	public boolean postOrderTraversal(String data)
	{
		System.out.println("Using Post-Order traversel to find '" + data + "' in the tree.");
		int step[] = {1};
		if(!postOrderTraversal_recursive(data, root, step))
		{
			System.out.println("Not found.");
			return false;
		}
		return true;
	}
	
	/**
	 * It compares given string with node
	 * @param from which is wanted to be found
	 * @param node to compare
	 * @return it returns node if it matches, else it returns null
	 */
	private DefaultMutableTreeNode findRoot(String from, DefaultMutableTreeNode node)
	{
		for(int i=0; i<node.getChildCount(); i++)
		{
			if(node.getChildAt(i).toString().compareTo(from) == 0)
			{
				return (DefaultMutableTreeNode)node.getChildAt(i);
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param control node which children is going to compare with given data
	 * @param str given data to compare
	 * @return If it is exist in tree it returns node object if it is not found it returns null
	 */
	private DefaultMutableTreeNode isAlreadyExist(DefaultMutableTreeNode control, String str)
	{
		for(int i=0; i<control.getChildCount(); i++)
		{
			if(control.getChildAt(i).toString().compareTo(str) == 0)
			{
				return (DefaultMutableTreeNode)control.getChildAt(i);
			}
		}
		return null;
	}
	
	/**
	 * It moves node from from path to to path
	 * @param from
	 * @param to
	 */
	public void move(String from, String to)
	{
		String[] from_seperated = from.split("->");
		DefaultMutableTreeNode addedNode = null;
		DefaultMutableTreeNode Parent = null;
		DefaultMutableTreeNode node = null;
		boolean fromIsNodeExist = false;
		boolean toIsNodeExist = false;
		
		for(int i=0; i<root.getChildCount(); i++)
		{
			if(root.getChildAt(i).toString().compareTo(from_seperated[0]) == 0)
			{
				fromIsNodeExist = true;
				addedNode = (DefaultMutableTreeNode)root.getChildAt(i);
			}
		}
		if(!fromIsNodeExist)
		{
			System.out.println("It is not exist");
			return;
		}
		//System.out.println("fora girmeden önce addNode: " + addedNode.toString());
		for(int i=1; i < from_seperated.length; i++)
		{
			Parent = addedNode;
			addedNode = findRoot(from_seperated[i], addedNode); // bu bana vvarsa yeni aradığım nodu yok ise null nodu return etsin
			if(addedNode == null)
			{
				System.out.println(from_seperated[i] +" It is not exist");
				return;
			}
		}
		//System.out.println("Parent: " + Parent.toString() + " Remove: " + addedNode.toString());
		Parent.remove(addedNode);
		
		
		
		// NODU BULDUKTAN SONRA EKLEME KISMI ---------------------------------
		for(int i=0; i<root.getChildCount(); i++)
		{
			if(root.getChildAt(i).toString().compareTo(to) == 0)
			{
				toIsNodeExist = true;
				node = (DefaultMutableTreeNode)root.getChildAt(i);
			}
		}
		
		if(!toIsNodeExist)//eklenecek yılın var olmama durumu
		{
			node = new DefaultMutableTreeNode(to);
			root.add(node);
			DefaultMutableTreeNode newNode;
			for(int i=1; i< from_seperated.length-1; i++)
			{
				newNode = new DefaultMutableTreeNode(from_seperated[i]);
				node.add(newNode);
				node = newNode;
			}
			node.add(addedNode);
		}
		else
		{
			toIsNodeExist = false;
			DefaultMutableTreeNode newNode;
			for(int i=1; i< from_seperated.length-1; i++)
			{
				for(int j=0; j<node.getChildCount(); j++)
				{
					if(node.getChildAt(j).toString().compareTo(from_seperated[i]) == 0)
					{
						toIsNodeExist = true;
						node = (DefaultMutableTreeNode)root.getChildAt(i);
					}
				}
				if(!toIsNodeExist)
				{
					newNode = new DefaultMutableTreeNode(from_seperated[i]);
					node.add(newNode);
					node = newNode;
				}
			}
			DefaultMutableTreeNode isExist = isAlreadyExist(node, from_seperated[from_seperated.length - 1]);
			//System.out.println("eklenecek node un parenti: " + node.toString());
			
			if(isExist != null)
			{
				System.out.println(to + "->"+ from.substring(6) + "is overwritten");
				node.remove(isExist);
				node.add(addedNode);
			}
			else
				node.add(addedNode);
			
		}
		for(int i=0; i<root.getChildCount(); i++)
		{
			if(root.getChildAt(i).isLeaf())
			{
				root.remove((DefaultMutableTreeNode)root.getChildAt(i));
			}
		}
		frame.setVisible(false);
		createFrame("Edited Tree");
	}
	
	/**
	 * It initializes frame and set title, size
	 * It initializes jTree
	 * @param title
	 */
	public void createFrame(String title)
	{
		frame = new JFrame();
		JTree jt=new JTree(root);
		frame.setTitle(title);
		frame.add(jt);
		frame.setSize(250, 250);
		frame.setVisible(true);
	}
	
	/**
	 * Tree constuructor.
	 * It calls read_file method
	 * It initializes root
	 * It creates tree with createChildren method
	 */
	public Tree()
	{ 
		data = new ArrayList<ArrayList<String>>();
		read_file();
		root = new DefaultMutableTreeNode("Root");
		for(int i=0; i<data.size(); i++)
		{
			createChildren(data.get(i), root, 0);
		}
		createFrame("Original Tree");
	}
	
	/**
	 * Method reads file
	 * It reads file line by line
	 * It seperates strings and stores in arraylist
	 */
	public void read_file() {
		
		BufferedReader readTxt = null;
		try {
			readTxt = new BufferedReader(new FileReader("kod.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line;
		
		try {
			while((line = readTxt.readLine()) != null)
			{
				ArrayList<String> row = new ArrayList<String>();
			    
			    String[] asd = line.split(";");
			    for(int j=0; j<asd.length; j++)
			    {
			    	row.add(asd[j]);
			    }
			    data.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			readTxt.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
