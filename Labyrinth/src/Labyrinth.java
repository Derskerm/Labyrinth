import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/*

	Coded by:
	Modified on:

*/

public class Labyrinth {

	private static final int rows = 20;
	private static final int cols = 20;
	private char[][] data;

	// Constructs an empty grid  
	public Labyrinth() {

	}

	// Constructs the grid defined in the file specified
	public Labyrinth(String filename) {
		data = readData(filename);
	}

	// Finds a path through the labyrinth and returns the number of moves required to exit
	public int findPath() {
		char c = 'C';
		for (int x = 0; x < data.length; x++) {
			for (int y = 0; y < data[x].length; y++) {
				if (data[x][y] == c) {
					return findPath(x, y, false);
				}
			}
		}
		return -1;
	}

	// Private recursive version of findPath()
	private int findPath(int x, int y, boolean cloak, int steps) {
		//System.out.print(this);
		char d = data[x][y];
		System.out.println(d);
		 if (d == '#' || d == '!' || (d == 'A' || d == '?') && !cloak) {
			 return -1;
		 } else if (d == 'X') {
			 if (cloak) {
				 data[x][y] = '!';
			 } else {
				 data[x][y] = '?';
			 }
			 return ++steps;
		 } else {
			 if (d == '@') {
				 cloak = true;
			 }
			 if (cloak) {
				 data[x][y] = '!';
			 } else {
				 data[x][y] = '?';
			 }
			 
			 int[] steps = new int[4];
			 
			 steps[0] = findPath(x,y-1,cloak,++steps);
			 steps[1] = findPath(x,y+1,cloak,steps);
		 	 steps[2] = findPath(x-1,y,cloak,steps);
			 steps[3] = findPath(x+1,y,cloak,steps);
			 
			 for () {
				 
			 }
			 
			 data[x][y] = ' ';
			 return false;
		 }
	}

	// Formats this grid as a String to be printed (one call to this method returns the whole multi-line grid)
	public String toString() {
		String s = "";
		for (int x = 0; x < data.length; x++) {
			for (int y = 0; y < data[x].length; y++) {
				s += data[x][y];
			}
			s += "\n";
		}
		return s;
	}

	public char[][] readData (String filename) {
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			char[][] gameData = new char[rows][cols];

			int count = 0;

			FileReader reader = null;
			try {
					reader = new FileReader(dataFile);
					Scanner in = new Scanner(reader);


					while (in.hasNext() && count < rows) {
						String line = in.nextLine();
						for(int i = 0; i < line.length(); i++)
							gameData[count][i] = line.charAt(i);

						count++;
					}

			} catch (IOException ex) {
				System.out.println("File cannot be read.");
				return null;
			} catch (NumberFormatException ex) {
				System.out.println("File is in the wrong format.");
				return null;
			} finally {
				try {
					reader.close();
				} catch (IOException ex) {
					System.out.println("File cannot be closed.");
					return null;
				}
			}
			return gameData;
		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
    }

}