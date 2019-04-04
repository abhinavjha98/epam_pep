package com.epam.fourth;

import java.util.*;
class Node
{
    int data;
    Node next;
    Node(int d)
    {
        this.data=d;
        next=null;
    }
}
class LinkedList
{
    Node head;
    Node tail;
    LinkedList()
    {
        head=null;
        tail=null;
    }
    void push(int d)
    {Node newNode=new Node(d);
        if(head==null)
        {head=newNode;
        tail=newNode;
        return;
            
        }
        tail.next=newNode;
        tail=tail.next;
    }
    Node middleElement()
    {
        if(head!=null)
        {
            Node sptr=head;
            Node fptr=head;
            while(fptr!=null && fptr.next!=null)
            {
                fptr=fptr.next.next;
                sptr=sptr.next;
            }
            return sptr;
        }
        return null;
        
    }
}
class middleOfLinkedList
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        LinkedList l=new LinkedList();
        for(int i=0;i<n;i++)
        {
            l.push(sc.nextInt());
            }
            Node mid=l.middleElement();
    if(mid!=null)
    System.out.println(mid.data);
        
    }
}
