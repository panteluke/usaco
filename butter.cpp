/*
ID: ...
PROG: butter
LANG: C++11
*/
#include <vector>
#include <climits>
#include <fstream>
#include <iostream>


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
    int weight;

public:
    Edge(): v(-1), w(-1), weight(-1) {}
    Edge(int v, int w, int weight) : v(v), w(w), weight(weight) {  }

    int get_weight() {
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

class DijkstraUndirectedSP {
private:
    std::vector<int> distTo;
    std::vector<Edge> edgeTo;
    IndexMinPQ<int> pq;    // priority queue of vertices

public:
    DijkstraUndirectedSP(EdgeWeightedGraph G, int s) : distTo(G.getV()), edgeTo(G.getV()), pq(G.getV()) {
        for (int v = 0; v < G.getV(); v++)
            distTo[v] = INT_MAX;
        distTo[s] = 0;

        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : G.getAdj(v))
                relax(e, v);
        }

    }

private:
    void relax(Edge e, int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.get_weight()) {
            distTo[w] = distTo[v] + e.get_weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

public:
    int distanceTo(int v) {
        return distTo[v];
    }

    bool hasPathTo(int v) {
        return distTo[v] < INT_MAX;
    }

    vector<Edge> pathTo(int v) {
        std::vector<Edge> result;
        if (!hasPathTo(v)) return result;

        int x = v;
        for (Edge e = edgeTo[v]; e.either()  != -1; e = edgeTo[x]) {
            result.push_back(e);
            x = e.other(x);
        }
        return result;
    }
};


int main() {
    ofstream fout ("butter.out");
    ifstream fin ("butter.in");

    int N;
    int P;
    int C;
    fin >> N >> P >> C;

    vector<int> positions;

    for (int i = 1; i <= N; ++i) {
        int tmp;
        fin >> tmp;
        positions.push_back(tmp - 1);
    }

    EdgeWeightedGraph g(P);

    for (int i = 1; i <= C; ++i) {
        int P1;
        int P2;
        int weight;
        fin >> P1;
        fin >> P2;
        fin >> weight;

        Edge e(P1 - 1, P2 - 1, weight);

        g.addEdge(e);
    }

    int solution = INT_MAX;

    for (int i = 0; i < P; ++i) {
        DijkstraUndirectedSP sp(g, i);
        int chosen = 0;

        for (auto position : positions) {

            chosen += sp.distanceTo(position);
        }

        if (chosen < solution) solution = chosen;
    }

    fout << solution << endl;

    return 0;
}