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
    static ArrayList<Integer> c =new ArrayList<Integer>();
    public static void main(String[] args) 
    {
        
        ArrayList<String> str2 = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        int z = sc.nextInt();
        int y = sc.nextInt();
        if(y==1)
        {
            String[] s2 = new String[z+1];
            for(int i=0;i<=z;i++)
            {
                s2[i] = sc.nextLine();
            }
            str2 = adjMatrix(s2);
        }
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
        String temp=1+"";
        depth_first_search(temp);
        System.out.println(str.substring(0,str.length()-1));
    }
    public static void depth_first_search(String temp)
        {
        //int g=0;
        if(str.indexOf(temp.charAt(0)+"")==-1)
        {
            st.push(temp.charAt(0));
            str=str+temp.charAt(0)+",";
            //count++;
            while(!st.empty())
            {
                ArrayList<Character> al = h.get(st.peek());
                //c.add(count);
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
    public static ArrayList<String> adjMatrix(String[] str)
    {
        ArrayList<String> str1 = new ArrayList<String>();
        for(int i=0;i<str.length;i++)
        {
            String[] str2 = str[i].split(" ");
            if(str[i].indexOf("1")!=-1)
            for(int j=0;j<str2.length;j++)
            {
                if(str2[j].equals("1"))
                {
                    str1.add("("+i+","+(j+1)+")");
                }
                
            }
        }
        return str1; 
    }
}