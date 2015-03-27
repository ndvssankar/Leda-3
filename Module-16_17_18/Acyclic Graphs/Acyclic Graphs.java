//package cyclic;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author prithvi
 */
public class Cyclic{
    /**
     * @param args the command line arguments
     */
    private String str = "";
    private int count=0;
    private Hashtable<Character,ArrayList<Character>> h = new Hashtable<Character,ArrayList<Character>>();
    private Stack<Character> st = new Stack<Character>();
    private ArrayList<Integer> c =new ArrayList<Integer>();
    public static void main(String[] args) 
    {
        Cyclic cy = new Cyclic();
        ArrayList<String> str2 = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        int z = sc.nextInt();
        int y1 = sc.nextInt();
       int y = sc.nextInt();
        ArrayList<String> s2 = new ArrayList<String>();
            int l = 0;
            while(sc.hasNext())
            {
                s2.add(sc.nextLine());
                l++;
            }
        if(y==1)
        {            
            str2 = cy.adjMatrix(s2);
        }
        else
        {
            str2 = cy.adjList(s2);
        }
        cy.hash(str2);
        int temp=y1;
        //System.out.println(y);
       cy.depth_first_search(temp+"");
            System.out.println("Graph has no cycles");
       // System.out.println(str.substring(0,str.length()-1));
    }
    public void hash(ArrayList<String> str2)
        {
        
        for (int i = 0; i < str2.size() ; i++) 
        {
            ArrayList<Character> temp = new ArrayList<Character>();
            if(h.containsKey(str2.get(i).charAt(1)))
            {
                temp = h.get((str2.get(i)).charAt(1));
                temp.add(str2.get(i).charAt(3));
                Collections.sort(temp);
                h.put(str2.get(i).charAt(1),temp);  
            }
            else
            {
                temp.add(str2.get(i).charAt(3));
                h.put(str2.get(i).charAt(1),temp);
            }   
        }
        for (int i = 0; i < str2.size() ; i++) 
        {
            ArrayList<Character> temp1 = new ArrayList<Character>();
            if(!h.containsKey(str2.get(i).charAt(3)))
            {
                h.put(str2.get(i).charAt(3),temp1);
            }   
        }
    }
    public void depth_first_search(String temp)
        {
        //int g=0;
        if(st.search(temp.charAt(0))==-1)
        {
            if(str.indexOf(temp.charAt(0)+"")==-1)
                {
            count++;
            st.push(temp.charAt(0));
            str=str+temp.charAt(0)+",";
            //count++;
            while(!st.empty())
            {
                ArrayList<Character> al = h.get(st.peek());
                for (int i = 0; i < al.size() ; i++) 
                {
                    depth_first_search(al.get(i)+"");   
                }
                //g++;
                try
                {
                    st.pop();   
                }
                catch (EmptyStackException f)
                {
                }
            }
            }
        }
        else
            {
            System.out.println("Graph has cycles");
            System.exit(0);
        }
        }
    public ArrayList<String> adjMatrix(ArrayList<String> str)
    {
        ArrayList<String> str1 = new ArrayList<String>();
        for(int i=0;i<str.size();i++)
        {
            String[] str2 = str.get(i).split(",");
            //if(str[i].indexOf("1")!=-1)
            for(int j=0;j<str2.length;j++)
            {
                if(!str2[j].equals("0"))
                {
                    str1.add("("+i+","+(j+1)+")");
                }
                
            }
        }
        return str1; 
    }
    public ArrayList<String> adjList(ArrayList<String> str)
    {
        ArrayList<String> str1 = new ArrayList<String>();
        for(int i=0;i<str.size();i++)
        {
            //System.out.println(str[i]);
            if(str.get(i).length()!=1)
            {
            String[] str2 = str.get(i).split("->");
            for(int j=1;j<str2.length;j++)
            {
                str1.add("("+str2[0]+","+str2[j]+")");
                //System.out.println("("+str2[0]+","+str2[j]+")");
            }
            }
        }
        return str1;
    }
}