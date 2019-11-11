/*
ID: ...
PROG: fact4
LANG: C++11
*/
#include <fstream>
#include <sstream>
#include <string>

using namespace std;


int main() {
    ofstream fout ("fact4.out");
    ifstream fin ("fact4.in");

    int N;

    fin >> N;

    int product = 1;
    for (int i = 1; i <= N; ++i) {
        product = product * i;
        std::stringstream ss;
        ss << product;

        std::string product_str = ss.str();
        std::string to_keep;
        int counter = 0;
        for (auto it = product_str.rbegin(); it != product_str.rend(); ++it) {

            if (*it != '0') {
                counter++;
                to_keep = *it + to_keep;
                if (i == N) {
                    fout << *it << endl;
                    break;
                }
                if (counter == 6) break;
            }
        }

        product = atoi(to_keep.c_str());
    }

    return 0;
}

