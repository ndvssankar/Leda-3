#include <iostream>
#include <vector>
using namespace std;
class AdjacencyMatrix
    {
        private:
            int n;
            int **adj;
            bool* visited;
        public:
            AdjacencyMatrix(int n){
                this->n = n;
                adj = new int* [n];
                visited = new bool[n];
                for (int i = 0; i < n; i++){
                    adj[i] = new int [n];
                    visited[i]=false;
                    for(int j = 0; j < n; j++)
                        adj[i][j] = 0;
                }
            }
            
            void add_edge(int origin, int destin){
                if( origin > n || destin > n || origin < 0 || destin < 0)                
                    cout<<"Invalid edge!\n";                
                else                
                    adj[origin - 1][destin - 1] = 1;      
            }        
            //Prints vector in expected format
            void pr(vector<int> a){
                for (int i=0,size=a.size();i<size;i++)
                    cout << a[i] << (i==size-1?'\n':',');
                }
            //Traverses graph in DFS recursively,puts nodes in given vector
            void traverseDFS(int startNode,vector<int> *a){
                visited[startNode-1]=true;
                a->push_back(startNode);
                 for (int i=0;i<n;i++)
                        if (adj[startNode-1][i]==1 && !visited[i])
                            traverseDFS(i+1,a);
            }
    };
int main(){
int numV,start,k;
cin >> numV >> start;
AdjacencyMatrix gr(numV);
for (int i=1;i<=numV;i++)
    for (int j=1;j<=numV;j++)
        {cin >> k;
         if (k==1)
             gr.add_edge(i,j);}
vector<int> *x = new vector<int>();    
gr.traverseDFS(start,x);
gr.pr(*x);
}
