package com.epam.stats;

import java.util.Arrays;
import java.util.Scanner;

public class statsofstring {
  static int power(int a, int b, int mod) {
    if (b == 0) {
      return 1;
    }
    int p = power(a, b / 2, mod);
    p = (int) ((long) p * p % mod);
    if (b % 2 == 1) {
      p = (int) ((long) p * a % mod);
    }
    return p;
  }
  static int[] array;
  /**
   * 
   * @param i
   * @return
   */
  static int find(int i) {
    return array[i] < 0 ? i : (array[i] = find(array[i]));
  }
  /**
   * 
   * @param i
   * @param j
   * @return
   */
  static boolean join(int i, int j) {
    i = find(i);
    j = find(j);
    if (i == j) {
      return false;
    }
    if (array[i] > array[j]) {
      array[i] = j;
    }
    else {
      if (array[i] == array[j]) {
        array[i]--;
      }
      array[j] = i;
    }
    return true;
  }
/**
 * 
 * @param k
 * @param v
 * @param n
 * @param a
 * @param mod
 * @return
 */
  static int simplify(int k, int v, int n, int a, int mod) {
    Arrays.fill(array, -1);
    int bcnt = 0;
    int cnt = n;
    for (int i = 1; i < n; i++) {
      if ((v & 1 << i) > 0) {
        for (int j = i; j < i + k; j++) {
          if (join(j, j - i)) {
            cnt--;
          }
          bcnt++;
        }
      }
    }
    int sum = power(a, cnt, mod);
    return bcnt % 2 == 1 ? sum : (mod - sum) % mod;
  }
/**
 * 	
 * @param args
 * @throws Exception
 */
public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int a = sc.nextInt();
    int mod = sc.nextInt();
    array = new int[n];
    int sum = 0;
    for (int k = 1; k < n; k++) {
      for (int v = 2; v < 1 << n - k + 1; v += 2) {
        sum = (sum + simplify(k, v, n, a, mod)) % mod;
      }
    }
    System.out.println(sum);
  }
}
