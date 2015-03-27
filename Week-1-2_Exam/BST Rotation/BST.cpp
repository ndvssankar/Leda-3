#include <iostream>
#include <sstream>
#include <string>
using namespace std;
struct node
{
int info;
struct node *left;
struct node *right;
}*root;
class BST
{
public:
int find(int, node **, node **);
void insert(int);
void insert(node *tree, node *newnode);
void preorder(node *);
void inorder(node *);
void postorder(node *);
void rotate(int newroot,int* nr);
void rotate(node*,node* );
    
 BST()
{
root = NULL;
}};
int BST::find(int item, node **par, node **loc)
{
//cout << "looking for " << item << endl;
node *ptr, *ptrsave;
int trav = 1;
if (root == NULL)
{
*loc = NULL;
*par = NULL;
return 0;
}
if (item == root->info)
{
            *loc = root;
            *par = NULL;
            return trav;
        }
        if (item < root->info)
            {ptr = root->left;
            trav++;}
        else
            {ptr = root->right;
            trav++;}
        ptrsave = root;
        while (ptr != NULL)
        {
            if (item == ptr->info)
            {
                *loc = ptr;
                *par = ptrsave;
                return trav;
            }
            ptrsave = ptr;
            if (item < ptr->info)
            {ptr = ptr->left;
            trav++;}
        else
           {ptr = ptr->right;
           trav++;}
        }
        *loc = NULL;
        *par = ptrsave;
    return trav+1;
    }
    void BST::insert(node *tree, node *newnode)
    {
        if (root == NULL)
        {
            root = new node;
            root->info = newnode->info;
            root->left = NULL;
            root->right = NULL;
            return;
        }
        if (tree->info == newnode->info)
        {
            return;
        }
        if (tree->info > newnode->info)
        {
            if (tree->left != NULL)
            {
                insert(tree->left, newnode);
        }
        else
        {
                tree->left = newnode;
                (tree->left)->left = NULL;
                (tree->left)->right = NULL;
                return;
            }
        }
        else
        {
            if (tree->right != NULL)
            {
                insert(tree->right, newnode);
            }
            else
            {
                tree->right = newnode;
                (tree->right)->left = NULL;
                (tree->right)->right = NULL;
                return;
            }
        }
    }
    void BST::preorder(node *ptr)
    {
        if (root == NULL)
        {
            cout<<"Tree is empty"<<endl;
            return;
        }
        //cout << "pre on " << ptr->info << (ptr->left==NULL ? "l null ": " l ok ") << (ptr->right==NULL ? "right null": " rt ok") << endl;
        if (ptr != NULL)
        {
            cout<<ptr->info<<"  ";
            if (ptr->left || ptr->right) cout << ",";
            preorder(ptr->left);
            if (ptr->left && ptr->right) cout << ",";
            preorder(ptr->right);
        }
    }
    void BST::inorder(node *ptr)
    {
        if (root == NULL)
        {
            cout<<"Tree is empty"<<endl;
            return;
        }
        //cout << "in on " << ptr->info << (ptr->left==NULL ? "l null ": " l ok ") << (ptr->right==NULL ? "right null": " rt ok") << endl;
        if (ptr != NULL)
        {
         inorder(ptr->left);
        if (ptr->left) cout << ",";
        cout << ptr->info;
        if (ptr->right) cout << ",";
          inorder(ptr->right);
        }
    }
    void BST::postorder(node *ptr)
    {
        if (root == NULL)
        {
            cout<<"Tree is empty"<<endl;
            return;
        }
        //cout << "post on " << ptr->info << (ptr->left==NULL ? "l null ": " l ok ") << (ptr->right==NULL ? "right null": " rt ok") << endl;
        if (ptr != NULL)
        {
            postorder(ptr->left);
            if (ptr->right && ptr->left) cout << ",";
            postorder(ptr->right);
            if (ptr->right || ptr->left) cout << ",";
            cout<<ptr->info;
        }
    }
    void BST::rotate(int newroot,int* nr){
        node *parent, *loc;
        if (root->info==newroot)
            {//cout << " rotn done\n";
            return ;}
        else {
            find(newroot,&parent,&loc);
              rotate(parent,loc);   
             (*nr)++; 
        }
              rotate(newroot,nr);
    }
    void BST::rotate(node* x,node* y){
        //cout << " X " << x->info;
        //cout << " Y " << y->info;
        //cout << " while root is " << root->info  << endl;
        node* xpar;
        if (x->right == y){ //left rotate
        x->right = y-> left; 
        if (x==root) //if x is root
            root = y;
        else {
            find(x->info,&xpar,&x);
            if (x == xpar->left)
                xpar->left=y;
            else xpar->right= y;}
        y->left = x;
        }
     else  {//right rotate
         x->left = y->right;
         if (x==root)
             root=y;
         else {find(x->info,&xpar,&x);
         if (x==xpar->left)
             xpar->left = y;
          else xpar->right = y;}
         y->right = x;        
     }      
    }
int main() {
string s;
BST bst;
node *temp;
int newroot,disp,nr=0;
getline(cin,s);
istringstream in(s);
while (getline(in,s,','))
    {temp = new node;
     istringstream tmp(s);
     tmp >> disp;
    temp->info = disp;
    bst.insert(root,temp);}
//bst.preorder(root);
cin >> newroot;
bst.rotate(newroot,&nr);
//bst.preorder(root);
cout << nr << endl;
cin >> disp;
switch(disp){
    case 1:bst.preorder(root);break;
    case 2:bst.inorder(root); break;
    case 3:bst.postorder(root); break;
}
return 0;
}
