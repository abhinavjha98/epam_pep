package com.epam.fifth;


import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class RustMurderer {

    static ArrayList<Integer>[] data;
    static private int[] d;
    static private int[] state;
    
    public static void main(String[] args) throws IOException {
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine().trim());
        for (int i = 0; i < n; i++) {

            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            data = new ArrayList[a];

            for (int j = 0; j < a; j++) {
                data[j] = new ArrayList<Integer>();
            }
            for (int j = 0; j < b; j++) {
                st = new StringTokenizer(in.readLine());
                int c = Integer.parseInt(st.nextToken()) - 1;
                int d1 = Integer.parseInt(st.nextToken()) - 1;

                data[c].add(d1);
                data[d1].add(c);
            }

            int s = Integer.parseInt(in.readLine().trim()) - 1;
            int out[] = breadthFirstSearch(s);

            for (int j = 0; j < out.length; j++) {
                if(j==s)
                {
                    continue;
                }
                System.out.print(out[j]+" ");
            }
            System.out.println("");
        }

        
    }
    static int[] breadthFirstSearch(int s) {

        java.util.LinkedList<Integer> q = new java.util.LinkedList<Integer>();
        d=new int[data.length];
        state=new int [data.length];
        for (int i = 0; i < data.length; i++) {
            state[i] = -1;
            d[i] = -1;
        }
        q.add(s);
        state[s] = 0;
        d[s]=0;

        while (q.size() != 0) {
            int hold = q.removeFirst();

            for (int i = 0; i < data.length; i++) {
               // System.out.println((hold + 1) + "   -- " + (i + 1));

                if (state[i] == -1 && !data[hold].contains(i) ) {
                   // System.out.println("*");
                    q.add(i);
                    state[i] = 0;
                    d[i] = d[hold] + 1;
                }
            }
            state[hold] = 1;
            for (int i = 0; i < d.length; i++) {
                if(d[i]==-1)
                {
                    break;
                }
                if(i==d.length-1)
                {
                    return d;
                }
            }
        }
        return d;
    }
}