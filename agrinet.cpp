/*
ID: ...
PROG: agrinet
LANG: C++11
*/
#include <iostream>
#include <fstream>
#include <map>
#include <vector>
#include <string>
#include <climits>
#include <cmath>
#include <set>

using namespace std;

template <class T>
class IndexMinPQ {
private:
    int maxN;
    int n;
    vector<int> pq;
    vector<int> qp;
    vector<T> keys;

public:
    IndexMinPQ(int maxN) : maxN(maxN), n(0), pq(maxN + 1), qp(maxN+1), keys(maxN + 1) {
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    bool isEmpty() {
        return n == 0;
    }

    bool contains(int i) {
        return qp[i] != -1;
    }

    int size() {
        return n;
    }

    void insert(int i, T key) {
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    int minIndex() {
        return pq[1];
    }

    T minKey() {
        return keys[pq[1]];
    }

    int delMin() {
        int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1;        // delete

        pq[n+1] = -1;        // not needed
        return min;
    }

    T keyOf(int i) {
        return keys[i];
    }


    void changeKey(int i, T key) {
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }


    void decreaseKey(int i, T key) {
        keys[i] = key;
        swim(qp[i]);
    }

    void increaseKey(int i, T key) {
        keys[i] = key;
        sink(qp[i]);
    }

    void deleteIndex(int i) {
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        qp[i] = -1;
    }

private:
    bool greater(int i, int j) {
        return keys[pq[i]] > keys[pq[j]];
    }

    void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }



};

class Edge {
private:
    int v;
    int w;
    double weight;

public:
    Edge(): v(-1), w(-1), weight(-1) {}
    Edge(int v, int w, double weight) : v(v), w(w), weight(weight) {  }

    double get_weight() {
        return weight;
    }

    int either() {
        return v;
    }

    int other(int vertex) {
        if      (vertex == v) return w;
        else  return v;

    }

    int compareTo(Edge that) {
        return weight < that.weight;
    }

};


class EdgeWeightedGraph {
private:
    int V;
    int E;
    std::vector<std::vector<Edge>> adj;

public:
    EdgeWeightedGraph(int V) : V(V), E(0), adj(V) { }

    int getV() {
        return V;
    }

    int getE() {
        return E;
    }

    void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].push_back(e);
        adj[w].push_back(e);
        E++;
    }

    std::vector<Edge> getAdj(int v) {
        return adj[v];
    }

    int degree(int v) {
        return adj[v].size();
    }

    std::vector<Edge> edges() {
        vector<Edge> list;
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj[v]) {
                if (e.other(v) > v) {
                    list.push_back(e);
                } else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.push_back(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }
};

class PrimMST {
private:
    std::vector<Edge> edgeTo;        // edgeTo[v] = shortest edge from tree vertex to non-tree vertex
    std::vector<double> distTo;      // distTo[v] = weight of shortest such edge
    std::vector<bool> marked;     // marked[v] = true if v on tree, false otherwise
    IndexMinPQ<double> pq;

public:
    PrimMST(EdgeWeightedGraph G) : edgeTo(G.getV()), distTo(G.getV()), marked(G.getV()), pq(G.getV()) {
        for (int v = 0; v < G.getV(); v++)
            distTo[v] = INT_MAX;

        for (int v = 0; v < G.getV(); v++)      // run from each vertex to find
            if (!marked[v]) prim(G, v);      // minimum spanning forest

    }

    // run Prim's algorithm in graph G, starting from vertex s
private:
    void prim(EdgeWeightedGraph G, int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            scan(G, v);
        }
    }

    // scan vertex v
    void scan(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.getAdj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;         // v-w is obsolete edge
            if (e.get_weight() < distTo[w]) {
                distTo[w] = e.get_weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }
    }

public:
    std::vector<Edge> edges() {
        vector<Edge> mst;;
        for (int v = 0; v < edgeTo.size(); v++) {
            Edge e = edgeTo[v];
            if (e.either() != -1) {
                mst.push_back(e);
            }
        }
        return mst;
    }

    double get_weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.get_weight();
        return weight;
    }
};


int main() {
    ofstream fout ("agrinet.out");
    ifstream fin ("agrinet.in");

    int N;

    fin >> N;


    EdgeWeightedGraph g(N);
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            int weight;
            fin >> weight;

            if (weight > 0) {
                Edge e(i, j, weight);
                g.addEdge(e);
            }
        }
    }

    PrimMST prim(g);



    fout << (int)prim.get_weight() << endl;

    return 0;
}

