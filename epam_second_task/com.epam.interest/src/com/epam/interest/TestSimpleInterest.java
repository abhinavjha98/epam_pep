package com.epam.interest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestSimpleInterest {
  
  @Test
void test() {
    InterestCalculator test = new InterestCalculator();
    double output = test.simpleInterest(100, 12, 1);
    assertEquals(12, output);
  }

}
