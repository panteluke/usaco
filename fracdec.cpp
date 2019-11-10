/*
ID: ...
PROG: fracdec
LANG: C++11
*/
#include <iostream>
#include <fstream>
#include <sstream>
#include <map>
#include <vector>
#include <string>

using namespace std;


int main() {
    ofstream fout ("fracdec.out");
    ifstream fin ("fracdec.in");

    int N, D;

    fin >> N >> D;

    stringstream ss;

    std::map<int, size_t> dist;
    std::vector<int> digits;
    size_t start_cycle;
    bool has_cycle = false;
    int div = N / D;
    int mod = N % D;
    if (mod == 0) {
        ss << div << ".0";
    } else {
        ss << div << ".";
        dist[mod] = 0;

        bool finished = false;
        while (!finished) {
            N = mod * 10;
            mod = N % D;
            div = N / D;
            digits.push_back(div);

            if (dist.find(mod) != dist.end()) {
                finished = true;
                start_cycle = dist[mod];
                has_cycle = true;
                continue;
            }

            dist[mod] = digits.size();

            if (mod == 0) {
                finished = true;
            }
        }

        for (size_t i = 0; i < digits.size(); ++i) {
            if (has_cycle && i == start_cycle) {
                ss << "(";
            }
            ss << digits[i];
        }
        if (has_cycle) ss << ")";
    }

    string result = ss.str();
    while (result.size() > 76) {
        fout << result.substr(0, 76) << endl;
        result = result.substr(76, result.size() - 76);
    }
    if (!result.empty()) {
        fout << result << endl;
    }

    return 0;
}
