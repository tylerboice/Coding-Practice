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

    public ColorMaze() {
        legend = null;
        maze = new char[1000][1000];
        size = 0;
    }

    public void initializeMaze(String filename) {
        // Initialize variables for reading file and tracking the lines
        try {
            String currentLine = null;
            int mazeLine = 0;
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            // setup the legend for the maze which is always the first line of text
            setLegend(br.readLine().replace(" ", ""));

            // While the maze still has lines, put the lines into the maze variable
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

    // set the legend for the maze
    public void setLegend(String legendString) {
        this.legend = legendString.toCharArray();
    }

    // Returns the legend in String format
    public String legendString() {
        String tempString = "";
        for (int i = 0; i < legend.length; i++) {
            tempString += legend[i];
        }
        return tempString;
    }

    //set the values for the maze
    public void setMaze(int row, String mazeString) {
        for (int i = 0; i < mazeString.length(); i++) {
            this.maze[row][i] = mazeString.charAt(i);
        }
    }

    // Returns the string for the maze
    public String mazeString() {
        String tempString = "";
        for (int row = 0; row < size*size; row++) {
            for (int col = 0; col < size; col++) {
                tempString += maze[row][col];
            }
            tempString += "\n";
        }
        return tempString.trim();
    }

    //Finds the first viable path through the maze
    public void findPath() {
        for ( int col = 0; col < size-1; col++){
            if(isAllowed(maze[size-1][col])){
                path += "(" + col + "," + (size-1) + ")\n";
                if (pathRecursion(size-1, col)) {
                    return;
                }
            }
        }
    }

    public String printPath(){
        findPath();
        return path.trim();
    }

    //determines if a letter is allowed by the legend
    public boolean isAllowed(char letter){
        for( char item : legend) {
            if( item == letter) {
                return true;
            }
        }
        return false;
    }

    // Looks for paths to follow by checking, up, right, down, and left
    public boolean pathRecursion(int row, int col) {
        // The condition to complete the maze is to reach the last row
        if (row == 0) {
            return true;
        }
        // go up if possible
        if (isAllowed(maze[row-1][col])) {
            path += "(" + col + "," + (row-1) + ")\n";
            pathRecursion(row-1, col);
        }
        // go right if possible
        else if(isAllowed(maze[row][col+1])) {
            path += "(" + (col + 1) + "," + row + ")\n";
            pathRecursion(row, col + 1);
        }
        // go down if possible
        else if(isAllowed(maze[row+1][col])) {
            path += "(" + col + "," + (row + 1) + ")\n";
            pathRecursion(row+1, col);
        }
        // go left if possible
        else if(isAllowed(maze[row][col-1])) {
            path += "(" + (col-1) + "," + row + ")\n";
            pathRecursion(row, col-1);
        }
        return false;
    }
}