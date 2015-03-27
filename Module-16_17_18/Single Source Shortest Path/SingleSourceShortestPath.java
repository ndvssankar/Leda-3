
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class DA {
    private Hashtable<Character,ArrayList<String>> h = new Hashtable<Character,ArrayList<String>>();
    private Hashtable<Character,Integer> ht = new Hashtable<Character,Integer>();
    private Hashtable<Character,Character> ht1 = new Hashtable<Character,Character>();
    private int z;
    private String[] w; 
    public static void main(String[] args) 
    {
        DA cycle = new DA();        
        ArrayList<String> str2 = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        cycle.z = sc.nextInt();
        String h1 = sc.nextLine();
        String y = sc.nextLine();
        String f = sc.nextLine();
        f = f.substring(1,f.length()-1);
        cycle.w = f.split(",");
        for(int i=0;i<cycle.w.length;i++)
        {
            cycle.ht.put(cycle.w[i].charAt(0), i);
        }
            String[] s2 = new String[cycle.z];
            for(int i=0;i<cycle.z;i++)
            {
                s2[i] = sc.nextLine();
            }
            str2 = cycle.adjMatrix(s2);
       cycle.hash(str2);
        cycle.BFS(y.charAt(0));
    }
    public void hash(ArrayList<String> str2)
        {
        for (int i = 0; i < str2.size() ; i++) 
        {
            ArrayList<String> temp = new ArrayList<String>();
            if(h.containsKey(str2.get(i).charAt(0)))
            {
                temp = h.get((str2.get(i)).charAt(0));
                temp.add(str2.get(i).substring(2));
                Collections.sort(temp);
                h.put(str2.get(i).charAt(0),temp);  
            }
            else
            {
                temp.add(str2.get(i).substring(2));
                h.put(str2.get(i).charAt(0),temp);
            }   
        }
        for (int i = 0; i < str2.size() ; i++) 
        {
            ArrayList<String> temp1 = new ArrayList<String>();
            if(!h.containsKey(str2.get(i).charAt(2)))
            {
                h.put(str2.get(i).charAt(2),temp1);
            }   
        }
    }
    public void hash1(char[] parent)
        {
        for(int i=0;i<w.length;i++)
                {
                    if(parent[i]!='p')
                    ht1.put(w[i].charAt(0), parent[i]);
                    else
                        ht1.put(w[i].charAt(0),'s');
                }
    }
    public void BFS(char y)
    {
        int[] d = new int[z+1];
        char[] parent = new char[z+1];
        Arrays.fill(parent, 'p');
        Arrays.fill(d, 99999);
        d[0] = 0;
        parent[0] = y;
        Queue<String> Q = new LinkedList<String>();
        String S = y+","+0;
        Q.add(S);
        ArrayList<String> order = new ArrayList<String>();      
        int l=0;
        while(!Q.isEmpty())
        {
            sort(Q);
            String u = Q.remove();
            ArrayList<String> t = h.get(u.charAt(0));
            for(int v=0; v<t.size(); v++)
                {
                 if((d[ht.get(u.charAt(0))]+t.get(v).charAt(2)-48) < d[ht.get(t.get(v).charAt(0))])
                      {
                           d[ht.get(t.get(v).charAt(0))]=d[ht.get(u.charAt(0))]+Integer.parseInt(t.get(v).substring(2));
                           parent[ht.get(t.get(v).charAt(0))] = u.charAt(0);
                           order.addAll(Q);
                           int flag = 0,k;
                           for(k = 0;k<order.size();k++)
                           {
                                if(order.get(k).indexOf((t.get(v).charAt(0)+""))!=-1)
                                {
                                     flag = 1;
                                     break;
                                }
                           }
                            if(flag == 1)
                            {
                                 String str = order.get(k);
                                 order.remove(k);
                                  str = str.charAt(0)+","+d[ht.get(t.get(v).charAt(0))];
                                  order.add(str);
                            }
                            else
                            {
                                   String str = "";
                                    str = t.get(v).charAt(0)+","+d[ht.get(t.get(v).charAt(0))];
                                    order.add(str);
                             }
                             Q.clear();
                             Q.addAll(order);
                             order.clear();
                       }
                   }
           }
            hash1(parent);
            print(parent,d);
        }
    public void print(char[] parent,int[] d)
        {
        for(int j = 0;j<parent.length-1;j++)
                {
                    String str1 = "";
                    System.out.print(w[0].charAt(0));
                    char q = w[j].charAt(0);
                    while(q!=w[0].charAt(0))
                    {
                        str1 = str1+q;
                        q = ht1.get(q);
                    }
                    for(int i=str1.length()-1;i>=0;i--)
                    {
                        System.out.print("->"+str1.charAt(i));
                    }
                    System.out.print(":"+d[j]);
                    System.out.println();
                }
    }
        public Queue<String> sort(Queue<String> Q)
        {
            ArrayList<String> t=new ArrayList<String>();
            t.addAll(Q);
            Collections.sort(t,new MySalaryComp());
            Q.clear();
            Q.addAll(t);
            return Q;
        }
        public ArrayList<String> adjMatrix(String[] str)
    {
        int count=0;
        ArrayList<String> str1 = new ArrayList<String>();
        for(int i=0;i<str.length;i++)
        {
            String[] str2 = str[i].split(",");
            for(int j=0;j<str2.length;j++)
            {
                if(!str2[j].equals("0"))
                {
                    str1.add(w[i]+","+w[j]+","+str2[j]);
                }
            }
        }
        return str1; 
    }
}
class MySalaryComp implements Comparator<String>{
 
    @Override
    public int compare(String e1, String e2) {
        if(Integer.parseInt(e1.substring(2))>Integer.parseInt(e2.substring(2))){
            return 1;
        } else {
            return -1;
        }
    }
}