package com.epam.sortthearrayweight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Finding the special weight and sorting the number with non-increasing order.
 * @param n total number of weights
 * @param k weight
 * @param num is the number
 * @author Abhinav
 *
 */
public class SortArrayDescendingWeight {
  /**
   * Finding the special weight and sorting the number with non-increasing order.
   * @param args unused
   * @throws Exception if any array
   */
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    
    ArrayList <Integer> arr[] = new ArrayList[k];
    
    for (int i = 0;i < k;i++) {
      arr[i] = new ArrayList<Integer>();
    }
    for (int i = 0;i < n;i++) {
      int num = sc.nextInt();
      arr[num % k].add(num);
    }
    for (int i = 0;i < k;i++) {
      Collections.sort(arr[i]);
    }
    for (int i = k - 1; i >= 0;i--) {
      for (int j = 0;j <= arr[i].size();j++) {
        System.out.println(arr[i].get(j) + " ");
      }
    }
  }
}
