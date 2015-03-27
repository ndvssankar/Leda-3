/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 *
 * @author prithvi
 */
public class BFS {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Hashtable<Character,ArrayList<Character>> h = new Hashtable<Character,ArrayList<Character>>();
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
       /* Enumeration e = h.keys();
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
        String str = "";
        Queue<Character> q = new LinkedList<Character>();
        q.add(temp.charAt(0));
        while(!q.isEmpty())
        {            
            ArrayList<Character> t1 = h.get(q.element());
            if(str.indexOf(q.element()+"")== -1)
            {
                str=str+q.remove()+" ";
                //System.out.println(str);
                if(t1!=null)
                    for(int i=0;i<t1.size();i++)
                    {
                        q.add(t1.get(i));
                    }
            }
            else
                q.remove();
        }
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
