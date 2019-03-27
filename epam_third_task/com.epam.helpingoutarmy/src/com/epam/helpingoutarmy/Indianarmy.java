package com.epam.helpingoutarmy;

import java.util.Arrays;
import java.util.Scanner;

public class Indianarmy {
  /**
  * Finding total distance of the x-axis, that is not protected by the armed forces.
  * @param args unused
  * @throws Exception unused
  */
  public static void main(String[] args) throws Exception {
    int i;
    int checkpoints;
    long sum = 0;
    Scanner scan = new Scanner(System.in);
    checkpoints = scan.nextInt();
    long[] mindefend = new long[checkpoints + 2];
    long[] maxdefend = new long[checkpoints + 2];
    mindefend[0] = scan.nextLong();
    mindefend[1] = scan.nextLong();
    maxdefend[0] = mindefend[0];
    maxdefend[1] = mindefend[1];
    for (i = 2;i < checkpoints + 2;i++) {
      long position = scan.nextLong();
      long army = scan.nextLong();
      if ((mindefend[0] <= position + army) && (maxdefend[1] >= position - army)) {
        mindefend[i] = position - army;
        maxdefend[i] = position + army;
      }
      else if (mindefend[0] > position + army) {
        mindefend[i] = mindefend[0];
        maxdefend[i] = mindefend[0];
      }
      else {
        mindefend[i] = mindefend[1];
        maxdefend[i] = mindefend[1];
      }
    }
    Arrays.sort(mindefend);
    Arrays.sort(maxdefend);
    for (i = 1;i < mindefend.length;i++) {
      if (mindefend[i] - maxdefend[i - 1] > 0) {
        sum += mindefend[i] - maxdefend[i - 1];
      }
    }
    System.out.print(sum);
  } 
}