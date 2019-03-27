package com.epam.metro;

/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
//import for Scanner and other utility classes
import java.util.*;
*/
import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.AbstractCollection;
import java.util.Comparator;
import java.io.InputStream;

class metro {
    public static void main(String args[] ) throws Exception {
        
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        ScanReader in = new ScanReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Metro solver = new Metro();
        solver.solve(1, in, out);
        out.close();
    }
static class Metro {
        public void solve(int testNumber, ScanReader in, PrintWriter out) {
            int n = in.scanInt();
            int m = in.scanInt();
            ArrayList<pair> arrayList[] = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) arrayList[i] = new ArrayList();
 
 
            for (int i = 0; i < m; i++) {
                int s = in.scanInt();
                long t = in.scanInt();
                int arr[] = new int[s];
                for (int j = 0; j < s; j++) arr[j] = in.scanInt();
                for (int j = 0; j < s - 1; j++) {
                    int we = in.scanInt();
                    arrayList[arr[j]].add(new pair(arr[j + 1], we, t));
                    t += we;
                }
            }
 
 
            int st = in.scanInt();
            int end = in.scanInt();
 
 
            long dis[] = new long[n + 1];
            Arrays.fill(dis, Long.MAX_VALUE / 2);
 
 
            dis[st] = 0;
            PriorityQueue<pair2> pq = new PriorityQueue<>(new Comparator<pair2>() {
 
                public int compare(pair2 o1, pair2 o2) {
                    return Long.compare(o1.dis, o2.dis);
                }
            });
 
            pq.add(new pair2(st, dis[st]));
            boolean visited[] = new boolean[n + 1];
            visited[st] = true;
 
 
            while (!pq.isEmpty()) {
                pair2 p = pq.poll();
                if (p.node == end) {
                    out.println(dis[p.node]);
                    return;
                }
 
                for (pair pp : arrayList[p.node]) {
                    if (dis[p.node] <= pp.t && dis[pp.u] > dis[p.node] + pp.w) {
                        dis[pp.u] = dis[p.node] + pp.w;
                        pq.add(new pair2(pp.u, dis[pp.u]));
                    }
                }
 
            }
            out.println(-1);
 
 
        }
 
        class pair2 {
            int node;
            long dis;
 
            public pair2(int node, long dis) {
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
 
    static class ScanReader {
        private byte[] buf = new byte[4 * 1024];
        private int INDEX;
        private BufferedInputStream in;
        private int TOTAL;
 
        public ScanReader(InputStream inputStream) {
            in = new BufferedInputStream(inputStream);
        }
 
        private int scan() {
            if (INDEX >= TOTAL) {
                INDEX = 0;
                try {
                    TOTAL = in.read(buf);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (TOTAL <= 0) return -1;
            }
            return buf[INDEX++];
        }
 
        public int scanInt() {
            int I = 0;
            int n = scan();
            while (isWhiteSpace(n)) n = scan();
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    I *= 10;
                    I += n - '0';
                    n = scan();
                }
            }
            return neg * I;
        }
 
        private boolean isWhiteSpace(int n) {
            if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1) return true;
            else return false;
        }
 
    }
}
