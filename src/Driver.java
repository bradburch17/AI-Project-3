import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Driver class for Project 3 in CSC330 Artificial Intelligence at DePauw University. 
 * Contains code to solve a Sudoku puzzle
 * 
 * Created 3-12-17
 * @author Brad Burch and Katherine Martin  
 */

public class Driver {
	private static int[][] puzzle = new int[9][9];
	
	public int[][] getPuzzle()
	{
		return puzzle;
	}
	
	public static void setPuzzle(int i, int j, int outcome)
	{
		puzzle[i][j] = outcome;
	}
	
	//Main method 
	public static void main(String[] args)
	{
		String fileName = new String();
		Scanner reader = new Scanner(System.in);
		
		
		System.out.println("This program is used to solve a sudoku puzzle");
		System.out.print("Please enter a file you would like to create the alogrithm: ");
		
		fileName = reader.nextLine();
		
		try
		{
			File file = new File (fileName);
			Scanner scanner = new Scanner(file);
			Solving solving = new Solving();
			
			System.out.println("Initial solution: ");
			while (scanner.hasNext())
			{
				for (int i = 0; i < 9; i ++)
				{
					for (int j = 0; j < 9; j++)
					{
						puzzle[i][j] = scanner.nextInt();
						System.out.print(puzzle[i][j] + " ");
						
					}
					System.out.println();
				}
			}
			System.out.println();
			
			scanner.close();
			int count = 0;
			do
			{
				for(int i = 0; i < 9; i++)
				{
					for(int j = 0; j < 9; j++)
					{
						System.out.println("Solving for: " + i + " " + j);
						if (puzzle[i][j] == 0)
						{
							Remainder r = new Remainder();
							
							r.setiValue(i);
							r.setjValue(j);
//							System.out.println("The R values: " + r.getiValue() + " " + r.getjValue());
							if (!solving.isInSolving(i, j))
							{
								System.out.println("ADDING TO SOLVING");
								solving.addToSolving(r);
							}
//							solving.getSolving();
	
							r.findPossibilities();
//							System.out.print("Printing possibilities: ");
//							r.printPossibilities();
							if (r.getPossibilitySize() == 1)
							{
								puzzle[i][j] = r.getOnlyPossibility();
								solving.removeFromSolving(r);
							}
						}
					}
				}
				System.out.println("Solving size: " + solving.getSolvingSize());
				
			}
			while(solving.getSolvingSize() > 1);
			
			System.out.println("Puzzle after one iteration: ");
			printPuzzle();
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Unable to open the file " + fileName + ".");
		}
		catch(IOException e)
		{
			System.out.println("Could not read from " + fileName + ".");
		}
	}
	
	public static void printPuzzle()
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(puzzle[i][j] + " ");
			}
			System.out.println();
		}
	}

}
