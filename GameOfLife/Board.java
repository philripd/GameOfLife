import java.util.*;

public class Board
{
    private int x;                      // Number of columns
    private int y;                      // Number of rows
    private int currentGeneration = 1;  // Current generation index
    private int neighbors;              // Number of a cell's neighbors
    private char[][] gameBoard;         // Actual game board
    private char[][] tempBoard;         // Temporary game board

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

    // Return number of columns
    public int getColumns()
    {
        return this.x;
    }

    // Return number of rows
    public int getRows()
    {
        return this.y;
    }

    // Return 0 if cell is '0'
    // Return 1 if cell is 'X'
    public int getCell(int column, int row)
    {
        if (gameBoard[row][column] == '0')
        {
            return 0;
        }
        return 1;
    }

    // Set cell to '0' or 'X'
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

    // Compute next generation
    public void computeNextGeneration(int numGenerations) throws InterruptedException
    {
        System.out.println();
        System.out.println("Generation: " + currentGeneration);
        System.out.println();

        // Prints current generation
        print();

        // Prints board on 1s (1000ms) intervals
        Thread.sleep(1000);

        // Creating temporary board, tempBoard
        tempBoard = new char[y][x];

        // Computing next generation values and assigning to tempBoard
        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < x; j++)
            {
                // Check amount of neighbors
                neighbors = calculateNeighbors(y, x);
                
                // If cell is currently alive
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

                // If cell is currently dead
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
        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < x; j++)
            {
                gameBoard[i][j] = tempBoard[i][j];
            }
        }

        // Increment current generation counter
        currentGeneration++;

        // Method recursively calls itself
        if (numGenerations > 1)
        {
            computeNextGeneration(numGenerations - 1);
        }
    }

    // Print out current board
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
    
    // Check if cell has a Neighbor
    private int calculateNeighbors(int row, int column)
    {
        int numNeighbors = 0;

        /*
        
        if cell is in first row
        {
            if cell is in first column          (top left corner)
                check bottom, bottom right, right
            else if cell is in last column      (top right corner)
                check left, bottom left, bottom
            else
                check left, bottom left, bottom, bottom right, right
        }
        else if cell is in last row
        {
            if cell is in first column          (bottom left corner)
                check top, top right, right
            else if cell is in last column      (bottom right corner)
                check left, top left, top
            else
                check left, top left, top, top right, right
        }
        else if cell is in first column
        {
            check top, top right, right, bottom right, bottom
        }
        else if cell is in last column
        {
            check top, top left, left, bottom left, bottom
        }
        else
        {
            check all eight cells around
        }

        */
        
        return numNeighbors;
    }
}

/*
Pseudocode

X X X X X
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
if cell is in first row
{
    if cell is in first column          (top left corner)
        check bottom, bottom right, right
    else if cell is in last column      (top right corner)
        check left, bottom left, bottom
    else
        check left, bottom left, bottom, bottom right, right
}

0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
X X X X X
else if cell is in last row
{
    if cell is in first column          (bottom left corner)
        check top, top right, right
    else if cell is in last column      (bottom right corner)
        check left, top left, top
    else
        check left, top left, top, top right, right
}

0 0 0 0 0
X 0 0 0 0
X 0 0 0 0
X 0 0 0 0
0 0 0 0 0
else if cell is in first column
{
    check top, top right, right, bottom right, bottom
}

0 0 0 0 0
0 0 0 0 X
0 0 0 0 X
0 0 0 0 X
0 0 0 0 0
else if cell is in last column
{
    check top, top left, left, bottom left, bottom
}

0 0 0 0 0
0 X X X 0
0 X X X 0
0 X X X 0
0 0 0 0 0
else
{
    check all eight cells around
}

*/