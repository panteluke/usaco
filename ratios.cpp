/*
ID: ...
PROG: ratios
LANG: C++11
*/
#include <iostream>
#include <fstream>

using namespace std;

int main() {
    ofstream fout ("ratios.out");
    ifstream fin ("ratios.in");

    int n1, n2, n3;
    int a1, a2, a3;
    int b1, b2, b3;
    int c1, c2, c3;

    fin >> n1 >> n2 >> n3;
    fin >> a1 >> a2 >> a3;
    fin >> b1 >> b2 >> b3;
    fin >> c1 >> c2 >> c3;


    for (int i = 0; i < 100; ++i) {
      for (int j = 0; j < 100; ++j) {
        for (int k = 0; k < 100; ++k) {
          int a = i * a1 + j * b1 + k * c1;
          if (a % n1 == 0) {
            int x = a / n1;
            if ((n2 * x == i * a2 + j * b2 + k * c2) && (n3 * x == i * a3 + j * b3 + k * c3)) {
                if (i != 0 || j != 0 || k != 0) {
                    fout << i << " " << j << " " << k << " " << x << std::endl;
                    return 0;
                }
            }
          }  
        }
      }
    }




    fout << "NONE" << std::endl;

    return 0;
}

