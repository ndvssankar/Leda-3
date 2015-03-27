//package javaapplication33;
import java.util.*;
import java.io.*;
//package javaapplication33;
import java.util.*;
import java.io.*;
class Solution
{
    public Hashtable<String,ArrayList<String>> h = new Hashtable<String,ArrayList<String>>();
    public Hashtable<Integer,String> vertex1 = new Hashtable<Integer,String>();
    public Hashtable<String,Integer> vertex2 = new Hashtable<String,Integer>();
    public ArrayList<String> result_set = new ArrayList<String>();
    public int [] d ;
    public int [] parent;
    public void insert_vertices(String[] s)
    {
        for (int i = 0; i < s.length ; i++) 
        {
            vertex1.put((i+1),s[i]);    
        }
        for (int i = 0; i < s.length ; i++) 
        {
            vertex2.put(s[i],(i+1));    
        }
    }
    public void print_Output()
    {
        String t ="";
        for(int i = 0; i < vertex1.size(); i++)
        {
            int distance = d[i+1];
            for(int j = (i+1); j > 0 ; j = parent[j])
            {
                if (j == 1 || j == 99999) 
                {
                    t = t+vertex1.get(1);
                    break;
                }
                else
                    t = t+vertex1.get(j)+">-";
            }
            t = reverse(t);
            System.out.print(t+":"+distance);
            System.out.println();
            t ="";
        }   
    }
    public String reverse(String s)
    {
        String reverse = "";
        for ( int i = s.length() - 1 ; i >= 0 ; i-- )
         reverse = reverse + s.charAt(i);
        return reverse;
    }   
    public void dij_Alg(int s)
    {
        Arrays.fill(d,99999);
        Arrays.fill(parent,99999);
        int temp;
        parent[s] = s;
        Queue<String> q = new LinkedList<String>();
        d[s] = 0;       
        q.add(s+","+d[s]);
        while(!q.isEmpty())
        {
            String u = delete_Min(q);
            result_set.add(u);
            ArrayList<String> v = h.get(u);
            for(int i = 0 ; i < v.size() ; i++ )
            {
                if(!v.get(i).equals("0"))
                {
                    int e = Integer.parseInt(v.get(i));
                    if((d[Integer.parseInt(u)] + e ) < d[i+1])
                    {
                        temp = d[i+1];
                        d[i+1] = (d[Integer.parseInt(u)] + e );
                        parent[i+1] = Integer.parseInt(u);
                        if (q.contains((i+1)+","+d[(i+1)]) || q.contains((i+1)+","+temp)) 
                        {
                            q = decrease_Key(q);
                            q = sort(q);                            
                        }
                        else
                        {
                            q.add((i+1)+","+d[(i+1)]);
                            q = sort(q);                            
                        }
                    }
                }
            }
        }
    } 
    public Queue<String> decrease_Key(Queue<String> q)
    {
        Queue<String> temp = new LinkedList<String>();
        String[] y = q.toArray(new String[q.size()]);
        for(int i = 0; i < y.length; i++)
        {
            String s = y[i];
            String[] t = s.split(",");
            for (int j = 1; j < d.length ; j++ ) 
            {
                if(Integer.parseInt(t[0]) == j )
                {
                    y[i] = j+","+d[j];
                }   
            }
        }
        for(int j = 0; j < y.length; j++)
        {
            temp.add(y[j]);
        }
        return temp;
    }
    public Queue<String> sort(Queue<String> q)
    {
        Queue<String> temp = new LinkedList<String>();
        int c,d;
        String swap;
        ArrayList<String> a = new ArrayList<String>();
        String[] y = q.toArray(new String[q.size()]);
        for (c = 0; c < y.length - 1; c++) 
        {
            for (d = 0; d < y.length - c - 1; d++) 
            {
                String s1 = y[d];
                String[] t1 = s1.split(",");
                String s2 = y[d+1];
                String[] t2 = s2.split(",");
                if (Integer.parseInt(t1[1]) > Integer.parseInt(t2[1])) 
                {
                    swap   = y[d];
                    y[d]   = y[d+1];
                    y[d+1] = swap;
                }
            }
        }
        for(int i = 0; i< y.length; i++)
        {
            a.add(y[i]);
        }       
        if(q.size() > 1)
        {
            temp.addAll(a);         
            return temp;
        }
        else
            return q;
    }
    public String delete_Min(Queue<String> q)
    {
        String s = q.poll();
        String[] t = s.split(",");
            return t[0];        
    }    
    public void insert_Into_Hash(String[] s)
    {
        for (int i = 0; i < s.length ; i++) 
        {
            ArrayList<String> temp = new ArrayList<String>();
            String t = s[i].substring(1,s[i].length()-1);
            String[] sp = t.split(",");
            if(h.containsKey(sp[0]))
            {
                temp = h.get(sp[0]);
                temp.add(sp[1]);
                h.put(sp[0],temp);              
            }
            else
            {
                temp.add(sp[1]);
                h.put(sp[0],temp);
            }           
        }       
    }
}
class DA
{
    public static void main(String[] args) 
    {
        Solution da = new Solution();
        Scanner scan = new Scanner(System.in);
        int count = 0;
        ArrayList<String> a = new ArrayList<String>();
        int n = Integer.parseInt(scan.nextLine());
        String source = scan.nextLine();
        String set = scan.nextLine();
        set = set.substring(1,set.length()-1);
        String [] vertices = set.split(",");
        da.insert_vertices(vertices);
        while(count < n)
            {
                String s2 = scan.nextLine();
                String[] s3 = s2.split(",");
                for (int i = 0; i < s3.length ;i++ ) 
                {
                    a.add("("+(count+1)+","+s3[i]+")");
                }
                count++;
            }           
        String[] s1 = new String[a.size()];
        for(int i = 0; i < s1.length; i++)
        {
            s1[i] = a.get(i);
        }
        da.insert_Into_Hash(s1);
        da.d = new int[n+1];
        da.parent = new int[n+1];
        da.dij_Alg(da.vertex2.get(source));
        String t ="";
        for(int i = 0; i < da.vertex1.size(); i++)
        {
            int distance = da.d[i+1];
            for(int j = (i+1); j > 0 ; j = da.parent[j])
            {
                if (j == 1 || j == 99999) 
                {
                    t = t+da.vertex1.get(1);
                    break;
                }
                else
                    t = t+da.vertex1.get(j)+">-";
            }
            t = da.reverse(t);
            System.out.print(t+":"+distance);
            System.out.println();
            t ="";
        }
    }
}