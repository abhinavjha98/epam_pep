package com.epam.interest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestCompundInterest {
  @Test
void test() {
    InterestCalculator test = new InterestCalculator();
    double output = test.calculateCompoundInterest(102, 15, 5);
    assertEquals(205.15843312499993, output);
  }

}
