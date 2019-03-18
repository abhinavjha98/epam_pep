package com.epam.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestDiv {

  @Test
void test() {
    Calculator test = new Calculator();
    double op = test.divide(50,5);
    assertEquals(10,op);
  }

}
