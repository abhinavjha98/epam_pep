package com.epam.calculator;

public class Calculator {
  /**Calculator having three function. 
*
 */
  /** Returns the addition of two numbers.
   * @param number1 input
   * @param number2 input
   * @return integer sum of two numbers
   */
  public int add(int number1,int number2) {
    
    return number1 + number2;
  }
  /** Returns the multiply of two numbers.
   * @param number1 input
   * @param number2 input
   * @return integer multiply of two numbers
   */
  
  public int multiply(int number1,int number2) {
    return number1 * number2;   
  }
  /** Returns the division of two numbers.
   * @param number1 input
   * @param number2 input
   * @return {double} division of two numbers
   */
  
  public double divide(int number1,int number2) {
    
    double divideTwoNubers = 0.0;
    try {
      divideTwoNubers = (double)number1 / number2; 
    } catch (ArithmeticException arithmeticException) {
      arithmeticException.printStackTrace();
    }
    return divideTwoNubers; 
  }
}
