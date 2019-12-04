/*******************************************************************
*   File: Board.java
*   Authors: P. Dang, C. McCarry, D. Phung
*   Class: CS 1400 – Introduction to Programming and Problem Solving
*
*   Assignment: Program Assignment 5
*   Date last modified: 12/3/2019
*
*   Purpose: Creates and manipulates the boards needed to implement
*   Conway's Game of Life.
*
*******************************************************************/

import java.util.*;

public class Board
{
    private int numColumns;             // Number of columns
    private int numRows;                // Number of rows
    private int currentGeneration = 1;  // Current generation index
    private int neighbors;              // Number of a cell's neighbors
    private char[][] gameBoard;         // Actual game board
    private char[][] tempBoard;         // Temporary game board

    // Method: Board
    // Purpose: constructor method that creates a new board
    public Board(Scanner fileReader)
    {
        // Read board size
        numColumns = fileReader.nextInt();
        numRows = fileReader.nextInt();
        fileReader.nextLine();

        // Create board
        gameBoard = new char[numRows][numColumns];

        // Set initial board values
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numColumns; j++)
            {
                gameBoard[i][j] = fileReader.next().charAt(0);
            }
        }
    }

    // Method: getColumns
    // Purpose: return number of columns
    public int getColumns()
    {
        return this.numColumns;
    }

    // Method: getRows
    // Purpose: return number of rows
    public int getRows()
    {
        return this.numRows;
    }

    // Method: getCell
    // Purpose:
    // - return 0 if cell is '0' or out of bounds
    // - return 1 if cell is 'X'
    public int getCell(int column, int row)
    {
        if (column < 0 || column > numColumns || row < 0 || row > numRows)
        {
            return 0;
        }
        if (gameBoard[row][column] == '0')
        {
            return 0;
        }
        return 1;
    }

    // Method: setCell
    // Purpose: set cell to '0' or 'X'
    public void setCell(int column, int row, int value)
    {
        if (value == 0)
        {
            gameBoard[row][column] = '0';
        }
        else if (value == 1)
        {
            gameBoard[row][column] = 'X';
        }
        else
        {
            System.out.println("Invalid input");
        }
    }

    // Method: computeNextGeneration
    // Purpose: compute next generation and assign to gameBoard
    public void computeNextGeneration(int numGenerations)
    {
        System.out.println();
        System.out.println("Generation: " + currentGeneration);
        System.out.println();

        // Print current generation
        print();

        // Create temporary board
        tempBoard = new char[numRows][numColumns];

        // Compute next generation values and assigning to tempBoard
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numColumns; j++)
            {
                // Check amount of neighbors
                neighbors = calculateNeighbors(i, j);

                // Set cell if cell is currently alive
                if (gameBoard[i][j] == 'X')
                {
                    if (neighbors < 2 || neighbors > 3)
                    {
                        tempBoard[i][j] = '0';
                    }
                    else
                    {
                        tempBoard[i][j] = 'X';
                    }
                }

                // Set cell if cell is currently dead
                if (gameBoard[i][j] == '0')
                {
                    if (neighbors == 3)
                    {
                        tempBoard[i][j] = 'X';
                    }
                    else
                    {
                        tempBoard[i][j] = '0';
                    }
                }
            }
        }

        // Update gameBoard using tempBoard
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numColumns; j++)
            {
                gameBoard[i][j] = tempBoard[i][j];
            }
        }

        // Increment current generation counter
        currentGeneration++;

        // Recursive call
        if (numGenerations > 1)
        {
            computeNextGeneration(numGenerations - 1);
        }
    }

    // Method: print
    // Purpose: print out current board
    public void print()
    {
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numColumns; j++)
            {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method: calculateNeighbors
    // Purpose: look for and return the number of neighbors a cell has
    private int calculateNeighbors(int row, int column)
    {
        int numNeighbors = 0;
        
        // up = row - 1;
        // down = row + 1;
        // left = column - 1;
        // right = column + 1;

        // If cell is in first row
        if (row == 0)
        {
          if (column == 0)  // top left corner cell
          {
            // Check right
            if (gameBoard[row][column+1] == 'X')
            {
              numNeighbors++;
            }
            // Check bottom
            if (gameBoard[row+1][column] == 'X')
            {
              numNeighbors++;
            }
            // Check bottom right
            if (gameBoard[row+1][column+1] == 'X')
            {
              numNeighbors++;
            }
          }
          else if (column == numColumns-1)  // top right corner cell
          {
            // Check left
            if (gameBoard[row][column-1] == 'X')
            {
              numNeighbors++;
            }
            // Check bottom left
            if (gameBoard[row+1][column-1] == 'X')
            {
              numNeighbors++;
            }
            // Check bottom
            if (gameBoard[row+1][column] == 'X')
            {
              numNeighbors++;
            }
          }
          else
          {
            // Check left
            if (gameBoard[row][column-1] == 'X')
            {
              numNeighbors++;
            }
            // Check right
            if (gameBoard[row][column+1] == 'X')
            {
              numNeighbors++;
            }
            // Check bottom left
            if (gameBoard[row+1][column-1] == 'X')
            {
              numNeighbors++;
            }
            // Check bottom
            if (gameBoard[row+1][column] == 'X')
            {
              numNeighbors++;
            }
            // Check bottom right
            if (gameBoard[row+1][column+1] == 'X')
            {
              numNeighbors++;
            }
          }
        }
        // If cell is in last row
        else if (row == numRows-1)
        {
          if (column == 0)  // bottom left corner cell
          {
            // Check top
            if (gameBoard[row-1][column] == 'X')
            {
              numNeighbors++;
            }
            // Check top right
            if (gameBoard[row-1][column+1] == 'X')
            {
              numNeighbors++;
            }
            // Check right
            if (gameBoard[row][column+1] == 'X')
            {
              numNeighbors++;
            }
          }
          else if (column == numColumns-1)  // bottom right corner cell
          {
            // Check top left
            if (gameBoard[row-1][column-1] == 'X')
            {
              numNeighbors++;
            }
            // Check top
            if (gameBoard[row-1][column] == 'X')
            {
              numNeighbors++;
            }
            // Check left
            if (gameBoard[row][column-1] == 'X')
            {
              numNeighbors++;
            }
          }
          else
          {
            // Check top left
            if (gameBoard[row-1][column-1] == 'X')
            {
              numNeighbors++;
            }
            // Check top
            if (gameBoard[row-1][column] == 'X')
            {
              numNeighbors++;
            }
            // Check top right
            if (gameBoard[row-1][column+1] == 'X')
            {
              numNeighbors++;
            }
            // Check left
            if (gameBoard[row][column-1] == 'X')
            {
              numNeighbors++;
            }
            // Check right
            if (gameBoard[row][column+1] == 'X')
            {
              numNeighbors++;
            }
          }
        }
        // If cell is in first column
        else if (column == 0)
        {
          // Check top
          if (gameBoard[row-1][column] == 'X')
          {
            numNeighbors++;
          }
          // Check top right
          if (gameBoard[row-1][column+1] == 'X')
          {
            numNeighbors++;
          }
          // Check right
          if (gameBoard[row][column+1] == 'X')
          {
            numNeighbors++;
          }
          // Check bottom
          if (gameBoard[row+1][column] == 'X')
          {
            numNeighbors++;
          }
          // Check bottom right
          if (gameBoard[row+1][column+1] == 'X')
          {
            numNeighbors++;
          }
        }
        // If cell is in last column
        else if (column == numColumns-1)
        {
          // Check top left
          if (gameBoard[row-1][column-1] == 'X')
          {
            numNeighbors++;
          }
          // Check top
          if (gameBoard[row-1][column] == 'X')
          {
            numNeighbors++;
          }
          // Check left
          if (gameBoard[row][column-1] == 'X')
          {
            numNeighbors++;
          }
          // Check bottom left
          if (gameBoard[row+1][column-1] == 'X')
          {
            numNeighbors++;
          }
          // Check bottom
          if (gameBoard[row+1][column] == 'X')
          {
            numNeighbors++;
          }
        }
        // Remaining center cells
        else
        {
          // Check top left
          if (gameBoard[row-1][column-1] == 'X')
          {
            numNeighbors++;
          }
          // Check top
          if (gameBoard[row-1][column] == 'X')
          {
            numNeighbors++;
          }
          // Check top right
          if (gameBoard[row-1][column+1] == 'X')
          {
            numNeighbors++;
          }
          // Check left
          if (gameBoard[row][column-1] == 'X')
          {
            numNeighbors++;
          }
          // Check right
          if (gameBoard[row][column+1] == 'X')
          {
            numNeighbors++;
          }
          // Check bottom left
          if (gameBoard[row+1][column-1] == 'X')
          {
            numNeighbors++;
          }
          // Check bottom
          if (gameBoard[row+1][column] == 'X')
          {
            numNeighbors++;
          }
          // Check bottom right
          if (gameBoard[row+1][column+1] == 'X')
          {
            numNeighbors++;
          }
        }
        return numNeighbors;
    }
}