package com.epam.rodcuttingproblem;

import java.util.ArrayList;
import java.util.Scanner;

public class RodCuttingProblem {
  
  static int count = 0;
  /**
   * Finding the actual length by shifting the number.
   * @param n number
   * @return
   */
  public static int len(int n) {
    if (n == 0 || n == 1) {
      return 0;
    }
    return (1 + len(n >> 1));
  }
  /**
   * Main method.
   * @param args unused
   * @throws Exception for error
   */
  
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    ArrayList<Integer> l = new ArrayList<Integer>();
    int test = sc.nextInt(); 
    int temp = test;
    while (test != 0) {
      int lengthofrod = sc.nextInt();
      lengthofrod = lengthofrod + 1;
      int a = len(lengthofrod) - 1;
      l.add(a);
      count = 0;
      test--;
    }
    for (int i = 0;i < temp;i++) {
      System.out.println(l.get(i));
    }
 
  }
}
