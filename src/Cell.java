import java.util.ArrayList;

public class Cell {

	private int xValue;
	private int yValue;
	private ArrayList<Integer> possibilities;
	private Driver d = new Driver();
	private int[][] puzzle = d.getPuzzle();
	private int[][] smallPuzzle = new int[3][3];
	
	//Initiates new instance of Cell with the yValue, xValue, and a new possibilities ArrayList
	public Cell(int yValue, int xValue)
	{
		possibilities = new ArrayList<Integer>() {{
		    add(1); add(2);  add(3);
		    add(4); add(5);  add(6);
		    add(7); add(8);  add(9);
		}};
		
		this.xValue = xValue;
		this.yValue = yValue;
	}
	
	//Returns the size of possibilities 
	public int getPossibilitySize()
	{
		return possibilities.size();
	}
	
	public ArrayList<Integer> getPossibilities()
	{
		return possibilities;
	}
	
	public int getXValue()
	{
		return xValue;
	}
	
	public int getYValue()
	{
		return yValue;
	}
	
	public void getSquareValues()
	{
		//Searches through the square
		int rowOffset = (yValue / 3) * 3;
		int columnOffset = (xValue / 3) * 3;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				smallPuzzle[i][j] = puzzle[i + rowOffset][j + columnOffset];
				if (possibilities.contains(puzzle[i + rowOffset][j + columnOffset]))
				{
//					System.out.println("Found in square: " + puzzle[i + rowOffset][j + columnOffset]);
					possibilities.remove(Integer.valueOf(puzzle[i + rowOffset][j + columnOffset]));
				}
			}
		}
	}
	
	//Returns the X and Y positions of the current cell 
	public String getXY()
	{
		return "X: " + xValue + " Y: " + yValue;
	}
	
	//Returns the only possibility left in the possibilites list
	public int getOnlyPossibility()
	{
		return possibilities.get(0);
	}
	
	//Finds the possibilities of the current cell by first going through the row, column, 
	public void findPossibilities()
	{
		//Searches through the row
		for(int j = 0; j < 9; j++)
		{
			if (possibilities.contains(puzzle[yValue][j]))
			{
//				System.out.println("Found on x:" +  puzzle[yValue][j]);
				possibilities.remove(Integer.valueOf(puzzle[yValue][j]));
			}
		}
		
		//Searches through the column
		for(int i = 0; i < 9; i++)
		{
			if (possibilities.contains(puzzle[i][xValue]))
			{
//				System.out.println("Found on y: " +  puzzle[i][xValue]);
				possibilities.remove(Integer.valueOf(puzzle[i][xValue]));
			}
		}
		
		//Searches through the square
		int rowOffset = (yValue / 3) * 3;
		int columnOffset = (xValue / 3) * 3;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				smallPuzzle[i][j] = puzzle[i + rowOffset][j + columnOffset];
				if (possibilities.contains(puzzle[i + rowOffset][j + columnOffset]))
				{
//					System.out.println("Found in square: " + puzzle[i + rowOffset][j + columnOffset]);
					possibilities.remove(Integer.valueOf(puzzle[i + rowOffset][j + columnOffset]));
				}
			}
		}
		
//		printSmallPuzzle();
	}
	
	//Prints the possibilities ArrayList
	public void printPossibilities()
	{
		System.out.print("Cell at: " + xValue + " " + yValue + ":  ");
		for(int i = 0; i < possibilities.size();i++)
		{
			System.out.print(possibilities.get(i) + " ");
		}
		System.out.println();
	}
	
	public void printSmallPuzzle()
	{
		for(int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				System.out.print(smallPuzzle[i][j] + " ");
			}
			System.out.println();
		}
	}
}
