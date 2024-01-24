package Graph;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * To keep input from file which is going to be read
 * @author Onur
 *
 */
public class CSE222Map {

	private int[][] map;
	private int[][] num;
	
	private int start_x;
	private int start_y;
	private int end_x;
	private int end_y;
	private int numberOfZeros;
	private int x_axis;
	private int y_axis;
	
	/**
	 * @param file_name
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public CSE222Map(String file_name, int width, int height) throws IOException
	{
		x_axis = width;
		y_axis = height;
		map = new int[y_axis][x_axis];
		num = new int[y_axis][x_axis];
		numberOfZeros = 0;
		buildMap(file_name);
	}
	
	public int getX()
	{
		return x_axis;
	}
	
	public int getY()
	{
		return y_axis;
	}
	
	public int get(int y, int x)
	{
		return map[y][x];
	}
	
	public int getNum(int y, int x)
	{
		return num[y][x];
	}
	
	/**
	 * Read given file and creates map
	 * Num array keeps order of zeros according to their coordinates in map
	 * @param file_name
	 * @throws IOException
	 */
	private void buildMap(String file_name) throws IOException
	{
		FileReader file_Reader = new FileReader(file_name + ".txt");
		BufferedReader reader	= new BufferedReader(file_Reader);

        String line;
        int y = 0;
        //It reads file line by line and it uses ',' as a delimeter and it seperates each character as String elements
        for(int j=0; (line = reader.readLine()) != null; j++)
        {
        	String coordinates[] = line.split(",");
        	if(j==0)
        	{
        		start_y = Integer.parseInt(coordinates[0]);
    			start_x = Integer.parseInt(coordinates[1]);
        	}
        	else if(j==1)
        	{
        		end_y = Integer.parseInt(coordinates[0]);
    			end_x = Integer.parseInt(coordinates[1]);
        	}
        	else
        	{
        		for(int i=0; i< coordinates.length; i++)
        		{
        			int value = Integer.parseInt(coordinates[i]);
        			map[y][i] = value;
        			if(value == 1)
        			{
        				num[y][i] = -1;
        			}
        			else if(value == 0)
        			{
        				num[y][i] = numberOfZeros;
        				numberOfZeros++;
        			}
        		}
        		y++;
        	}
        }
	}
	
	/**
	 * It creates a png file and it change pixels color according to their coordinates
	 * @param path_coordinates It keeps shortest path as coordinates
	 * @param width
	 * @param height
	 * @param name
	 */
	public void CreatePng(ArrayList<ArrayList<Integer>> path_coordinates, int width, int height, String name)
	{
	        //int height = 500; //0 engeller için 50 engelsiz için 250 benim yolum
	        BufferedImage png = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        for (int y = 0; y < height; y++) 
	        {
	            for (int x = 0; x < width; x++) 
	            {
	            	int Color = 0;
	            	if(contains(path_coordinates, y, x))
	            	{
	            		Color = 1000;
	            	}
	            	else if(this.get(y, x) == 0)
	            	{
	            		Color = 40;
	            	}
	            	else
	            	{
	            		Color = 0;
	            	}
	            	png.setRGB(x, y, Color);
	            }
	       }
	        File PngFile = new File(name +".png");
	        try 
	        {
	            ImageIO.write(png, "png", PngFile);
	            System.out.println(name+".png file created successfully! ");
	        } 
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	}
	
	/**
	 * It controls whether given coordinates is already exist in path
	 * @param path_coordinates
	 * @param y
	 * @param x
	 * @return
	 */
	private boolean contains(ArrayList<ArrayList<Integer>> path_coordinates, int y, int x)
	{
		for(int i=0; i<path_coordinates.size(); i++)
    	{
    		if(path_coordinates.get(i).get(0) == y && path_coordinates.get(i).get(1) == x)
    			return true;
    	}
		return false;
	}
	
	/**
	 * It writes path coordinates to file
	 * @param path_coordinates
	 * @param mapName
	 * @param algorithm_type
	 * @throws IOException
	 */
	public void writePath( ArrayList<ArrayList<Integer>> path_coordinates, String mapName ,String algorithm_type) throws IOException
	{
		 FileWriter writer = new FileWriter(mapName + algorithm_type + ".txt");
		 for(int i=0; i<path_coordinates.size(); i++)
		 {
			 writer.write(path_coordinates.get(i).get(0) + "," + path_coordinates.get(i).get(1) + "\n");
		 }
		 writer.close();
	}
	
	public int getStartX()
	{
		return start_x;
	}
	
	public int getStartY()
	{
		return start_y;
	}
	
	public int getEndX()
	{
		return end_x;
	}
	
	public int getEndY()
	{
		return end_y;
	}
	public int getNumberOfZeros()
	{
		return numberOfZeros;
	}
}
