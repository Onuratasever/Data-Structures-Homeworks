package Graph;

import java.io.IOException;
import java.util.ArrayList;

public class TestCases implements Runnable{

	private  String FileName;
    private int X_SIZE;
    private int Y_SIZE;

    public TestCases(String FileName, int X_SIZE, int Y_SIZE) 
    {
        this.FileName = FileName;
        this.X_SIZE = X_SIZE;
    	this.Y_SIZE = Y_SIZE;
    }

    public void test() throws IOException
    {
    	System.out.println("\n\n*******************\nMap is " + this.FileName + " with X_SIZE " + this.X_SIZE + " and Y_SIZE " + this.Y_SIZE + "\n********************\n");
    	
    	CSE222Map map = new CSE222Map(FileName, X_SIZE, Y_SIZE);
    	CSE222Graph graph = new CSE222Graph(map);
    	ArrayList<ArrayList<Integer>> path_BFS = new ArrayList<ArrayList<Integer>>();
    	ArrayList<ArrayList<Integer>> path_DJ = new ArrayList<ArrayList<Integer>>();
    	graph.Dijkstra(map.getStartY(), map.getStartX(), map.getEndY(), map.getEndX(), map, path_DJ);
    	map.CreatePng(path_DJ, X_SIZE, Y_SIZE, (FileName+"_dj"));
		map.writePath(path_DJ, FileName, "dj");
		
		
		graph.BFS(map.getStartY(), map.getStartX(), map.getEndY(), map.getEndX(), map, path_BFS);
		map.CreatePng(path_BFS, X_SIZE, Y_SIZE, (FileName+"_BFS"));
		map.writePath(path_BFS, FileName, "_BFS");
		
		System.out.println("Dijkstra Path of " + FileName + ": "+ path_DJ.size());
		System.out.println("BFS Path: "+ path_BFS.size());
    }
    
	@Override
	public void run() {
		 try {
			test();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
