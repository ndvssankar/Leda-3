#include <iostream>
#include <cstdlib>
#include <sstream>
#include <string>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
    class AdjacencyMatrix
    {
        private:
            int n;
            int **adj;
            bool* visited;
        public:
            AdjacencyMatrix(int n)
            {
                this->n = n;
                adj = new int* [n];
                visited = new bool[n];
                for (int i = 0; i < n; i++)
                {
                    adj[i] = new int [n];
                    visited[i]=false;
                    for(int j = 0; j < n; j++)
                    {
                        adj[i][j] = 0;
                    }
                }
            }
            void add_edge(int origin, int destin)
            {
                if( origin > n || destin > n || origin < 0 || destin < 0)
                {
                    cout<<"Invalid edge!\n";
                }
                else
                {
                    adj[origin - 1][destin - 1] = 1;
                }
            }
            void traverseDFS(int startNode){
                visited[startNode-1]=true;
                cout << startNode << " ";
                 for (int i=0;i<n;i++)
                        if (adj[startNode-1][i]==1 && !visited[i])
                            traverseDFS(i+1);
            }
    };
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
cin >> numV >> ds;
AdjacencyMatrix gr(numV);
switch(ds){
case 0:
    {string s;
    while (getline(cin,s)){
          replaceAll(s,"->",",");
          istringstream is(s);
          k=-1;
          while (getline(is,s,','))
                { if (k==-1)
                        k=toInt(s);
                 else gr.add_edge(k,toInt(s));
                 }
            }
    break;}
case 1:
    for (int i=1;i<=numV;i++)
                for (int j=1;j<=numV;j++)
                    {cin >> k;
                    if (k==1)
                        gr.add_edge(i,j);}
    break;
case 2://adj array
    break;
}
gr.traverseDFS(1);
}