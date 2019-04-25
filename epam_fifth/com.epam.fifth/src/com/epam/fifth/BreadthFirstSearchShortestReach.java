package com.epam.fifth;

import java.util.*;

public class BreadthFirstSearchShortestReach {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int test = input.nextInt();
        while(test > 0){
            int n = input.nextInt();
            int m = input.nextInt();
            node_g[] vertex = new node_g[n+1];
            for(int i=0;i<vertex.length;i++){
                vertex[i] = new node_g();
            }
            for(int i=0;i<m;i++){
                int a = input.nextInt();
                int b = input.nextInt();
                vertex[a].list.add(vertex[b]);
                vertex[b].list.add(vertex[a]);
            }
            int s = input.nextInt();
            ArrayList<node_g> prev = new ArrayList<node_g>();
            vertex[s].visited = true;
            vertex[s].distance = 0;
            prev.add(vertex[s]);
            ArrayList<node_g> curr = new ArrayList<node_g>();
            while(prev.size() != 0){
                for(int i=0;i<prev.size();i++){
                    for(int j=0;j<prev.get(i).list.size();j++){
                        if(!prev.get(i).list.get(j).visited){
                            prev.get(i).list.get(j).visited = true;
                            prev.get(i).list.get(j).distance = prev.get(i).distance + 6;
                            curr.add(prev.get(i).list.get(j));
                        }
                    }
                }
                prev = curr;
                curr = new ArrayList<node_g>();
            }
            for(int i=1;i<=n;i++){
                if(vertex[i].distance != 0){
                    System.out.print(vertex[i].distance+" ");
                }
            }
            System.out.println();
            test--;
        }
    }
}
class node_g{
    boolean visited = false;
    ArrayList<node_g> list = new ArrayList<node_g>();
    int distance = -1;
}
