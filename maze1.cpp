/*
ID: ...
PROG: maze1
LANG: C++11
*/
#include <fstream>
#include <sstream>
#include <string>
#include <vector>
#include <queue>

using namespace std;

class Graph {
    private:
        int V;
        int E;
        vector<vector<int>> adj;
    
    public:    
        Graph(int v) : V(v), E(0), adj(v) { }

        int getV() { return V; }
        int getE() { return E; }

        void addEdge(int v, int w) {
            E++;
            adj[v].push_back(w);
            adj[w].push_back(v);
        }   

        vector<int> getAdj(int v) {
            return adj[v];
        }

        int degree(int v) {
            return adj[v].size();
        }
};

class BreadthFirstPaths {
    private:
        vector<bool> marked;   // marked[v] = is there an s-v path
        vector<int> edgeTo;    // edgeTo[v] = previous edge on shortest s-v path
        vector<int> distTo;    // distTo[v] = number of edges shortest s-v path

    public:
        BreadthFirstPaths(Graph G, int s) : marked(G.getV()), distTo(G.getV()), edgeTo(G.getV()) {
            bfs(G, s);
        }


    // breadth-first search from a single source
    private:
        void bfs(Graph G, int s) {
            std::queue<int> q;
            
            for (int v = 0; v < G.getV(); v++)
                distTo[v] = -1;
            distTo[s] = 0;
            marked[s] = true;
            q.push(s);

            while (!q.empty()) {
                int v = q.front(); q.pop();
                for (int w : G.getAdj(v)) {
                    if (!marked[w]) {
                        edgeTo[w] = v;
                        distTo[w] = distTo[v] + 1;
                        marked[w] = true;
                        q.push(w);
                    }
                }
            }
        }

    public:
        bool hasPathTo(int v) {
            return marked[v];
        }

        int distance(int v) {
            return distTo[v];
        }
};

int main() {
    ofstream fout ("maze1.out");
    ifstream fin ("maze1.in");
    std::string s;

    std::getline(fin, s);
    stringstream ss;
    ss << s;

    int w, h;
    ss >> w >> h;
    
    int maze[201][77];
    for (int i = 0; i < 201; ++i) {
        for (int j = 0; j < 77; ++j) {
            maze[i][j] = -1;
        }
    }

    for (int i = 0; i < 2*h + 1; ++i) {
        std::getline(fin, s);
        
        for (size_t index = 0; index < s.size(); ++index) {
            if ((s[index] == '-') || (s[index] == '|' || (s[index] == '+'))) {
                maze[i][index] = -2;
            } 
        }
    }

    int exit1 = -1;
    int exit2 = -1;
    for (int i = 0; i < w; ++i) {
        if (maze[0][2*i + 1] == -1) {
            if (exit1 == -1) {
                exit1 = i;
            } else {
                exit2 = i;
            }
        }

        if (maze[2*h][2*i + 1] == -1) {
            if (exit1 == -1) {
                exit1 = w * (h - 1) + i;
            } else {
                exit2 = w * (h - 1) + i;
            }
        } 
    }

    for (int i = 0; i < h; ++i) {
        if (maze[2*i + 1][0] == -1) {
            if (exit1 == -1) {
                exit1 = i * w;
            } else {
                exit2 = i * w;
            }
        }

        if (maze[2*i + 1][2*w] == -1) {
            if (exit1 == -1) {
                exit1 = (i+1)*w-1;
            } else {
                exit2 = (i+1)*w-1;
            }
        } 
    }

    Graph g(w*h);
    for (int i = 0; i < h; ++i) {
        for (int j = 0; j < w; ++j) {
            int v = i * w + j;
            if ((maze[2 * i + 1][2 * j + 2] == -1) && (j != w - 1)) {
                g.addEdge(v, v + 1);
            }
            if ((maze[2*i + 2][2 * j  + 1] == -1) && (i != h - 1)) {
                g.addEdge(v, v + w);
            }
        }
    }

    BreadthFirstPaths paths1(g, exit1);
    BreadthFirstPaths paths2(g, exit2);

    int max_dist = 0;
    for (int i = 0;i < h; ++i) {
        for (int j = 0; j < w; ++j) {
            int dist1 = paths1.distance(i * w + j);
            int dist2 = paths2.distance(i * w + j);
            int min_dist = min(dist1, dist2);
            if (min_dist > max_dist) max_dist = min_dist;
        }
    }
    fout << (max_dist + 1) << endl;
    return 0;
}
