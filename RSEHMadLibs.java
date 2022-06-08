// Rangganatha Siregar and Elaine Hsu
// 3/15/2022
// CS &141
// Lab #5
//
// The purpose of this program is to allow the user to play a game of Mad Libs.
// We got everything working perfectly.

import java.util.Scanner;
import java.io.*;

public class RSEHMadLibs
{
   public static void main (String [] args) throws FileNotFoundException
   {
       // printing instructions
       System.out.println("Welcome to the game of Mad Libs.");
       System.out.println("I will ask you to provide various words");
       System.out.println("and phrases to fill in a story.");
       System.out.println("The result will be written to an output file.");
       
       userChoice(); // calls method to let user choose C/V/Q
   }
   
   
   public static void userChoice() throws FileNotFoundException
   {    
      String answer = ""; // initializing answer
      System.out.println();
      
      do // do while to make sure the option runs once
      {
         System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
         Scanner sc = new Scanner(System.in);
         answer = sc.nextLine(); // prompts user to answer
      } 
      while (!answer.equals("C") && !answer.equals("c") && !answer.equals("V") &&
             !answer.equals("v") && !answer.equals("Q") && !answer.equals("q"));
   
      switch (answer)
      {
         case "C":
         case "c" :
            System.out.println();
            createFile() ; // calls method to create the mad-lib
            break;
            
         case "V":
         case "v" :
            System.out.println();
            viewFile(); // view the output file
            
         case "Q":
         case "q" : // quit the game
            System.exit(0);
       }    
   }
   
  public static void createFile() throws FileNotFoundException
  {
      Scanner readFile = new Scanner(new File("MyMadLib.txt")); // reads input file
      PrintStream out = new PrintStream (new File("MadLibs_Output.txt")); // output
            
      while (readFile.hasNextLine()) // checks if there is another line
      {
         String line = readFile.nextLine();
         Scanner readLine = new Scanner(line); // scans the line to find placeholders
         
         while (readLine.hasNext())
         {
            String word = readLine.next();
            
            if (word.startsWith("<") && word.endsWith(">"))
            {
               word = word.replace('<', ' '); // replacing the <-> in placeholders
               word = word.replace(">", "");
               word = word.replace('-', ' ');
               
               String aOrAn = aOrAn(word); // evaluate word, if the word uses an or a
             
               System.out.print("Please type " + aOrAn + word + ": ");
               
               Scanner input = new Scanner(System.in);
               String userInput = input.nextLine(); // user types their word
               
               out.print(userInput + " "); // putting user's word in the output
            }
            
            else
            {
               out.print(word + " "); // printing all the other words that are not
            }                         // placeholders
         }  
         
         if (!line.isEmpty())
         {
            out.println(); // make sure another line is made after each line is
         }                 // read 
      }
      
      System.out.println("Your mad-lib has been created!");
      userChoice(); // showing users the C/V/Q choice again
  }
  
  public static void viewFile() throws FileNotFoundException
  {
      Scanner readFile = new Scanner(new File("MadLibs_Output.txt")); // to print
                                                                      // output 
      while (readFile.hasNextLine()) 
      {
         String line = readFile.nextLine(); // getting the lines and printing them
         System.out.println(line);
      }
      
      userChoice(); // showing users the C/V/Q choice again
  }
  
  public static String aOrAn(String word)
  {
      char aAn = word.charAt(1); // extracting the first letter of the word
      String aOrAn = ""; // initializing
      
      if (aAn == 'a' || aAn == 'i' || aAn == 'u' || aAn == 'e' || aAn == 'o')
      {
          aOrAn = "an"; // if charAt(1) = vowels, use an
      }
               
      else 
      {
          aOrAn = "a";
      }
      
      return aOrAn; // return either a or an to be put before word
  }
   
}