/*
ID: ...
PROG: contact
LANG: C++11
*/
#include <iostream>
#include <fstream>
#include <map>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;


struct Sequence {
    int n;
    string s;
    bool ready;
    Sequence(int s) : n(s), ready(false) {}
    void rotate(char l) {
        if (ready) {
            s = s.substr(1, s.size() - 1) + l;
        } else {
            s.push_back(l);
            ready = s.size() == n;
        }
    }
};

static bool compareSeqString(string a, string b)
{
    if (a.size() == b.size()) {
        return a < b;
    } else {
        return a.size() < b.size();
    }
}

int main() {
    ofstream fout ("contact.out");
    ifstream fin ("contact.in");

    int A, B, N;

    fin >> A >> B >> N;

    string s;
    std::vector<Sequence> sequences;
    for (int i = A; i <= B; ++i) {
        sequences.push_back({i});
    }
    std::map<string, int> t1;
    while(fin >> s) {
        for (size_t i = 0; i < s.size(); ++i) {
            for (size_t j = 0; j < sequences.size(); ++j) {
                sequences[j].rotate(s[i]);
                if (sequences[j].ready) {
                    t1[sequences[j].s]++;
                }
            }
        }
    }

    std::map<int, vector<string>> t2;
    for (auto& it : t1) {
        t2[it.second].push_back(it.first);
    }

    for (auto& it : t2) {
        std::sort(it.second.begin(), it.second.end(), compareSeqString);
    }

    int counter = 0;
    for (auto it = t2.rbegin(); it != t2.rend(); ++it) {
        counter++;

        fout << it->first << endl;

        for (size_t i = 0; i < it->second.size(); ++i) {
            fout << it->second[i];

            if ((i + 1) % 6 == 0) {
                fout << endl;
                continue;
            }
            if (i < it->second.size() - 1) {
                fout << " ";
            } else {
                fout << endl;
            }
        }

        if (counter == N) break;
    }


    return 0;
}

