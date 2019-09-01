/*
ID: ..
TASK: gift1
LANG: C++                 
*/
/* LANG can be C++11 or C++14 for those more recent releases */
#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

struct Info {
    string name;
    int money;
};

int main() {
    ofstream fout ("gift1.out");
    ifstream fin ("gift1.in");
    int np;
    fin >> np;
    vector<Info> data;
    for (int i = 0; i < np; i++) {
        string s;
        fin >> s;
        Info info;
        info.money = 0;
        info.name = s;
        data.push_back(info);
    }

    for (int i = 0; i < np; i++) {
        string s;
        fin >> s;
        size_t k = 0;
        for (size_t j = 0; j < data.size(); ++j) {
            if (data[j].name == s) {
                k = j;
                break;
            }
        }

        int total;
        int num_of_persons;
        fin >> total >> num_of_persons;
        data[k].money -= total;
        int give_to_each = 0;
        if (num_of_persons > 0) {
            give_to_each = total / num_of_persons;
            data[k].money += total % num_of_persons;
        } else {
            data[k].money += total;
        }
        for (int j = 0; j < num_of_persons; ++j) {
            string receipient;
            fin >> receipient;
            for (size_t jj = 0; jj < data.size(); ++jj) {
                if (data[jj].name == receipient) {
                    data[jj].money += give_to_each;
                    break;
                }
            }

        }
        
    }

    for (size_t i = 0; i < data.size(); ++i) {
        fout << data[i].name << " " << data[i].money << endl;
    }

    return 0;
}
