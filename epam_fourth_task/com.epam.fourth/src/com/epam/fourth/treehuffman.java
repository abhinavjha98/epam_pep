package com.epam.fourth;

import java.util.*;

abstract class Nodes implements Comparable<Node> {
    public  int frequency; // the frequency of this tree
    public  char data;
    public  Nodes left, right; 
    public Nodes(int freq) { 
      frequency = freq; 
    }
 
    // compares on the frequency
    public int compareTo(Nodes tree) {
        return frequency - tree.frequency;
    }
}
 
class HuffmanLeaf extends Nodes {
    
 
    public HuffmanLeaf(int freq, char val) {
        super(freq);
        data = val;
    }

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
 
class HuffmanNode extends Nodes {
    
    public HuffmanNode(Nodes l, Nodes r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return 0;
	}

}


class Decoding {

	void decode(String s, Nodes root) {
        
Nodes temp=root;
        String ans="";
        for(int i=0;i<s.length();i++){
         //   System.out.println("er1");
            if(s.charAt(i)=='0')
                temp=temp.left;
            else
                temp=temp.right;
            if(temp.right==null && temp.left==null)
                {
                ans+=(temp.data);
                temp=root;
            }
        }
        System.out.println(ans); 
    }
       
    



}

 
public class treehuffman {
  
    // input is an array of frequencies, indexed by character code
    public static Nodes buildTree(int[] charFreqs) {
      
        PriorityQueue<Nodes> trees = new PriorityQueue<Nodes>();
        // initially, we have a forest of leaves
        // one for each non-empty character
        for (int i = 0; i < charFreqs.length; i++)
            if (charFreqs[i] > 0)
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i));
 
        assert trees.size() > 0;
      
        // loop until there is only one tree left
        while (trees.size() > 1) {
            // two trees with least frequency
            Nodes a = trees.poll();
            Nodes b = trees.poll();
 
            // put into new node and re-insert into queue
            trees.offer(new HuffmanNode(a, b));
        }
      
        return trees.poll();
    }
  
    public static Map<Character,String> mapA=new HashMap<Character ,String>();
  
    public static void printCodes(Nodes tree, StringBuffer prefix) {
      
        assert tree != null;
      
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
 
            // print out character, frequency, and code for this leaf (which is just the prefix)
            //System.out.println(leaf.data + "\t" + leaf.frequency + "\t" + prefix);
            mapA.put(leaf.data,prefix.toString());

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 
            // traverse left
            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);
 
            // traverse right
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    
        String test= input.next();
 
        // we will assume that all our characters will have
        // code less than 256, for simplicity
        int[] charFreqs = new int[256];
      
        // read each character and record the frequencies
        for (char c : test.toCharArray())
            charFreqs[c]++;
 
        // build tree
        Nodes tree = buildTree(charFreqs);
 
        // print out results
        printCodes(tree, new StringBuffer());
        StringBuffer s = new StringBuffer();
      
        for(int i = 0; i < test.length(); i++) {
          	char c = test.charAt(i);
            s.append(mapA.get(c));
        }
      
        //System.out.println(s);
        Decoding d = new Decoding();
        d.decode(s.toString(), tree);

    }
}