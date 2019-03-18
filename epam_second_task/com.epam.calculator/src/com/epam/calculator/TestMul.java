package com.epam.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestMul {

  @Test
void test() {
    Calculator test = new Calculator();
    int op = test.multiply(10, 15);
    assertEquals(150,op);
  }

}
