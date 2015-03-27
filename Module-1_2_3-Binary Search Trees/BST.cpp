#include <iostream>
#include <string>
#include <sstream>
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
void del(int);
void case_a(node *,node *);
void case_b(node *,node *);
void case_c(node *,node *);
void preorder(node *);
void inorder(node *);
void postorder(node *);
 BST()
{
root = NULL;
}};
   
int BST::find(int item, node **par, node **loc)
{
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
    //min Right to max Left
    void BST::del(int item)
    {
        node *parent, *location;
        if (root == NULL)
        {
            cout<<"Tree empty"<<endl;
            return;
        }
        find(item, &parent, &location);
        if (location == NULL)
        {
            cout<<"Item not present in tree"<<endl;
            return;
        }
        if (location->left == NULL && location->right == NULL)
            case_a(parent, location);
        if (location->left != NULL && location->right == NULL)
            case_b(parent, location);
        if (location->left == NULL && location->right != NULL)
            case_b(parent, location);
        if (location->left != NULL && location->right != NULL)
            case_c(parent, location);
        free(location);
    }
    void BST::case_a(node *par, node *loc )
    {
        if (par == NULL)
        {
            root = NULL;
        }
        else
        {
            if (loc == par->left)
                par->left = NULL;
            else
                par->right = NULL;
        }
    }
    void BST::case_b(node *par, node *loc)
    {
        node *child;
        if (loc->left != NULL)
            child = loc->left;
        else
            child = loc->right;
        if (par == NULL)
        {
            root = child;
        }
        else
        {
            if (loc == par->left)
                par->left = child;
            else
                par->right = child;
        }
    }
    void BST::case_c(node *par, node *loc)
    {
        node *ptr, *ptrsave, *suc, *parsuc;
        ptrsave = loc;
        /*ptr = loc->right;
        while (ptr->left != NULL)
        {
            ptrsave = ptr;
            ptr = ptr->left;
        }*/
        ptr = loc->left;
        while (ptr->right != NULL)
        {
            ptrsave = ptr;
            ptr = ptr->right;
        }
        suc = ptr;
        parsuc = ptrsave;
        if (suc->left == NULL && suc->right == NULL)
            case_a(parsuc, suc);
        else
            case_b(parsuc, suc);
        if (par == NULL)
        {
            root = suc;
        }
        else
        {
            if (loc == par->left)
                par->left = suc;
            else
                par->right = suc;
        }
        suc->left = loc->left;
        suc->right = loc->right;
    }
    void BST::preorder(node *ptr)
    {
        if (root == NULL)
        {
            cout<<"Tree is empty"<<endl;
            return;
        }
        if (ptr != NULL)
        {
            cout<<ptr->info<<"  ";
            preorder(ptr->left);
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
        if (ptr != NULL)
        {
            postorder(ptr->left);
            if (ptr->right && ptr->left) cout << ",";
            postorder(ptr->right);
            if (ptr->right || ptr->left) cout << ",";
            cout<<ptr->info;
        }
    }
int main() {
string a;
BST bst;
node *temp;
int k;
while (getline(cin,a)){
    istringstream s(a);
    while (getline(s,a,',')){
    switch(a.at(0)){
        case 'I': temp = new node;
                  temp->info = stoi(a.substr(1));
                  bst.insert(root, temp);
                  bst.inorder(root); 
                  cout << endl;
                  break;
        case 'S':node *parent, *location;
                k = bst.find(stoi(a.substr(1)), &parent, &location);
                if (location == NULL)
                    cout << "false,"<<k<<endl;
                else cout << "true,"<<k<<endl;    
                break;
        case 'R':bst.del(stoi(a.substr(1)));
                bst.postorder(root);
                cout << endl;
                break;
        default:cout << "Can't process instruction: " << a << endl;
                break;
    }}
}    
return 0;
}