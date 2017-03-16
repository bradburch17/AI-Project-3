import java.util.ArrayList;

public class Cell {

	private int xValue;
	private int yValue;
	private ArrayList<Integer> possibilities;
	private Driver d = new Driver();
	private int[][] puzzle = d.getPuzzle();
	private int[][] smallPuzzle = new int[3][3];
	
	public Cell(int xValue, int yValue)
	{
		possibilities = new ArrayList<Integer>() {{
		    add(1); add(2);  add(3);
		    add(4); add(5);  add(6);
		    add(7); add(8);  add(9);
		}};
		
		this.xValue = xValue;
		this.yValue = yValue;
	}
	
	public int getPossibilitySize()
	{
		return possibilities.size();
	}
	
	public int getOnlyPossibility()
	{
		return possibilities.get(0);
	}
	
	public void printPossibilities()
	{
		System.out.print("Cell at: " + xValue + " " + yValue + ":  ");
		for(int i = 0; i < possibilities.size();i++)
		{
			System.out.print(possibilities.get(i) + " ");
		}
		System.out.println();
	}
	
	public void findPossibilities()
	{
		//Searches through the row
		for(int j = 0; j < 9; j++)
		{
			if (possibilities.contains(puzzle[yValue][j]))
			{
				System.out.println("Found on x:" +  puzzle[xValue][j]);
				possibilities.remove(Integer.valueOf(puzzle[yValue][j]));
			}
		}
		
		//Searches through the column
		for(int i = 0; i < 9; i++)
		{
			if (possibilities.contains(puzzle[i][xValue]))
			{
				System.out.println("Found on y: " +  puzzle[i][xValue]);
				possibilities.remove(Integer.valueOf(puzzle[i][xValue]));
			}
		}
		
		//Searches through the square
		int rowOffset = (xValue / 3) * 3;
		int columnOffset = (yValue / 3) * 3;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				smallPuzzle[i][j] = puzzle[i + rowOffset][j + columnOffset];
				if (possibilities.contains(puzzle[i + rowOffset][j + columnOffset]))
				{
					System.out.println("Found in square: " + puzzle[i + rowOffset][j + columnOffset]);
					possibilities.remove(Integer.valueOf(puzzle[i + rowOffset][j + columnOffset]));
				}
			}
		}
		
//		printSmallPuzzle();
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
