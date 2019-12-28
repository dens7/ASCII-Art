import java.util.Scanner;

/**
 * ////////////////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION///////////////////////////// 
 * // Title: Ascii Art  Program 
 * // Files: StackADT.java, DrawingChange.java, DrawingStack.java, DrawingStackIterator.java,
 * //        AsciiArt.java, AsciiTest.java, Canvas.java, Node.java  
 * // Course: CS 300 Fall term 2018 
 * // Author: Vedaant Tambi 
 * // Email: tambi@wisc.edu 
 * // Lecturer's Name: MOUNA AYARI BEN HADJ KACEM 
 * //////////////////////////////////////// CREDIT OUTSIDE HELP ////////////////////////////////////
 * //                                              NONE                                           //
 * //////////////////////////////////////// 100 COLUMNS WIDE ///////////////////////////////////////
 */

/**
 * This class is the driver class of the Ascii Art programme and implements the other classes of the
 * of the programme
 * 
 * @author Vedaant Tambi
 * @version 1.0
 * @since 1.0
 */
public class AsciiArt {

  /**
   * This function is reponsible for getting valid int input from the user
   * 
   * @param scnr refers to the input scanner
   * @return refers to the valid int input given by the user
   */
  private static int intInputGetter(Scanner scnr) {

    int var = 0; // if user does not enter correct input, zero is returned
    // if an integer is not the next variable the scanner moves forward
    while (!scnr.hasNextInt())
      scnr.next();
    var = scnr.nextInt(); // the first int entered as input is assigned to var
    return var;
  }

  /**
   * The main method is the driver function of the AsciiArt programme. It is responsible for setting
   * up an interface for the user and calling the methods of the various classes in the programme
   * 
   * @param args
   */
  public static void main(String[] args) {
    // Welcome message
    System.out.println("=================   Welcome to the Ascii Art App   =================\r\n");
    // Set the menu, prompt the user command, error messages, and goodbye strings
    final String menu = "\nCOMMAND MENU: \r\n" + "   [1] Create a new canvas\r\n"
        + "   [2] Draw a character\r\n" + "   [3] Undo drawing\r\n" + "   [4] Redo drawing\r\n"
        + "   [5] Show current canvas\r\n" + "   [6] Exit\r\n ";
    final String promptUser = "\nENTER COMMAND: "; // prompt to be displayed for the user to enter
    final String errorMsg = "ERROR. Please refer to the above COMMAND MENU for details.";
    final String noCanvasMsg = "ERROR. Please create a canvas first.";
    final String formatErrMsg = "ERROR: COMMAND must contain an int value";

    // final message
    final String goodBye =
        "===================   Thank you for using this App!!!!   ===================";


    // Scanner object to read the user input from the keyboard
    Scanner input = new Scanner(System.in);
    Canvas cnvs = null; // The canvas object is declared
    int chc = 0; // chc will store the user's choice
    boolean quit = false; // variable to determine if the programme is to be quit

    // do-while loop to run the prgram as long as the user wants. (Will run at least once)
    do {


      chc = 0; // choice is set to zero every iteration of the loop
      // Display menu and prompts the user
      System.out.println(menu);
      System.out.print(promptUser);
      chc = intInputGetter(input); // user choice is stored

      if (chc == 0) // if the user enters no valid integer value then format error message is printd
        System.out.println(formatErrMsg);

      // switthc case to execute the user;s choice
      switch (chc) {

        // for creating a canvas object
        case 1:

          int canvasHeight, canvasWidth;
          // Canvas dimensions are entered by the user
          System.out.println("Enter canvas height: ");
          canvasHeight = intInputGetter(input);
          System.out.println("Enter canvas width: ");
          canvasWidth = intInputGetter(input);
          // try block is used to catch exception thrown for wrong dimensions of the canvas
          try {
            cnvs = new Canvas(canvasWidth, canvasHeight); // creates a canvas with user's values
          } catch (IllegalArgumentException excpt) { // catches exception thrown for invalid args
            System.out.println(excpt.getMessage());
          }
          break; // break statement to prevent fall-through

        // for drawing a character on the canvas
        case 2:

          if (cnvs == null) // no character can be drawn without creating the canvas
            System.out.println(noCanvasMsg); // error message if the user has not created a canvas
          else {

            int r, c; // the coordinates for drawing the character on the canvas
            char character;
            // takes the input for the character and its position from the user
            System.out.println("Enter the row no.: ");
            r = intInputGetter(input);
            System.out.println("Enter the column no.: ");
            c = intInputGetter(input);
            System.out.println("Enter the character you want to draw");
            character = input.next().charAt(0);
            /*
             * try block is used to catch exception thrown for drawing a character which is outside
             * the canvas
             */
            try {
              cnvs.draw(r, c, character); // draws the charascter at the position specified by user
            } catch (IllegalArgumentException excpt) { // catches exception thrown for invalid args
              System.out.println(excpt.getMessage());
            }
          }
          break; // break statement to prevent fall-through

        // for performing the undo operation
        case 3:

          if (cnvs == null) // no character can be drawn without creating the canvas
            System.out.println(noCanvasMsg); // error message if the user has not created a canvas

          // performs the undo operation and checks if it was performed successfully
          else if (!cnvs.undo())
            // undo operation is unsuccessful, if there are no moves to be undone
            System.out.println("ERROR. No moves are available to be undone.");
          break; // break statement to prevent fall-through

        // for performing the redo operation
        case 4:

          if (cnvs == null)
            System.out.println(noCanvasMsg);
          // performs the redo operation and checks if it was performed successfully
          else if (!cnvs.redo())
            // redo operation is unsuccessful, if there are no moves to be redone
            System.out.println("ERROR. No moves are available to be redone.");
          break; // break statement to prevent fall-through

        // for printing out the canvas
        case 5:

          // canvas is not displayed if it isn't created
          if (cnvs == null)
            System.out.println(noCanvasMsg);
          else
            System.out.println(cnvs); // implicit call to the toString method
          break; // break statement to prevent fall-through

        // for writing the programme
        case 6:

          quit = true; // the value of quit is changed
          break; // break statement to prevent fall-through

        // error message is printed uot if user enters any other number choice
        default:
          System.out.println(errorMsg);
      }
    } while (!quit); // programme runs until the user wishes to quit

    input.close(); // free the Scanner resource
    System.out.println(goodBye); // display goodbye message
  }
}
