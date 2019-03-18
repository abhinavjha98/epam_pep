package com.epam.interest;

public class InterestCalculator {
  /**It calculates the interest for given details.

 */
  /**Function describing simple interest.
 * @param principal amount
 * @param rate amount
 * @param time for the period
 * @return the simple interest
 */
  public double simpleInterest(double principal,double rate,int time) {

    double simpleInterest = (principal * time * rate) / 100.0;

    return simpleInterest;
  }
  /**It describes compound interest.
* @param principal amount
* @param rate amount
* @param time for the period
* @return the compound interest
*/
  
  public double calculateCompoundInterest(double principal, double rate, int time) {
    double compoundInterest = principal * (Math.pow((1 + (rate / 100.0)),time));

    return compoundInterest;
  }
}
