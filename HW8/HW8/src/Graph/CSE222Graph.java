package Graph;

import java.util.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CSE222Graph {

	private int numVer;
	private LinkedList<Edge>[] edges;
	
	/**
	 * It initializes arrays and LinkedLists
	 * It arranges number of vertices as number of zeros
	 * It calls createGraph method
	 * @param map given map
	 */
	public CSE222Graph(CSE222Map map)
	{
		numVer = map.getNumberOfZeros();
		edges = new LinkedList[numVer];
		for(int i=0; i<numVer; i++)
		{
			edges[i] = new LinkedList<Edge>();
		}
		createGraph(map);
		System.out.println("Graph created");
	}
	
	/**
	 * It scans map and for each zero in map it controls around
	 * @param map
	 */
	private void createGraph(CSE222Map map)
	{
		for(int y=0; y<map.getY(); y++)
		{
			for(int x =0; x<map.getX(); x++)
			{
				if(map.get(y, x) == 0)
				{
					checkPoint(map, y, x);
				}
			}
		}
	}
	
	/**
	 * It controls around of given coordinates.
	 * If there is a zero It creates new edge and adds it to linked list
	 * @param map
	 * @param y
	 * @param x
	 */
	private void checkPoint(CSE222Map map, int y, int x)
	{
		if(y-1 >= 0)
		{
			if(map.get(y-1, x) == 0)
			{
				insert(new Edge(map.getNum(y,x), map.getNum(y-1,x)));
			}
		}
		if(y+1 < map.getY())
		{
			if(map.get(y+1, x) == 0)
			{
				insert(new Edge(map.getNum(y,x), map.getNum(y+1,x)));
			}
		}
		if(x-1 >= 0)
		{
			if(map.get(y, x-1) == 0)
			{
				insert(new Edge(map.getNum(y,x), map.getNum(y,x-1)));
			}
		}
		if(x+1 < map.getX())
		{
			if(map.get(y, x+1) == 0)
			{
				insert(new Edge(map.getNum(y,x), map.getNum(y,x+1)));
			}
		}
		if(y-1 >= 0 && x-1 >= 0)
		{
			if(map.get(y-1, x-1) == 0)
			{
				insert(new Edge(map.getNum(y,x), map.getNum(y-1,x-1)));
			}
		}
		if(y-1 >= 0 && x+1 < map.getX())
		{
			if(map.get(y-1, x+1) == 0)
			{
				insert(new Edge(map.getNum(y,x), map.getNum(y-1,x+1)));
			}
		}
		if(y+1 < map.getY() && x+1 < map.getX())
		{
			if(map.get(y+1, x+1) == 0)
			{
				insert(new Edge(map.getNum(y,x), map.getNum(y+1,x+1)));
			}
		}
		if(y+1 < map.getY() &&  x-1 >= 0)
		{
			if(map.get(y+1, x-1) == 0)
			{
				insert(new Edge(map.getNum(y,x), map.getNum(y+1,x-1)));
			}
		}
	}

	/**
	 * It print all edges for all vertices
	 */
	public void printEdges()
	{
		for(int i=0; i<edges.length; i++)
		{
			System.out.print("vertex " + i + " : ");
			for(int j=0; j<edges[i].size(); j++)
			{
				System.out.print(" -> " + edges[i].get(j).getDest());
			}
			System.out.println();
		}
	}
	
	public int getNumVer()
	{
		return numVer;
	}
	
	/**
	 * It controls whether given edge is exist
	 * @param source
	 * @param dest
	 * @return
	 */
	public boolean isEdge(int source, int dest)
	{
		return edges[source].contains(new Edge(source, dest));
	}
	
	/**
	 * It adds given edge to linkedList
	 * @param edge
	 */
	public void insert(Edge edge)
	{
		edges[edge.getSource()].add(edge);
	}
	
	/**
	 * It returns an Iterator to traverse
	 * @param source
	 * @return
	 */
	public Iterator<Edge> edgeIterator(int source)
	{
		return edges[source].iterator();
	}
	
	/**
	 * Finds shortest path if it is exist.
	 * @param start_y Y coordinate of beggining point
	 * @param start_x X coordinate of beggining point
	 * @param end_y Y coordinate of end point
	 * @param end_x X coordinate of end point
	 * @param map given map
	 * @param path_coordinates to keep path coordinates
	 * @return
	 */
	public ArrayList<Integer> BFS(int start_y, int start_x, int end_y, int end_x, CSE222Map map, ArrayList<ArrayList<Integer>> path_coordinates)
	{
		//I found vertices according to given coordinates
		int start_vertex = map.getNum(start_y, start_x);
		int target_vertex = map.getNum(end_y, end_x);
		
		//Queue to continue algorithm with edges in order
		Queue<Integer> queue = new LinkedList<Integer>();
		
		//It keep any vertex is visited
		boolean[] visited = new boolean[numVer];
		
		
		int[] path = new int[numVer];
		
		for(int i=0; i<numVer; i++)
		{
			visited[i] = false;
			path[i] = -1;
		}
		
		visited[start_vertex] = true;
		queue.add(start_vertex);
		
		//It checks all unvisited adjacents of current vertex. In the path array, It keeps where they come from
		while(!queue.isEmpty())
		{
			int control = queue.remove();
			for(int i=0; i<edges[control].size(); i++)
			{
				if(!visited[edges[control].get(i).getDest()])
				{
					visited[edges[control].get(i).getDest()] = true;
					path[edges[control].get(i).getDest()] = control;
					queue.add(edges[control].get(i).getDest());
					
					if(edges[control].get(i).getDest() == target_vertex)
					{
						//According to where they came from It keeps vertices in reverse order
						ArrayList<Integer> newPath = new ArrayList<Integer>();
						int index = target_vertex;
						newPath.add(target_vertex);
						while(path[index] != -1)
						{
							newPath.add(path[index]);
							index = path[index];
						}
						//According to reverse ordered list, It fills  path_coordinates according to vertices's coordinates.
						setCoordinates(newPath, path_coordinates, map);
						return newPath;
					}
				}
			}
		}
		System.out.println("Path could not found");
		return null;
	}

	//Path was reverse ordered. It fills path_coordinates according to order.
	public void setCoordinates(ArrayList<Integer> path, ArrayList<ArrayList<Integer>> path_coordinates, CSE222Map map)
	{
		boolean isBreak;
		for(int i=path.size()-1; i >=0; i--)
		{
			isBreak = false;
			ArrayList<Integer> coordinate = new ArrayList<Integer>();
			for(int y=0; y<map.getY(); y++)
			{
				for(int x=0; x<map.getX(); x++)
				{
					if(path.get(i) == map.getNum(y, x))
					{
						coordinate.add(y);
						coordinate.add(x);
						path_coordinates.add(coordinate);
						isBreak = true;
					}
					if(isBreak)
					{
						break;
					}
				}
				if(isBreak)
				{
					break;
				}
			}
		}
	}
	
	/**
	 * Finds shortest path if it is exist.
	 * @param start_y Y coordinate of beggining point
	 * @param start_x X coordinate of beggining point
	 * @param end_y Y coordinate of end point
	 * @param end_x X coordinate of end point
	 * @param map given map
	 * @param path_coordinates to keep path coordinates
	 * @return
	 */
	public void Dijkstra(int start_y, int start_x, int end_y, int end_x, CSE222Map map, ArrayList<ArrayList<Integer>> path_coordinates)
	{
		
		//I found vertices according to given coordinates
		int start_vertex = map.getNum(start_y, start_x);
		int target_vertex = map.getNum(end_y, end_x);
		int current_vertex;
		
		//This is parent array. It keeps where vertices come from (shortest one)
		int[] pred = new int[numVer];
		
		//It keeps distance from start_vertex for each vertex
		double[] distance = new double[numVer];
		
		//It keeps all vertices to be processed
		HashSet<Integer> vertices = new HashSet<Integer>(numVer);
		
		//Since we do not know the distance to start_vertex it fills all indexes with infinity except start_vertex index
		//It adds all vertices to be processed except start_vertex.
		//It fill all indexes of pred  with -1
		for(int i=0; i<numVer; i++)
		{
			if(i != start_vertex)
			{
				vertices.add(i);
			}
			if(i== start_vertex)
			{
				distance [i] = 0;
			}
			else
				distance[i] = Double.POSITIVE_INFINITY;
			pred[i] = -1;
		}
		
		current_vertex = start_vertex;
		//It removes current_vertex from set to process and It controls it's adjacents.
		// If distance of adjacent is bigger than current_vertex distance + weight it updates pred and distances
		while(!vertices.isEmpty())
		{
			vertices.remove(current_vertex);
			
			for(int i=0; i<edges[current_vertex].size(); i++)
			{
					if(edges[current_vertex].get(i).getWeight() + distance[current_vertex]< distance[edges[current_vertex].get(i).getDest()])
					{
						distance[edges[current_vertex].get(i).getDest()] = edges[current_vertex].get(i).getWeight() + distance[current_vertex];
						pred[edges[current_vertex].get(i).getDest()] = current_vertex;
					}
				
			}
			
			//It founds smallest vertices from set according to their distance to start_vertex
			int counter =0;
			for(int i=0; i<distance.length; i++)
			{
				if(vertices.contains(i))
				{
					if(counter == 0)
					{
						current_vertex = i;
						counter++;
					}
					if(distance[i] < distance[current_vertex])
						current_vertex = i;
				}
			}
		}
		
		//According to where they came from It keeps vertices in reverse order
		ArrayList<Integer> newPath = new ArrayList<Integer>();
		int index = target_vertex;
		newPath.add(target_vertex);
		while(pred[index] != -1)
		{
			newPath.add(pred[index]);
			//System.out.println(pred[index]);
			index = pred[index];
		}
		//According to reverse ordered list, It fills  path_coordinates according to vertices's coordinates.
		setCoordinates(newPath, path_coordinates, map);
		
	}
	
	/**
	 * It returns an edge according to given coordinates.
	 * @param source
	 * @param dest
	 * @return
	 */
	public Edge getEdge(int source, int dest)
	{
		Edge target = new Edge(source, dest, Double.POSITIVE_INFINITY);
		
		for(Edge edge : edges[source])
		{
			if(edge.equals(target))
				return edge;
		}
		
		return target;
	}
}
