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
 * This class represents a stack which holds elements of the DrawingChange type. It implements the
 * StackADT interface.
 * 
 * @author Vedaant Tambi
 * @version 1.0
 * @since 1.0
 */
public class DrawingStack implements StackADT<DrawingChange> {

  Node<DrawingChange> top; // top of the linked list
  int size; // size of the stack

  /**
   * This constructor creates a DrawingStack object and initializes the data fields of the object
   * 
   */
  public DrawingStack() {
    top = null; // since no object is present, top is null
    size = 0; // since the stack is empty the size is 0
  }

  /**
   * Checks if the stack is empty or not
   * 
   * @return true if the stack is empty, false otherwose
   */
  @Override
  public boolean isEmpty() {
    return top == null; // if the top element is absent, then the stack does not exist
  }

  /**
   * Adds a DrawingChange object to the Stack and updates the size of the stack
   *
   * @param element is the DrawingChange object that is added to the stack
   * @throws IllegalArgumentException if element is not initialized
   */
  @Override
  public void push(DrawingChange element) throws IllegalArgumentException {
    if (element == null)
      throw new IllegalArgumentException("WARNING: you MUST enter a valid character to draw");
    // Creating a Node which holds the DrawingChange data and points to the top Node
    Node<DrawingChange> newNode = new Node<DrawingChange>(element, this.top);
    // now the top node becomes the node which was recently added
    top = newNode;
    size++; // the size of the stacck is incremented after the addition of the node
    newNode = null; // newNode is liberated
  }

  /**
   * Performs the pop operation, i.e. deletes the first element of the stack (top) and returns it
   * 
   * @return DrawingChange item which was popped from the stack
   */
  @Override
  public DrawingChange pop() {
    // checks if the stack is empty since a pop operation cannot be performed on an empty Stack
    if (isEmpty())
      return null; // a null return means that the pop operation was unsuccessful
    // the popped DataChange item is stored in a varible of the same type
    DrawingChange remove = top.getData();
    top = top.getNext(); // the top of the stack is assigned to the next element
    size--; // the size of the Stack is decremented by one
    return remove; // the stored data is returned
  }

  /**
   * Performs the pee operation, i.e. returns the first element of the stack (top)
   * 
   * @return DrawingChange item which was at the top of the stack
   */
  @Override
  public DrawingChange peek() {
    // checks if the stack is empty since a pop operation cannot be performed on an empty Stack
    if (isEmpty())
      return null; // a null return means that the pop operation was unsuccessful
    return top.getData(); // the data, i.e. the Drawing Change is returned
  }

  // returns the current size of the stack
  public int size() {
    return size;
  }

  public Iterator<DrawingChange> iterator() {
    return new DrawingStackIterator(top);
  }

}
