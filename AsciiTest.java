import java.util.Iterator;

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
 * This class is the Tester class for the Ascii Art programme. This class uses several methods to
 * the implementation of various methods in this programme.
 * 
 * @author Vedaant Tambi
 * @version 1.0
 * @since 1.0
 */
public class AsciiTest {

  /**
   * This method tests the push and peek() methods of the DrawingStack class which implements the
   * Stack.ADT interface
   * 
   * @return true if method shows current functionality, false otherwise
   */
  public static boolean testStackPushPeek() {
    DrawingStack D1 = new DrawingStack(); // creates and initializes a Drawing Stack
    DrawingChange ele = new DrawingChange(1, 4, 'r', 's'); // creates & initializes a DrawingChange
                                                           // obj
    D1.push(ele); // the DrawingChange object is pushed into the stack

    /*
     * the top element of the Stack is returned and checked if it is the same element that was added
     * to the stack in the previous step
     */
    return D1.peek().equals(ele); // true is returned if the push() and peek() method are successful
  }

  /**
   * This method tests the isEmpty() and size() method of the DrawingStack class which implements
   * the StackADT interface
   * 
   * @return true if method shows current functionality, false otherwise
   */
  public static boolean testStackSizeIsEmpty() {

    DrawingStack D2 = new DrawingStack(); // creates and initializes a Drawing Stack
    if (!D2.isEmpty()) // checks if the stack is empty
      return false; // returns false if fails if isEmpty shows wrong functionality

    /*
     * Since no element has been added to stack, the size of the stack must be 0. If size() does not
     * return 0, then the method shows incorrect functionality
     */
    if (D2.size() != 0)
      return false;

    // 2 elements of DrawingChange type are added to the stack
    D2.push(new DrawingChange(1, 1, 'a', 'b'));
    D2.push(new DrawingChange(2, 2, 'c', 'd'));

    // Since the stack is not empty now, isEmpty() method must return false
    if (D2.isEmpty())
      return false;

    if (D2.size() != 2) // Since the stack size is 2 now
      return false;
    return true; // This line has been reached, so both the methods show correct functionality
  }

  /**
   * This method tests the pop() method of the DrawingStack class which implements the StackADT
   * interface
   * 
   * @return true if method shows current functionality, false otherwise
   */
  public static boolean testPop() {

    DrawingStack D3 = new DrawingStack(); // creates and initializes a Drawing Stack

    // checks whether the empty stack returns null if a pop operation is performed on the stack
    if (D3.pop() != null)
      return false;
    // 2 elements of DrawingChange type are created and pushed into the stack
    DrawingChange first = new DrawingChange(0, 1, 'e', 'f');
    DrawingChange second = new DrawingChange(2, 3, 'g', 'h');
    D3.push(first);
    D3.push(second);
    DrawingChange remove = D3.pop(); // stack is popped and top is stored in DrawChange variable
    // if the removed element returns a null, pop() shows incorrect functionality and returns false
    if (remove == null)
      return false;

    // if the popped element is not equal to the element at the top of the stack, false is returned
    if (!remove.equals(second))
      return false;
    return true; // if this line has been reached, the pop method shows correct functionality
  }

  /**
   * This method tests the iterator() method of the DrawingStack class which implements the StackADT
   * interface
   * 
   * @return true if method shows current functionality, false otherwise
   */
  public static boolean testIterator() {
    DrawingStack D4 = new DrawingStack(); // creates and initializes a Drawing Stack

    // the iterator returned by iterator() is stored in another iterator of DrawingChange type
    Iterator<DrawingChange> iterator = D4.iterator();

    /*
     * Since the stack is empty, the iterator's hasNext() method cannot return true. Behaviour
     * opposite to this shows incorrect functionality
     */
    if (iterator.hasNext())
      return false;

    // 2 elements of DrawingChange type are created and pushed into the stack
    DrawingChange first = new DrawingChange(5, 6, 't', 'g');
    DrawingChange second = new DrawingChange(2, 2, 'j', 'h');
    D4.push(first);
    D4.push(second);

    /*
     * new iterator is returned on every call of the iterator() method, so iteration again starts
     * from the top
     */
    iterator = D4.iterator();

    // iterator is on the 'second' DrawingChange and returns the same
    iterator.next();

    // this checks if the iterator correctly returns the 'first' DrawingChange
    if (!iterator.next().equals(first))
      return false;
    // checks if the next() method returns null, since there are no elements to iterate over.
    if (iterator.next() != null)
      return false;
    return true; // if this line has been reached, the iterator() method shows correct functionality
  }

