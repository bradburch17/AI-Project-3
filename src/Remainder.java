import java.util.ArrayList;

public class Remainder {

	private static ArrayList<Integer> possibilities;
	private static int iValue;
	private static int jValue;
	private static int outcome;
	private static Driver driver = new Driver();
	private static int[][] puzzle = driver.getPuzzle();
	private static int[][] smallPuzzle = new int[3][3];
	
	public Remainder()
	{
		possibilities = new ArrayList<Integer>() {{
		    add(1); add(2);  add(3);
		    add(4); add(5);  add(6);
		    add(7); add(8);  add(9);
		}};
	}
	
	public static int getiValue() {
		return iValue;
	}
	
	public static void setiValue(int iValue) {
		Remainder.iValue = iValue;
	}
	
	public static int getjValue() {
		return jValue;
	}
	
	public static void setjValue(int jValue) {
		Remainder.jValue = jValue;
	}
	
	public static int getOutcome() {
		return outcome;
	}
	
	public static void setOutcome(int outcome) {
		Remainder.outcome = outcome;
	}
	
	public static int getPossibilitySize()
	{
		return possibilities.size();
	}
	
	public static int getOnlyPossibility()
	{
		return possibilities.get(0);
	}
	
	public void findPossibilities()
	{
		for(int j = 0; j < 9; j++)
		{
			
			if (possibilities.contains(puzzle[iValue][j]))
			{
//				System.out.println("Found on x:" +  puzzle[iValue][j]);
				possibilities.remove(Integer.valueOf(puzzle[iValue][j]));
				
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			if (possibilities.contains(puzzle[i][jValue]))
			{
//				System.out.println("Found on y: " +  puzzle[i][jValue]);
				possibilities.remove(Integer.valueOf(puzzle[i][jValue]));
			}
		}
		
		fillSmallPuzzle();
	}
	
	public static void fillSmallPuzzle()
	{
		if(iValue < 3)
		{
			if (jValue < 3)
			{
				for(int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						smallPuzzle[i][j] = puzzle[i][j];
					}
				}
			}
			else if (jValue < 6)
			{
				for(int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						smallPuzzle[i][j] = puzzle[i][j + 3];
					}
				}
			}
			else if (jValue < 9)
			{
				for(int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						smallPuzzle[i][j] = puzzle[i][j + 6];
					}
				}
			}
		}
		else if(iValue < 6)
		{
			if (jValue < 3)
			{
				for(int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						smallPuzzle[i][j] = puzzle[i + 3][j];
					}
				}
			}
			else if (jValue < 6)
			{
				for(int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						smallPuzzle[i][j] = puzzle[i + 3][j + 3];
					}
				}
			}
			else if (jValue < 9)
			{
				for(int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						smallPuzzle[i][j] = puzzle[i + 3][j + 6];
					}
				}
			}
		}
		else if(iValue < 9)
		{
			if (jValue < 3)
			{
				for(int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						smallPuzzle[i][j] = puzzle[i + 6][j];
					}
				}
			}
			else if (jValue < 6)
			{
				for(int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						smallPuzzle[i][j] = puzzle[i + 6][j + 3];
					}
				}
			}
			else if (jValue < 9)
			{
				for(int i = 3; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						smallPuzzle[i][j] = puzzle[i + 6][j + 6];
					}
				}
			}
		}
		
//		printSmallPuzzle();
		solveSmallPuzzle();
	}
	
	public static void solveSmallPuzzle()
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if (possibilities.contains(smallPuzzle[i][j]))
				{
//					System.out.println("Found in puzzle: " +  smallPuzzle[i][j]);
					possibilities.remove(Integer.valueOf(smallPuzzle[i][j]));
				}
			}
		}
	}
	
	public static void printPossibilities()
	{
		for(int i = 0; i < possibilities.size(); i++)
		{
			System.out.print(possibilities.get(i) + " ");
		}
		System.out.println();
	}
	
	public static void printSmallPuzzle()
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
