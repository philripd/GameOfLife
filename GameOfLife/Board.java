import java.io.*;
import java.util.*;

import javax.lang.model.util.ElementScanner6;

public class Board
{
    private int x;                  // Columns
    private int y;                  // Rows
    private char[][] gameBoard;     // Actual game board
    private char[][] tempBoard;     // Temporary game board

    public Board(Scanner fileReader)
    {
        // Read board size
        x = fileReader.nextInt();
        y = fileReader.nextInt();
        fileReader.nextLine();

        gameBoard = new char[y][x];

        // Set initial board values
        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < x; j++)
            {
                gameBoard[i][j] = fileReader.next().charAt(0);
            }
        }
    }


    public int getColumns()
    {
        return this.x;
    }

    public int getRows()
    {
        return this.y;
    }

    public int getCell(int column, int row)
    {
        if (gameBoard[row][column] == '0')
        {
            return 0;
        }
        return 1;
    }

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

    public void computeNextGeneration(int generation)
    {
        
    }

    public void print()
    {
        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < x; j++)
            {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
}