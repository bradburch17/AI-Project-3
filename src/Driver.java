import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Driver class for Project 3 in CSC330 Artificial Intelligence at DePauw University. 
 * Contains code to solve a sudoku puzzle
 * 
 * Created 3-12-17
 * @author Brad Burch and Katherine Martin  
 */

public class Driver {
	
	//Main method 
	public static void main(String[] args)
	{
		String fileName = new String();
		Scanner reader = new Scanner(System.in);
		int[][] puzzle = new int[9][9];
		
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

}
