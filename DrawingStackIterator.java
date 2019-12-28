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
 * This class represents an iterator that iterates over a stack of elements of the DrawingChange
 * type. This implements the Iterator<T> interface
 * 
 * @author Vedaant Tambi
 * @version 1.0
 * @since 1.0
 */
public class DrawingStackIterator implements Iterator<DrawingChange> {
  
  private Node<DrawingChange> drawingChangeNode; // Node that will be used to iterate over the stack
  
  /**
   * This constructor creates the DrawingStackIterator and assigns the runner node to the top of the
   * stack  
   * 
   * @param top represents the top node of the stack or the starting node
   */
  public DrawingStackIterator(Node<DrawingChange> top) {
    drawingChangeNode=top; // the runner node now points to the top of the stack 
  }
  
  /**
   * Checks whether the end of the Stack has been reached or not. Overrides the Iterator<T> class's
   * hasNext() function
   * 
   * @return true if the Node points to another node in the list, false otherwise 
   */
  @Override
  public boolean hasNext() {
    return drawingChangeNode!=null; // to check and return if the runner node is null
  }
  
  /**
   * This method returns the current element of the stack and also moves the runner node forward
   * 
   * @return the current element of the stack which is of DrawingChange type
   */
  @Override
  public DrawingChange next() {
    // checks whether the next node is there or not
    if (!hasNext()) 
      return null; 
    Node<DrawingChange> curr = drawingChangeNode; // temporary node to store the current node
    drawingChangeNode=drawingChangeNode.getNext(); // runner node is moved forward
    return curr.getData(); // data of the current node is returned
  }

}