  /**
   * This method tests the undo method of the Canvas class
   * 
   * @return true if method shows current functionality, false otherwise
   */
  public static boolean testUndo() {

    Canvas canvas = new Canvas(3, 3); // A canvas object is created and initialized with fixed
    canvas.getDrawingArray()[1][2] = 'X'; // 'X' character is drawn at the specified position
    canvas.getDrawingArray()[1][2] = 'O'; // 'O' character is drawn on top of the 'X' character

    // checks whether the 'O' character was successfully written over the 'X' character
    if (canvas.getDrawingArray()[1][2] == 'X')
      return false;

    // new DrawingChange element is pushed into the stack
    canvas.getUndoStack().push(new DrawingChange(1, 2, 'X', 'O'));

    if (!canvas.undo()) // undo() method returns true if the undo operation is successful
      return false;

    // checks if the over writing operation is undone and 'X' character is redrawn at the same spot
    if (canvas.getDrawingArray()[1][2] != 'X')
      return false;
    return true; // if this line has been reached, the undo() method shows correct functionality
  }

  /**
   * This method tests the draw() method of the Canvas class
   * 
   * @return true if method shows current functionality, false otherwise
   */
  public static boolean testCanvasDraw() {
    Canvas canvas = new Canvas(4, 5); // A canvas object is created and initialized with fixed
    try {
      canvas.draw(4, 5, 'p'); // invalid call on draw() method ((4,5) are coordinates outside
                              // canvas)
    } catch (IllegalArgumentException iae) { // catches the exception thrown for passing invalid
                                             // args

      // checks if the correct exception and message is thrown by the draw method
      if (!iae.getMessage().equals("WARNING: These coo-ordinates  CANNOT exceed the "
          + "dimensions of the Canvas or be NEGATIVE."))
        return false;
    }
    canvas.draw(2, 1, 'W'); // Valid call of draw() method

    // checks if the specified character is drawn in the corrct position of the canvas array
    if (canvas.getDrawingArray()[2][1] != 'W')
      return false;

    // checks if the draw method empties the redoStack
    if (!canvas.getRedoStack().isEmpty())
      return false;

    /*
     * checks if the DrawingChange is stored in the undo stack so that the undo operation can be
     * performed on the canvas
     */
    if (canvas.getUndoStack().peek().prevChar != ' ')
      return false;
    return true; // if this line has been reached, the draw() method shows correct functionality
  }

  /**
   * This test method calls all the methods of the DrawingStack to check whether all of the methods
   * show correct functionality
   * 
   * @return true if method shows current functionality, false otherwise
   */
  public static boolean runStackTestSuite() {

    // checks whether all the methods of the StackADT<DrawingChange> implementation run correctly
    if (testStackPushPeek() && testStackSizeIsEmpty() && testPop() && testIterator())
      return true; // true is returned if all the test methods return true
    return false;
  }


  /**
   * The main method is responsible calling all the test methods and displaying the results of their
   * functionality
   */
  public static void main(String[] args) {

    // the above defined test methods are called and the results of the test are displayed
    System.out.println("runStackTestSuite: " + runStackTestSuite());
    System.out.println("testCanvasDraw: " + testCanvasDraw());
    System.out.println("testUndo(): " + testUndo());
  }

}
