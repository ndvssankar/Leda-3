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
public class DFS {
    /**
     * @param args the command line arguments
     */
    static String str = "";
    static Hashtable<Character,ArrayList<Character>> h = new Hashtable<Character,ArrayList<Character>>();
    static Stack<Character> st = new Stack<Character>();
    public static void main(String[] args) 
    {
        
        ArrayList<String> str2 = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        int z = sc.nextInt();
        int y = sc.nextInt();
        if(y==0)
        {
            String[] s2 = new String[z+1];
            for(int i=0;i<=z;i++)
            {
                s2[i] = sc.nextLine();
            }
            str2 = adjList(s2);
        }
        if(y==1)
        {
            String[] s2 = new String[z+1];
            for(int i=0;i<=z;i++)
            {
                s2[i] = sc.nextLine();
            }
            str2 = adjMatrix(s2);
        }
        //String s = sc.nextLine();
        // TODO code application logic here
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
            if(h.containsKey(str2.get(i).charAt(3)))
            {
                temp1 = h.get(str2.get(i).charAt(3));
                temp1.add(str2.get(i).charAt(1));
                Collections.sort(temp1);
                h.put(str2.get(i).charAt(3),temp1);
            }
            else
            {
                temp1.add(str2.get(i).charAt(1));
                h.put(str2.get(i).charAt(3),temp1);
            }   
        }
        /*Enumeration e = h.keys();
        while(e.hasMoreElements())
        {
            ArrayList temp = h.get(e.nextElement());
            for(int i=0;i<temp.size();i++)
            {
                System.out.print(temp.get(i)+" ");
            }
            System.out.println();
        }*/
        String temp=1+"";
        //String str = "";
        depth_first_search(temp);
        System.out.println(str);
    }
    public static ArrayList<String> adjList(String[] str)
    {
        ArrayList<String> str1 = new ArrayList<String>();
        for(int i=0;i<str.length;i++)
        {
            //System.out.println(str[i]);
            if(str[i].length()!=1)
            {
            String[] str2 = str[i].split("->");
            for(int j=1;j<str2.length;j++)
            {
                str1.add("("+str2[0]+","+str2[j]+")");
                //System.out.println("("+str2[0]+","+str2[j]+")");
            }
            }
        }
        return str1;
    }
    public static void depth_first_search(String temp)
        {
        
        if(str.indexOf(temp.charAt(0)+"")==-1)
        {
            st.push(temp.charAt(0));
            str=str+temp.charAt(0)+" ";
            while(!st.empty())
            {
                ArrayList<Character> al = h.get(st.peek());
                for (int i = 0; i < al.size() ; i++) 
                {
                    depth_first_search(al.get(i)+"");   
                }
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
    public static ArrayList<String> adjMatrix(String[] str)
    {
        ArrayList<String> str1 = new ArrayList<String>();
        for(int i=0;i<str.length;i++)
        {
            //System.out.println(str[i]);
            String[] str2 = str[i].split(" ");
            for(int j=0;j<str2.length;j++)
            {
                if(str2[j].equals("1"))
                {
                    str1.add("("+i+","+(j+1)+")");
                    //System.out.println("("+i+","+(j+1)+")");
                }
            }
        }
        return str1; 
    }
}