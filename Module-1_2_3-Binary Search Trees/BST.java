
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
/**
 *
 * @author prithvi
 */
 class BSTNode
 {
     BSTNode left, right;
     int data;
     public BSTNode()
     {
         left = null;
         right = null;
         data = 0;
     }
     public BSTNode(int n)
     {
         left = null;
         right = null;
         data = n;
     }
     public void setLeft(BSTNode n)
     {
         left = n;
     }
     public void setRight(BSTNode n)
     {
         right = n;
     }
     public BSTNode getLeft()
     {
         return left;
     }
     public BSTNode getRight()
     {
         return right;
     }
     public void setData(int d)
     {
         data = d;
     }
     public int getData()
     {
         return data;
     }     
 }
 
 /* Class BST */
 class BST
 {
     private BSTNode root;
     private int count,temp,count1;
     private String s="";
     public BST()
     {
         root = null;
     }
     public boolean isEmpty()
     {
         return root == null;
     }
     public void insert(int data)
     {
         root = insert(root, data);
     }
     private BSTNode insert(BSTNode node, int data)
     {
         if (node == null)
             node = new BSTNode(data);
         else
         {
             if (data <= node.getData())
                 node.left = insert(node.left, data);
             else
                 node.right = insert(node.right, data);
         }
         return node;
     }
     public void delete(int k)
     {
         if (isEmpty())
             System.out.println("Tree Empty");
         else
         {
             root = delete(root, k);
         }
     }
     private BSTNode delete(BSTNode root, int k)
     {
         BSTNode p, p2, n;
         if (root.getData() == k)
         {
             BSTNode lt, rt;
             lt = root.getLeft();
             rt = root.getRight();
             if (lt == null && rt == null)
                 return null;
             else if (lt == null)
             {
                 p = rt;
                 return p;
             }
             else if (rt == null)
             {
                 p = lt;
                 return p;
             }
             else
             {
                 p2 = rt;
                 p = lt;
                 while (p.getRight() != null)
                     p = p.getRight();
                 root.setData(p.getData());
                 root.setLeft(lt);
                 lt.setRight(null);
                 return root;
             }
         }
         if (k < root.getData())
         {
             n = delete(root.getLeft(), k);
             root.setLeft(n);
         }
         else
         {
             n = delete(root.getRight(), k);
             root.setRight(n);             
         }
         return root;
     }
     private boolean search(BSTNode r, int val)
     {
         boolean found = false,flag = true;
         while ((r != null) && !found)
         {
             count++;
             int rval = r.getData();
             if (val < rval)
             {
                 r = r.getLeft();
                if(flag)
                 {
                     temp = rval;
                     flag = false;
                 }
             }
             else if (val > rval)
             {
                 r = r.getRight();                
             }
             else
             {
                 found = true;
                 break;
             }
             found = search(r, val);
         }
         return found;
     }
     private void inorder(BSTNode r)
     {
         if(r!=null)
             {                
                inorder(r.getLeft());
                s = s+r.getData()+",";
                inorder(r.getRight());
            }
         
     }
     private void postorder(BSTNode r)
     {
         if (r != null)
         {
             postorder(r.getLeft());             
             postorder(r.getRight());
             s = s+r.getData()+",";
         }
     }
     public static void main(String[] args) 
     {
        Scanner scan=new Scanner(System.in);
                boolean a;
        String s=scan.nextLine();   
        String starr[]=s.split(",");
        BST bst=new BST();
        for(int i=0;i<starr.length;i++){
            if(starr[i].charAt(0)=='I')
                        {
                bst.insert(Integer.parseInt(starr[i].substring(1)));
                                bst.inorder(bst.root);
                                System.out.print(bst.s.substring(0,bst.s.length()-1));
                                bst.s = "";
                        }
            if(starr[i].charAt(0)=='R')
                        {
                            bst.delete(Integer.parseInt(starr[i].substring(1)));
                            bst.postorder(bst.root);
                            System.out.print(bst.s.substring(0,bst.s.length()-1));
                            bst.s = "";
                        }
            if(starr[i].charAt(0)=='S')
                        {
                a = bst.search(bst.root,Integer.parseInt(starr[i].substring(1)));
                                if(a)
                                {
                                    System.out.print(a+","+bst.count);
                                    bst.count = 0;                                    
                                }
                                else
                                {
                                    System.out.print(false+","+bst.temp);
                                }
                        }
        
        System.out.println();
        }
    }
 }
