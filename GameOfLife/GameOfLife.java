import java.io.*;
import java.util.*;

public class GameOfLife
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        FileInputStream fileByteStream;
        Scanner s = new Scanner(System.in);
        Scanner fileReader;
        String fileName = "";
        int numGenerations;

        // Prompt user for file name
        System.out.println("Enter file name: ");
        fileName = s.nextLine();
        
        // Create board
        fileByteStream = new FileInputStream(fileName);
        fileReader = new Scanner(fileByteStream);
        Board board = new Board(fileReader);

        // Prompt user for number of generations to run
        System.out.println("Enter how many generations to compute: ");
        numGenerations = s.nextInt();

        // Tests
        /*
        board.setCell(0, 1, 1);
        board.setCell(0, 2, 1);
        board.setCell(0, 3, 1);
        */

        // Compute generations
        board.computeNextGeneration(numGenerations);
        
        fileByteStream.close();
        fileReader.close();
        s.close();
    }
}