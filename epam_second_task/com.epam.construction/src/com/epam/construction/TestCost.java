package com.epam.construction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestCost {

  @Test
void test() {
    CostCalculation test = new CostCalculation();
    double output = test.calculateCost(200, "highStandard", "fullyAutomated");
    assertEquals(500000.0, output);
  }

}
