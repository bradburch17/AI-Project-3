import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
	private static boolean solved = false;
	private static ArrayList<Cell> allCells = new ArrayList<Cell>();
	
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
			while(!solved)
			{
				for(int i = 0; i < 9; i++)
				{
					for(int j = 0; j < 9; j++)
					{
						System.out.println("Solving for: " + i + " " + j);
						if (puzzle[i][j] == 0)
						{
							Cell cell = new Cell(i, j);
							cell.findPossibilities();
							allCells.add(cell);
							
							if (cell.getPossibilitySize() == 1)
							{
								puzzle[i][j] = cell.getOnlyPossibility();
							}
						}
					}
				}
				count++;
				if (count > 3)
					solved = true;
			}
			
			System.out.println("All cell possibilities: ");
			for(Cell cell : allCells)
			{
				cell.printPossibilities();				
			}

			
			System.out.println("Puzzle after: ");
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
