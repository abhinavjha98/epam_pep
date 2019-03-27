package com.epam.glowingbulbs;

import java.util.*;
/* Class for calculating glowing bulbs
 * 
 */

public class Glowingbulbs {

  static Set<Integer> list = new TreeSet<>();
  static List<Integer> switches = new ArrayList<>(12);
  
  /**
   * Main function.
   * @param args nothing
   */
  
  public static void main(String[] args) {
     Scanner s = new Scanner(System.in);

     int t = s.nextInt();

    List<Integer> result = new ArrayList<>();
    long k, l, h, m = 0;
    int i;
    long ans = 0;

    while (t > 0) {
      t -= 1;
      switches.clear();
      String switchString = s.next();
      k = s.nextLong();

      for (i = 0; i < switchString.length(); i++) {
        if (switchString.charAt(i) == '1') {
          switches.add(i + 1);
        }     
      }

      l = 1; h = 1000000000000000L;

      while (l <= h) {
        m = (l + h) >> 1;
        if (calc(m) >= k) {
          ans = m;
          h = m - 1;
        }
        else {
          l = m + 1;
        }
      }

       System.out.println(ans);

    }
  }
  /**
  * Function for calculating the rod.
  * @param m is the mid
  * @return the result of type long
  */
  private static long calc( long m ) {
    int p = switches.size();    
    int i,j;
    long res = 0;
    for (i = 1; i < (1 << p); i++) {
      long pr = 1;
      int sign = -1;
      for (j = 0; j < p; j++) {
         if(((i >> j) &1) == 1) {
                 pr = pr * switches.get( j );
                 sign *= -1;
             }
         }
         res += sign*(m/pr);
    }
    return res;
  }
}