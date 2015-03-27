#include<iostream>
#include <list>
#include <sstream>
#include <vector>
using namespace std;
class Graph
{
    int V;    // No. of vertices
    list<int> *adj;    // Pointer to an array containing adjacency lists
    bool isCyclicUtil(int v, bool visited[], bool *rs);  // used by isCyclic()
    string *name;
    bool* visited;
public:
    Graph(int V,string s);   // Constructor
    void addEdge(int v,int w);   // to add an edge to graph
    bool isCyclic();    // returns true if there is a cycle in this graph
    void pr();
    //Prints vector in expected format
            void pr(vector<int> a){
                for (int i=0,size=a.size();i<size;i++)
                    cout << a[i] << (i==size-1?'\n':',');
                }
            //Traverses graph in DFS recursively,puts nodes in given vector
            void traverseDFS(int startNode,vector<int> *a){
                visited[startNode-1]=true;
                a->push_back(startNode);
                for (int i:adj[startNode])
                       if (!visited[i])
                            traverseDFS(i+1,a);
            }
    int in(string s){
            for (int i = 0; i < V; i++)
                if(s.compare(name[i])==0)
                    return i;
            return -1;
        }
    
};
Graph::Graph(int V,string s)
{
    this->V = V;
    adj = new list<int>[V];
    name = new string[V];
    for (int i = 0; i < V; i++)
    visited[i]=false;
    istringstream is(s);
    int i = 0;
    while (getline(is,s,',')){
       name[i]=s;
       i++;
      }
}
void Graph::addEdge(int v, int w)
{
    adj[v].push_back(w); // Add w to v’s list.
}
bool Graph::isCyclicUtil(int v, bool visited[], bool *recStack)
{
    if(visited[v] == false)
    {
        // Mark the current node as visited and part of recursion stack
        visited[v] = true;
        recStack[v] = true;
        // Recur for all the vertices adjacent to this vertex
        list<int>::iterator i;
        for(i = adj[v].begin(); i != adj[v].end(); ++i)
        {
            if ( !visited[*i] && isCyclicUtil(*i, visited, recStack) )
                return true;
            else if (recStack[*i])
                return true;
        }
    }
    recStack[v] = false;  // remove the vertex from recursion stack
    return false;
}
// Returns true if the graph contains a cycle, else false.
bool Graph::isCyclic()
{
    // Mark all the vertices as not visited and not part of recursion stack
    bool *visited = new bool[V];
    bool *recStack = new bool[V];
    for(int i = 0; i < V; i++)
    {
        visited[i] = false;
        recStack[i] = false;
    }
    // Call the recursive helper function to detect cycle in different DFS trees
    for(int i = 0; i < V; i++)
        if (isCyclicUtil(i, visited, recStack))
        return true;
    return false;
}
//Prints graph
void Graph::pr(){
    for (int i=0;i<V;i++)
        for(list<int>::iterator j = adj[i].begin();j != adj[i].end(); ++j)
            cout << i << "->" << *j<< endl;
}
void replaceAll(std::string& str, const std::string& from, const std::string& to) {
    if(from.empty())
        return;
    size_t start_pos = 0;
    while((start_pos = str.find(from, start_pos)) != std::string::npos) {
        str.replace(start_pos, from.length(), to);
        start_pos += to.length();
    }
}
int toInt(string s){
int k=0;
for (char c:s)
    k=10*k+(c-'0');
return k;
}
int main(){
int numV,ds,k;
string src,a;    
cin >> numV >>  ds >> src;
Graph gr(numV,a);
switch(ds){
case 1:
    for (int i=0;i<numV;i++)
        {string s;
         getline(cin,s);
         int j=0,k=0;
         istringstream is(s);
         while (getline(is,s,',')){
             k=toInt(s);
             if (k>0)
                 gr.addEdge(i,j);
             j++;
         }
    }
    break;
default:
    {string s;
    while (getline(cin,s)){
          replaceAll(s,"->",",");
          istringstream is(s);
          k=-1;
          while (getline(is,s,','))
                {if (k==-1)
                        k=toInt(s);
                 else gr.addEdge(k-1,toInt(s)-1);
                 }
            }
    break;}
}
    //gr.pr();
    cout << (gr.isCyclic()?"The graph has cycles":"Graph has no cycles") << endl;
    vector<int> *x = new vector<int>();
    gr.traverseDFS(gr.in(src),x);
    gr.pr(*x);
}