/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;
/**
 *
 * @author prithvi
 */
public class Graph 
{
    public static void main(String[] args)
    {
        Hashtable<Character,String> h = new Hashtable<Character,String>();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] s2 = s.split(", ");
        ArrayList a = new ArrayList();
        String s3 = "";
        for(int i = 0;i<s2.length;i++)
        {
            if(s3.indexOf(s2[i].charAt(1)) == -1)
            {
                s3 = s3+ s2[i].charAt(1);
                a.add(s2[i].charAt(1));
            }
            if(s3.indexOf(s2[i].charAt(3)) == -1)
            {
                s3 = s3+ s2[i].charAt(3);
                a.add(s2[i].charAt(3));
            }
        }
        Collections.sort(a);
        s3 = "";
        for(int k=0;k<a.size();k++)
        {
            s3 = s3+a.get(k);
            //System.out.println(a.get(k));
        }
        int n = s3.length();
        //System.out.println(s3);
        int[][] matrix = new int[n][n];
        //int n = Integer.parseInt(sc.nextLine());
        for(int i = 0;i<n;i++)
        {
            h.put(s3.charAt(i), "");
        }
        //h.put(s.charAt(0), s3);
        for(int i=0;i<s2.length;i++)
        {
            String s4 = h.get(s2[i].charAt(1));
            String s5 = s4+"->"+s2[i].charAt(3);
            h.put(s2[i].charAt(1),s5);
            if((s2[i].charAt(1))>96)
                matrix[(int)(s2[i].charAt(1))-97][(int)(s2[i].charAt(3))-97]=1;
            else
            {
                //System.out.println(s2[i].charAt(1));
                matrix[Integer.parseInt(""+s2[i].charAt(1))-1][Integer.parseInt(""+s2[i].charAt(3))-1]=1;
            }
        }
        System.out.print(" ");
        for(int l =0;l<s3.length();l++)
        {
            System.out.print(" "+s3.charAt(l));
        }
        System.out.println();
        int i = 0;
        for (int row = 0; row < matrix.length; row++) 
        {
            System.out.print(s3.charAt(i));
        for (int column = 0; column < matrix[row].length; column++) 
        {            
            System.out.print(" "+matrix[row][column]);            
        }
        i++;
        System.out.println();
        }
        System.out.println();
        for(int j = 0;j<n;j++)
        {
            String s6 = h.get(s3.charAt(j));
            String[] s7 = s6.split("->");
            Arrays.sort(s7);
            System.out.print(s3.charAt(j));
            for(int k=1;k<s7.length;k++)
             System.out.print("->"+s7[k]);
            System.out.print("->"+"null");
            System.out.println();
        }
    }
}
