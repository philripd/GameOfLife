/*******************************************************************
*   File: GameOfLife.java
*   Authors: P. Dang, C. McCarry, D. Phung
*   Class: CS 1400 – Introduction to Programming and Problem Solving
*
*   Assignment: Program Assignment 5
*   Date last modified: 12/3/2019
*
*   Purpose: Implements Conway's Game of Life given a String filename
*   and an integer number of generations to compute as inputs.
*
*******************************************************************/

import java.io.*;
import java.util.*;

public class GameOfLife
{
    public static void main(String[] args) throws IOException
    {
        FileInputStream fileByteStream;
        Scanner s = new Scanner(System.in);
        Scanner fileReader;
        String fileName = "";               // File name
        int numGenerations;                 // Number of generations

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

        // Compute generations
        board.computeNextGeneration(numGenerations);
        
        fileByteStream.close();
        fileReader.close();
        s.close();
    }
}