
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class BFS_1 {
    static Hashtable<Character,ArrayList<Character>> h = new Hashtable<Character,ArrayList<Character>>();
    static int z;
    public static void main(String[] args) 
    {
        
        ArrayList<String> str2 = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        z = sc.nextInt();
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
            }
            else
            {
                h.put(str2.get(i).charAt(3),temp1);
            }   
        }
        //ArrayList<Character> temp1 = new ArrayList<Character>();
        //h.put('3', temp1);
        //h.put('6', temp1);
        BFS(1);
    }
    public static void BFS(int s){
        int[] d = new int[z+1];
        int[] parent = new int[z+1];
        Arrays.fill(parent, -1);
        d[s] = 0;
        parent[s] = s;
        Queue<Integer> Q = new LinkedList<Integer>();
        Q.add(s);
        Queue<Integer> Qdash = new LinkedList<Integer>();
        ArrayList<Integer> order = new ArrayList<Integer>();
        
        int l=0;
        while(!Q.isEmpty()){
            order.addAll(Q);
                        //System.out.println(order);
            for(int u : Q){
                            String s2 = u+"";
                            ArrayList<Character> t = h.get(s2.charAt(0));
                for(int v=0; v<t.size(); v++){
                    //if(adjacencyMatrix[u-1][v] == 1){
                                    
                        if(parent[t.get(v)-48] == -1)
                                                {
                            Qdash.add(t.get(v)-48);
                            d[t.get(v)-48] = l+1;
                            parent[t.get(v)-48] = u;
                        }
                    //}
                }
            }
            Q = Qdash;
            Qdash = new LinkedList<Integer>();
            l++;
        }
                int i;
                for(i=0;i<order.size()-1;i++)
                {
                    System.out.print(d[order.get(i)]+",");
                }
                System.out.print(d[order.get(i)]);
                System.out.println();
                for(i=0;i<order.size()-1;i++)
                {
                    System.out.print(order.get(i)+",");
                }
                System.out.print(order.get(i));
        //System.out.println(order);
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
        int count=0;
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
