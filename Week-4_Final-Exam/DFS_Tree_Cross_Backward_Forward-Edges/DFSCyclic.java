//package dfs_2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class DFS_2 
{
    private int[] dfs;
    private int[] fds;
    private Hashtable<String,ArrayList<String>> h = new Hashtable<String,ArrayList<String>>();
    private Hashtable<String,Integer> ht = new Hashtable<String,Integer>();
    private Hashtable<String,Integer> ht1 = new Hashtable<String,Integer>();
    private int z,dfs1=1,fds1=1,flag=0;
    private String str="";
    private String[] st1;
    public static void main(String[] args)
    {
        DFS_2 main=new DFS_2();
        ArrayList<String> str2 = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        main.z = sc.nextInt();
        //System.out.println(z);
        int y = sc.nextInt();
        String ch1 = sc.nextLine();
        main.dfs = new int[main.z+1];
        main.fds = new int[main.z+1];
        
        String ch = sc.nextLine();
        //System.out.println(ch);
        String st=sc.nextLine();
        //System.out.println(st);
        //System.out.println(st+" "+y+" "+main.z);
        main.st1 = st.split(",");
        for(int i=0;i<main.st1.length;i++)
        {
            main.ht.put(main.st1[i], i);
        }
        if(y==0)
        {
            String[] s2 = new String[main.z+1];
            int i =0;
            while(sc.hasNext())
            {
                s2[i] = sc.nextLine();
                //System.out.println(s2[i]);
                i++;
            }
            str2 = main.adjList(s2);
        }
        if(y==1)
        {
            String[] s2 = new String[main.z+1];
            int i = 0;
            while(i<main.z)
            {
                s2[i] = sc.nextLine();
                //System.out.println(s2[i]);
                i++;
            }
            str2 = main.adjMatrix(s2);
        }
        main.hashing(str2);
        main.str = ch.charAt(0)+"";
        //System.out.println(main.str);
        main.dfs[main.ht.get(main.str)]=main.dfs1++;
        main.DFS(main.str,main.str);
        //System.out.print("dfs position : ");
        int i;
        for(i = 0;i<main.str.length()-1;i++)
        {
            //System.out.println(main.str.charAt(i));
            System.out.print(main.str.charAt(i)+",");
        }
        System.out.println(main.str.charAt(i));
        
        main.check(str2);
        if(main.flag==1)
            System.out.println("The graph has cycles");
        else
            System.out.println("The graph has no cycles");
    }
    public void hashing(ArrayList<String> str2)
    {
        for (int i = 0; i < str2.size() ; i++) 
        {
            String[] s3 = str2.get(i).substring(1,str2.get(i).length()-1).split(",");
            ArrayList<String> temp = new ArrayList<String>();
            if(h.containsKey(s3[0]))
            {
                temp = h.get(s3[0]);
               // System.out.println(s3[0]);
                temp.add(s3[1]);
                Collections.sort(temp);
                //System.out.println(temp);
                h.put(s3[0],temp);  
            }
            else
            {
                temp.add(s3[1]);
                //System.out.println(temp);
                h.put(s3[0],temp);
            }   
        }
        for (int i = 0; i < str2.size() ; i++) 
        {
            ArrayList<String> temp1 = new ArrayList<String>();
            String[] s3 = str2.get(i).substring(1,str2.get(i).length()-1).split(",");
            if(h.containsKey(s3[1]))
            {
            }
            else
            {
                h.put(s3[1],temp1);
            }   
        }
    }
    public void DFS(String u,String v)
        {
            ArrayList<String> t;
            t = h.get(v);
            //System.out.println(v);
            for(int i = 0;i<t.size();i++)
            {
                //System.out.println(t.get(i));
                if(str.indexOf(t.get(i))!=-1)
                {
                    //System.out.println(str+"   "+t.get(i));
                }
                else
                {
                   // System.out.println("value"+t.get(i));
                    //System.out.println(ht.get(t.get(i)));
                    dfs[ht.get(t.get(i))]=dfs1++;
                   // System.out.println("value"+t.get(i));
                    str=str+t.get(i);
                    //System.out.println(str);
                    DFS(v,t.get(i));
                }
            }
            fds[ht.get(v)] = fds1++; 
    }
        public ArrayList<String> adjList(String[] str)
    {
        ArrayList<String> str1 = new ArrayList<String>();
        for(int i=0;i<str.length-1;i++)
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
        public ArrayList<String> adjMatrix(String[] str)
    {
        int count=0;
        ArrayList<String> str1 = new ArrayList<String>();
        for(int i=0;i<str.length-1;i++)
        {
            //System.out.println(str[i]);
            String[] str2 = str[i].split(",");
            for(int j=0;j<str2.length;j++)
            {
                //System.out.println(st1[i]+" "+st1[j]);
                if(str2[j].equals("1"))
                {
                    //System.out.println(st1[i]+" "+st1[j]);
                    str1.add("("+st1[i]+","+st1[j]+")");
                   // System.out.println("("+i+","+(j+1)+")");
                }
            }
        }
        return str1; 
    }
        public void check(ArrayList<String> str1)
        {
            for(int i=0;i<str1.size();i++)
            {
                int flag1=0,flag2=0;
                if(dfs[ht.get(str1.get(i).charAt(1)+"")]<dfs[ht.get(str1.get(i).charAt(3)+"")])
                    flag1 = 1;
                if(fds[ht.get(str1.get(i).charAt(3)+"")]<fds[ht.get(str1.get(i).charAt(1)+"")])
                    flag2 = 1;
                if(flag1==1&& flag2==1)
                    System.out.println(str1.get(i).charAt(1)+"->"+str1.get(i).charAt(3)+": Tree Edge / Forward Edge");
                if(flag1==0&&flag2==1)
                    System.out.println(str1.get(i).charAt(1)+"->"+str1.get(i).charAt(3)+": Cross Edge");
                if(flag1==0&&flag2==0)
                {
                    System.out.println(str1.get(i).charAt(1)+"->"+str1.get(i).charAt(3)+": Backward Edge");
                    flag=1;
                }
            }
        }
}
