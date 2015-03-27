#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <stdio.h>
#include <limits.h>
using namespace std;
class Graph
    {
        private:
            int n;
            int **adj;
            bool* visited;
            string *name;
        int in(string s){
            for (int i = 0; i < n; i++)
                if(s.compare(name[i])==0)
                    return i;
            return -1; 
        }
        public:
            Graph(int n,string s){
                this->n = n;
                adj = new int* [n];
                visited = new bool[n];
                name = new string[n];
                istringstream is(s);
                int i = 0;
                while (getline(is,s,',')){
                    name[i]=s;
                    i++;
                }
                for (i = 0; i < n; i++){
                    adj[i] = new int [n];
                    visited[i]=false;
                    for(int j = 0; j < n; j++)
                        adj[i][j] = 0;
                }
            }
            void addEdge(int origin, int destin,int k){
                //cout <<"Add "<< origin << ":"<< destin << ":" << k << "\n";
                if( origin > n || destin > n || origin < 0 || destin < 0)
                    cout<<"Invalid edge!\n";
                else
                    adj[origin-1][destin] = k;
            }
            //Prints vector in expected format
            void pr(vector<string> a){
                for (int i=0,size=a.size();i<size;i++)
                    cout << a[i] << "->";
                }
    
            
            void pr(){
                cout << endl;
                for (int i=0;i<n;i++)
                    cout << name[i]<< (i==n-1?'\n':' ');
                for (int i=0;i<n;i++)
                    for (int j=0;j<n;j++)
                        cout << adj[i][j] << (j==n-1?'\n':' ');
                }   
int minDistance(int dist[], bool sptSet[])
{
   int min = INT_MAX, min_index;
   for (int v = 0; v < n; v++)
     if (sptSet[v] == false && dist[v] <= min)
         min = dist[v], min_index = v;
   return min_index;
}
 
void printSolution(int dist[],vector<string> p[],string s)
{
   for (int i = 0; i < n; i++)
   {   if(!p[i].empty())
            for (string a:p[i])
                    cout << a << "->";
       else if (s.compare(name[i])!=0)
            cout << s << "->";   
    cout << name[i] << ":"<< dist[i]<< "\n";   
   }
}
void dijkstra(string s)
{
     int src = in(s);
     int dist[n]; 
     vector<string> p[n];
     bool sptSet[n];
     for (int i = 0; i < n; i++)
        dist[i] = INT_MAX, sptSet[i] = false;
     dist[src] = 0;
     for (int count = 0; count < n-1; count++)
     {
       int u = minDistance(dist, sptSet);
       sptSet[u] = true;
       for (int v = 0; v < n; v++)
         if (!sptSet[v] && adj[u][v] > 0 && dist[u] != INT_MAX && dist[u]+adj[u][v] < dist[v])
             {dist[v] = dist[u] + adj[u][v];
              p[v].clear();
              for (string z:p[u])
                  p[v].push_back(z);
              p[v].push_back(name[u]);
               }
     }
     printSolution(dist,p,s);
}    
    };
int main(){
int numV,k,i=0;
string src,s;
cin >> numV >> src >> s;
istringstream is(s.substr(1,s.length()-1));
Graph gr(numV,s.substr(1,s.length()-2));
while (i<=numV)
        {string s;
         getline(cin,s);
         int j=0;
         istringstream is(s);
         while (getline(is,s,',')){
             gr.addEdge(i,j,stoi(s));
             j++;
         }
         i++;
    }
    //gr.pr();
    gr.dijkstra(src);
}
