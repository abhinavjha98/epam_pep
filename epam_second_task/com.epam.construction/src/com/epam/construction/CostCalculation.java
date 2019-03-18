package com.epam.construction;

public class CostCalculation {
  /**
   * This is used for calculation purpose.
*/
  public double calculateCost(double area, String choiceMaterial, String choiceAutomation) {
    /**Returns the calculations.
* @param double area
* @param String choiceMaterial
* @param String choiceAutomation
* @return the cost of the house
* 
*/
    double costHouse = 0.0;
    if (choiceAutomation.equalsIgnoreCase("fullyAutomated")) {
      if (choiceMaterial.equalsIgnoreCase("highStandard")) {
        costHouse = area * 2500;
      }
    } else {
      if (choiceMaterial.equalsIgnoreCase("highStandard")) {
        costHouse = area * 1800; 
      } else if (choiceMaterial.equalsIgnoreCase("aboveStandard")) {
        costHouse = area * 1500;
      } else if (choiceMaterial.equalsIgnoreCase("standard")) {
        costHouse = area * 1200;
      } else {
        costHouse = 0.0;
      }
    }
    return costHouse;
  }
}
