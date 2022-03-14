/*
 ID: v...
 PROG: range
 LANG: C++11
 */
#include <string>
#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main()
{
    ofstream fout ("range.out");
    ifstream fin ("range.in");


    int N;
    fin >> N;

    vector<vector<int>> matrix;
    matrix.resize(N);
    for (int i = 0; i < N; ++i) {
        string t;
        fin >> t;

        for (auto c : t) {
            if (c=='0') matrix[i].push_back(0);
            if (c=='1') matrix[i].push_back(1);

        }
    }

    vector<vector<int>> aces;
    aces.resize(matrix.size());
    for (size_t i = 0; i < matrix.size(); ++i) {
        aces[i].resize(matrix[i].size());
    }

    aces[0][0] = matrix[0][0];
    for (size_t i = 1; i < matrix[0].size(); ++i) {
        aces[0][i] = matrix[0][i] + aces[0][i-1];
    }

    for (size_t i = 1; i < matrix.size(); ++i) {
        aces[i][0] = matrix[i][0] + aces[i-1][0];
    }

    for (size_t i = 1; i < matrix.size(); ++i) {
        for (size_t j = 1; j < matrix[0].size(); ++j) {
            aces[i][j] = matrix[i][j] + aces[i-1][j] + aces[i][j-1] - aces[i-1][j-1];
        }
    }

    vector<int> result;
    result.resize(N+1);

    for (size_t i = 0; i < matrix.size(); ++i) {
        for (size_t j = 0; j < matrix[0].size(); ++j) {

            size_t k = i + 1;
            size_t l = j + 1;
            while (k < matrix.size() && l < matrix[0].size()) {
                int h = k - i + 1;
                int w = l - j + 1;

                int a = j > 0 ? aces[k][j-1] : 0;
                int b = i > 0 ? aces[i-1][l] : 0;
                int c = i > 0 && j > 0 ? aces[i-1][j-1] : 0;
                int diff = aces[k][l] - a - b + c;

                if (h * w == diff && h == w) result[w]++;
                else break;
                ++k;
                ++l;
            }
        }
    }

    for (size_t i = 2; i < result.size(); ++i) {
        if (result[i]) fout << i << " " << result[i] << endl;
    }

    return 0;
}