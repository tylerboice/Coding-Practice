// ColorMaze.java by Tyler Boice 8/12/17

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ColorMaze {
    private char[] legend;
    private char[][] maze;
    private int size;
    private String path = "";

    // Initializes the ColorMaze object
    public ColorMaze() {
        legend = null;
        maze = new char[1000][1000];
        size = 0;
    }

    // Generates a maze and legend based off a text file
    public void initializeMaze(String filename) {
        try {
            // Initialize variables for reading file and tracking the current line
            String currentLine = null;
            int mazeLine = 0;
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            // Set up the legend for the maze which is always the first line of text
            setLegend(br.readLine().replace(" ", ""));

            // While the maze still has lines, translate the lines into the maze array
            while ((currentLine = br.readLine()) != null) {
                setMaze(mazeLine++, (currentLine.replace(" ", "")));
                size = mazeLine;
            }
            br.close();
        }
        // If the file is not found print an error
        catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
        }
        // If the file cannot be read print an error
        catch (IOException e) {
            System.out.println("File could not be read");
        }
    }

    // Sets the legend for the maze
    public void setLegend(String legendString) {
        this.legend = legendString.toCharArray();
    }

    // Returns the legend in String format
    public String legendString() {
        // Create a temp string to hold the information that needs to be printed
        String tempString = "";
        for (int i = 0; i < legend.length; i++) {
            tempString += legend[i];
        }
        return tempString;
    }

    // Set the values for the maze
    public void setMaze(int row, String mazeString) {
        for (int i = 0; i < mazeString.length(); i++) {
            this.maze[row][i] = mazeString.charAt(i);
        }
    }

    // Returns the string for the maze
    public String mazeString() {
        // Creates a temp string to hold the information that needs to be printed
        String tempString = "";
        // Loop though every items in the maze array and put its value into tempString
        for (int row = 0; row < size*size; row++) {
            for (int col = 0; col < size; col++) {
                tempString += maze[row][col];
            }
            tempString += "\n";
        }
        return tempString.trim();
    }

    // Returns the string for the path
    public String pathString(){
        findPathEntrance();
        return path.trim();
    }

    // Looks for the beginning of the path
    public void findPathEntrance() {
        // All paths must start in the bottom row of the maze
        for ( int col = 0; col < size-1; col++){
            // If the value at a given point is in the legend, then begin finding a path and generating the path string
            if(isAllowed(maze[size-1][col])){
                path += "(" + col + ", " + (size-1) + ")\n";
                // The beginning of the path is always assumed to start upwards since downards is not an option
                if (pathRecursion(size-1, col, 'u')) {
                    return;
                }
            }
        }
    }

    // Determines if a letter is allowed by the legend
    public boolean isAllowed(char letter){
        // For each letter in the legend return true if the letter is in the legend and false if it is not
        for( char item : legend) {
            if( item == letter) {
                return true;
            }
        }
        return false;
    }

    // Looks for paths to follow by checking, up, right, down, and left
    public boolean pathRecursion(int row, int col, char direction) {
        // This function will prioritize going up and to the right since the path attempts to start form the bottom left
        // If the path reaches the top row, then the path is complete
        if (row == 0) {
            return true;
        }
        try {
            // The path will go up, unless the path is headed down
            if (isAllowed(maze[row - 1][col]) && direction != 'd') {
                path += "(" + col + ", " + (row - 1) + ")\n";
                pathRecursion(row - 1, col, 'u');
            }
            // The path will go right, unless the path is headed left
            else if (isAllowed(maze[row][col + 1]) && direction != 'l') {
                path += "(" + (col + 1) + ", " + row + ")\n";
                pathRecursion(row, col + 1, 'r');
            }
            // The path will go left, unless the path is headed right
            else if (isAllowed(maze[row][col - 1]) && direction != 'r') {
                path += "(" + (col - 1) + ", " + row + ")\n";
                pathRecursion(row, col - 1, 'l');
            }
            // The path will go down, unless the path is headed up
            else if (isAllowed(maze[row + 1][col]) && direction != 'u') {
                path += "(" + col + ", " + (row + 1) + ")\n";
                pathRecursion(row + 1, col, 'd');
            }
        }
        catch (IndexOutOfBoundsException e){
        }
        return false;
    }
}