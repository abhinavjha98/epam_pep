package com.epam.metro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Metros {
  static Scanner sc = new Scanner(System.in);
  /** Finding the shortest path in subway lines from the station.
   * 
   * @param args unused
   */
  
  public static void main(String[] args)  {
    Metro solver = new Metro();
    solver.solve();
  }
  /**Static class.
   * 
   * @author Abhi
   *
   */
  static class Metro {
    public void solve() {
      int n = sc.nextInt();
      int m = sc.nextInt();
      ArrayList<pair>[] arrayList = new ArrayList[n + 1];
      for (int i = 0; i <= n; i++) {
    	    arrayList[i] = new ArrayList();
      }
      for (int i = 0; i < m; i++) {
        int s = sc.nextInt();
        long t = sc.nextLong();
        int[] arr = new int[s];
        for (int j = 0; j < s; j++) {
          arr[j] = sc.nextInt();
        }
        for (int j = 0; j < s - 1; j++) {
          int we = sc.nextInt();
          arrayList[arr[j]].add(new pair(arr[j + 1], we,t));
          t += we;
        }
      }
      int st = sc.nextInt();
      int end = sc.nextInt();
      long[] dis = new long[n + 1];
      Arrays.fill(dis, Long.MAX_VALUE / 2);
      dis[st] = 0;
      PriorityQueue<Pairs> pq = new PriorityQueue<>(new Comparator<Pairs>() {
        /**
     * comparing the value.
     * @param o1 of class Pairs
     * @param o2 of class Pairs
     * return compared value
     */
        public int compare(Pairs o1, Pairs o2) {
            return Long.compare(o1.dis, o2.dis);
            }
        });
      pq.add(new Pairs(st, dis[st]));
      boolean[] visited = new boolean[n + 1];
      visited[st] = true;
      while (!pq.isEmpty()) {
        Pairs p = pq.poll();
        if (p.node == end) {
          System.out.println(dis[p.node]);
          return;
        }
        for (pair pp : arrayList[p.node]) {
          if (dis[p.node] <= pp.t && dis[pp.u] > dis[p.node] + pp.w) {
            dis[pp.u] = dis[p.node] + pp.w;
            pq.add(new Pairs(pp.u, dis[pp.u]));
          }
        }
      }
      System.out.println(-1);
    }
    /*
     * Class having node and distance.
     */
    
    class Pairs {
      int node;
      long dis;
     
      public Pairs(int node, long dis) {
        this.node = node;
        this.dis = dis;
      }
    }
    class pair {
      int u;
      long w;
      long t;
     
      public pair(int u, long w, long t) {
        this.u = u;
        this.w = w;
        this.t = t;
      }
    }
  }
 }