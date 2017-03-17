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
	private static ArrayList<Cell> solving = new ArrayList<Cell>();
	
	public int[][] getPuzzle()
	{
		return puzzle;
	}
	
	//Checks if the puzzle is solved by taking the total of the row and ensuring it is equal to 45
	public static boolean isSolved()
	{
		int total;
		for(int i = 0; i < 9; i++)
		{
			total = 0;
			for(int j = 0; j < 9; j++)
			{
				total += puzzle[i][j];
			}
			if(!(total == 45))
			{
				return false;
			}
		}
		return true;
	}
	
	//Checks if a specific cell is in the solving ArrayList
	public static boolean isInSolving(Cell cell)
	{
		int x, y;
		for(Cell c : solving)
		{
			if(c.getXValue() == cell.getXValue() && c.getYValue() == cell.getYValue())
			{
				return true;
			}
		}
		return false;
	}
	
	//Removes a cell from solving based on the coordinates of the cell
	public static void removeFromSolving(int i, int j)
	{
		Cell remove = null;
		for(Cell cell : solving)
		{
			if (cell.getXValue() == i && cell.getYValue() == j)
			{
				remove = cell;
			}
		}
		
		solving.remove(remove);
	}
	
	//Checks if there is only one possibility based on the column, row, square
	public static void checkMedium(Cell cell)
	{
		ArrayList<Integer> originalCellPossibilities = cell.getPossibilities(); //Sets the current cell possibilities 
		ArrayList<Integer> newCellPossibilities;
		ArrayList<Integer> notSame = new ArrayList<Integer>(); //List for the numbers not the same
		
		//Adding all numbers to the notSame list
		for(Cell c : solving)
		{
			//Checks the current cell and new cell's row and column. Currently not doing the square, because it required more code.
			if (c.getXValue() == cell.getXValue() || cell.getYValue() == c.getYValue())
			{
				newCellPossibilities = c.getPossibilities();
				for(int i = 0; i < originalCellPossibilities.size(); i++)
				{
					//Checks if any of the possibilities in the original cell are within the new cell 
					if (newCellPossibilities.contains(originalCellPossibilities.get(i)))
					{
						//Do nothing
					}
					else 
					{
						//Checks if the cell already contains the number
						if (!notSame.contains(originalCellPossibilities.get(i)))
						{
							notSame.add(originalCellPossibilities.get(i));
						}
					}	
				}
			}
		}
		
		System.out.print("What is in the notSame arrayList: ");
		for(int i = 0; i < notSame.size(); i++)
		{
			System.out.print(notSame.get(i) + " ");
		}
		System.out.println();
		
		//Checks if the numbers are in the notSame list. 
		for(Cell c : solving)
		{
			if (cell.getXValue() == c.getXValue() || cell.getYValue() == c.getYValue())
			{
				if(notSame.contains(puzzle[c.getYValue()][c.getXValue()]))
				{
					//Remove the number if it is found in the puzzle and notSame (indicating that it is a possibility in another cell)
					//Currently does not work
					notSame.remove(puzzle[c.getYValue()][c.getXValue()]);
				}
			}
		}
		
		System.out.print("What is in the notSame arrayList after checking again: ");
		for(int i = 0; i < notSame.size(); i++)
		{
			System.out.print(notSame.get(i) + " ");
		}
		System.out.println();
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

			while(!isSolved())
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
							if (!isInSolving(cell))
							{
//								System.out.println("Adding to solving");
								solving.add(cell);
							}
							
							checkMedium(cell);
							
							if (cell.getPossibilitySize() == 0)
							{
								System.out.println("Possibility 0. Printing grid: ");
								printPuzzle();
							}
							
							if (cell.getPossibilitySize() == 1)
							{
//								System.out.println("Adding: " + cell.getXY());
								puzzle[i][j] = cell.getOnlyPossibility();
								removeFromSolving(i, j);
							}
						}
					}
				}
				solving.clear();
			}
			System.out.println("Solving size: " + solving.size());
			System.out.println("All cell possibilities: ");
			for(Cell cell : allCells)
			{
				cell.printPossibilities();				
			}
			
			System.out.println("Solved puzzle: ");
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
