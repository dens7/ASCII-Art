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
 * This class represents a Canvas on which characters can be drawn
 * 
 * @author Vedaant Tambi, Alexi Brooks
 * @version 1.0
 * @since 1.0
 */
public class Canvas {
  private final int width; // width of the canvas
  private final int height; // height of the canvas

  private char[][] drawingArray; // 2D character array to store the drawing

  private final DrawingStack undoStack; // store previous changes for undo
  private final DrawingStack redoStack; // store undone changes for redo

  /**
   * Getter for drawingArray
   * 
   * @returns the drawingArray field
   */
  public char[][] getDrawingArray() {
    return drawingArray;
  }
  
  /**
   * Getter for undoStack 
   * 
   * @returns the undoStack field
   */
  public DrawingStack getUndoStack() {
    return undoStack;
  }
  
  /**
   * Getter for redoStack 
   * 
   * @returns the redoStack field
   */
  public DrawingStack getRedoStack() {
    return redoStack;
  }
  
  /**
   * This constructor creates the Canvas on which the characters can be drawn. Initializes the 
   * data fields of the canvas class
   * 
   * @param width is the width of the canvas
   * @param height is the height of the canvas
   */
  public Canvas (int width, int height) {  
    
    // if block to handle illegal dimensions such as 0 or negative numbers
    if (width<=0||height<=0)
      // exception is thrown if the width or height do not conform to the standards of dimensions 
      throw new IllegalArgumentException("WARNING: The height or width of the Canvas CANNOT be 0 or"
          + " NEGATIVE");    
    
    // initialization of the data fields
    this.width=width;
    this.height=height;
    undoStack = new DrawingStack(); // Creates an empty undoStack
    redoStack = new DrawingStack(); // creates an empty redoStack
    // Here the height and width of the canvas determine the number of rows and columns respectively 
    drawingArray = new char[this.height][this.width]; 
    
    // Nested for-loop for initializing the 2-D array  Canvas
    for (int i=0;i<this.height;i++) // outer loop is for initializing all the rows of the campuw
      for (int j=0; j<this.width;j++) // inner loop is for initializing all the elements in a row
        drawingArray[i][j]=' ';       // blank space is denoted by ' '   
  } 
  
  

 // This method should throw an IllegalArgumentException if the drawing position is outside the canvas
 // If that position is already marked with a different character, overwrite it.
 // After making a new change, add a matching DrawingChange to the undoStack so that we can undo if needed.
 // After making a new change, the redoStack should be empty.
  /**
   * draws a character at the given position (i.e. the one given by the user)
   * 
   * @param row refers to the number of rows in the canvas
   * @param col refers to the number of columns in the canvas
   * @param c is the character that user wants to draw on the canvas
   */
  public void draw(int row, int col, char c) { 
    
    // if block to make sure that the position for drawing is inside the canvas
    if (row>=this.height||row<0||col>=this.width||col<0)
      // exception is thrown with a warning message if the character is not inside the canvas 
      throw new IllegalArgumentException("WARNING: These coo-ordinates  CANNOT exceed the "
          + "dimensions of the Canvas or be NEGATIVE."); 
    
    // prev stores the state of the position before the drawing is made on that position
    char prev = drawingArray[row][col];
    
    // the character is drawn at that position
    drawingArray[row][col]=c;
    
    // the drawing change made is stored inside the undoStack as a DrawingChange object
    undoStack.push(new DrawingChange(row,col,prev,c));
    
    // Makes sure that the redoStack is empty (will only be filled if an undo is performed)
    while (!redoStack.isEmpty()) {
      redoStack.pop(); // Each element inside the stack is popped till it's empty
    }
  }

  /**
   * Performs the undo operation 
   * 
   * @returns true if the operation is completed successfully, otherwise false
   */
  public boolean undo() {  
    if (undoStack.isEmpty()) // checks if the undoStack is empty
      return false; // if the stack is empty then the operation cannot be performed and unsuccessful
    
    // draws the previous element back at the position of the new element.
    drawingArray[undoStack.peek().x][undoStack.peek().y] = undoStack.peek().prevChar;
    
    /* After the undo operation, the drawing change that was made is now stored in the redoStack, so
     *  that the user can perform the redo operation. Only, one element of the undoStack is deleted  
     */
    redoStack.push(undoStack.pop()); 
    
    return true; // If the compiler reaches this line, it means that the operation was successful
  } 
  

 // A redone DrawingChange should be added (back) to the undoStack so that we can undo again if needed.
  /**
   * Redo the most recent undone drawing change.
   *  
   * @return true if the redone operation is successful. False otherwise.
   */
  public boolean redo() { 
    
    if (redoStack.isEmpty()) // checks if the undoStack is empty 
      return false; // if the stack is empty then the operation cannot be performed and unsuccessful
    
    // draws the new element back at the position of the previous element after the undo.
    drawingArray[redoStack.peek().x][redoStack.peek().y] = redoStack.peek().newChar;
    
    /* After the redo operation, the drawing change that was made is now stored in the undoStack, so
     *  that the user can perform the undo operation again. Only, one element of the redoStack is 
     *  deleted  
     */
    undoStack.push(redoStack.pop()); 
    
    return true; // If the compiler reaches this line, it means that the operation was successful
  } 


  /**
   * This function returns the canvas in the graphic form. The canvas is printed as a grid of rows 
   * and columns on the console. This function is automatically called when the Canvas object is 
   * printed on the grid
   *
   * @returns the canvas in the form of a string  
   */
  @Override
  public String toString() {  
    // Dynamic initialization of the string
    String s = new String("");
    // nested for-loop to print the canvas as a grid of rows and columns
    for (int i=0;i<this.height;i++) {  
      for (int j=0; j<this.width;j++)
              s+=drawingArray[i][j]; 
      s+=System.lineSeparator(); // one line for each row  
    }
    return s; // returns a printable string version of the Canvas.
  } 

}
