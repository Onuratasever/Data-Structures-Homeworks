package Graph;

public class Edge {

	private int dest; // destination vertex of an edge
	private int source; // source vertex of an edge
	private double weight; // the weight;
	
	//If there is no wight in parameter it sets it as 1
	public Edge(int source, int dest)
	{
		this(source, dest, 1);
	}
	
	public Edge(int source, int dest, double weight)
	{
		this.dest = dest;
		this.source = source;
		this.weight = weight;
	}
	
	public boolean equals(Edge e)
	{
		if(this.dest == e.getDest() && this.source == e.getSource())
		{
			return true;
		}
		return false;
	}
	
	public int getDest()
	{
		return dest;
	}
	
	public int getSource()
	{
		return source;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	public String toString()
	{
		return (dest + " -> " + source);
	}
	
}
