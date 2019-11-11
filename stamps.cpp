/*
ID: ...
PROG: stamps
LANG: C++11
*/
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ofstream fout ("stamps.out");
    ifstream fin ("stamps.in");

    int K, N;
    std::vector<int> stamps;

    fin >> K >> N;
    for (int i  = 0; i < N; ++i) {
        int value;
        fin >> value;
        stamps.push_back(value);
    }

    std::sort(stamps.begin(), stamps.end());

    int t[2000001] = {0};

    for (size_t i = 0; i < stamps.size(); ++i) {
        t[stamps[i]] = 1;
    }
    int counter = 0;
    while (true) {
        counter++;
        if (t[counter] == 0) break;

        if (t[counter] < K) {
            for (size_t i = 0; i < stamps.size(); ++i) {
                int new_index = counter + stamps[i];
                if (t[new_index] == 0) {
                    t[new_index] = t[counter] + 1;
                } else if (t[new_index] > t[counter] + 1) {
                    t[new_index] = t[counter] + 1;
                }
            }
        }
    }

    fout << counter - 1 << endl;

    return 0;
}
