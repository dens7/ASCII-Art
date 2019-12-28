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
 * This class represents a drawing change to the canvas object defined by the Canvas class
 * 
 * @author Vedaant Tambi
 * @version 1.0
 * @since 1.0
 */
public class DrawingChange {
  public final int x; // x coordinate for a change
  public final int y; // y coordinate for a change
  public final char prevChar; // previous character in the (x,y)
  public final char newChar; // new character in the (x,y)

  /**
   * This constructor creates a DrawingChange object and initializes the datafields of the class
   * 
   * @param x refers to the x coordinate of the position where the character is drawn
   * @param y refers to the y coordinate of the position where the character is drawn
   * @param prevChar refers to the character at the specific position before a drawing change imade
   * @param newChar refers the new character that is drawn on the canvas, thus replacing prev char
   */
  public DrawingChange(int x, int y, char prevChar, char newChar) {

    // all data fields are initialized
    this.x = x;
    this.y = y;
    this.prevChar = prevChar;
    this.newChar = newChar;
  }
}
