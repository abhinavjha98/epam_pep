package com.epam.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class TestAdd {
  @Test
void test() {
    Calculator test = new Calculator();
    int op = test.add(10, 15);
    assertEquals(25,op);
  }

}
