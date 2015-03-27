#include <iostream>
#include <vector>
#include <queue>
using namespace std;
    class AdjacencyMatrix
    {
        private:
            int n;
            int **adj;
       //Helper,Prints vector in expected format       
       void pr(vector<int> a){
                for (int i=0,size=a.size();i<size;i++)
                    cout << a[i] << (i==size-1?'\n':',');
                }
       //Helper,searchs a vector
       int indexOf(vector<int> a,int k){
                for (int i=0,size=a.size();i<size;i++)
                    if (a[i]==k)
                        return i;
                return -1;    
            }
        
        public:
            AdjacencyMatrix(int n)
            {
                this->n = n;
                adj = new int* [n];
                for (int i = 0; i < n; i++)
                {
                    adj[i] = new int [n];
                    for(int j = 0; j < n; j++)
                        adj[i][j] = 0;
                }
            }
        //Adds edge from orig to destin
        void add_edge(int origin, int destin)
            {
                if( origin > n || destin > n || origin < 0 || destin < 0)
                    cout<<"Invalid edge!\n";
                else
                    adj[origin - 1][destin - 1] = 1;
            }            
         //Traverses graph in BFS,creating two list of visited nodes and distance from given node.
         void traverseBFS(int startNode){
                    queue<int> curNodes;
                    vector<int> visitedNodes,dist;
                    curNodes.push(startNode);
                    int tmp;
                    visitedNodes.push_back(startNode);
                    dist.push_back(0);
                    while (!curNodes.empty()){
                            tmp = curNodes.front();
                            curNodes.pop();
                            for (int i=1;i<=n;i++)
                            if (adj[tmp-1][i-1]==1 && indexOf(visitedNodes,i)==-1){
                                    visitedNodes.push_back(i);
                                    curNodes.push(i);
                                    dist.push_back(dist[indexOf(visitedNodes,tmp)]+1);
                            }
                    }
                    pr(dist);
                    pr(visitedNodes);                  
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
gr.traverseBFS(start);
}
