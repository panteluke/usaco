/*
ID: panteli1
PROG: combo
LANG: C++11
*/
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main() {
    ofstream fout ("combo.out");
    ifstream fin ("combo.in");
    int N;
    int a[3];
    int b[3];
    fin >> N;
    fin >> a[0] >> a[1] >> a[2];
    fin >> b[0] >> b[1] >> b[2];

    int common[3];
    for (int i = 0; i < 3; i++) {
        common[i] = 5 - max(a[i], b[i]) + min(a[i], b[i]);
        if ((common[i]) < 0 || common[i] > 5) {
            common[i] = 5 - min(a[i] + N, b[i]+N) + max(a[i], b[i]);;
            if (common[i] < 0 || common[i] > 5) {
                common[i] = 0;
            }
        }
        common[i] = min(common[i], N);
    }

    int k = min(5, N);
    int result = 2 * k * k * k - common[0]*common[1]*common[2];
    fout << result << endl;
    return 0;
}