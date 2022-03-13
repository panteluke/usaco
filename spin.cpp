/*
 ID: v...
 PROG: spin
 LANG: C++11
 */
#include <string>
#include <iostream>
#include <fstream>

using namespace std;

struct Wheel
{
    int v;
    int w;
    int start_angle[5];
    int width[5];
};

int main()
{
    ofstream fout ("spin.out");
    ifstream fin ("spin.in");


    Wheel wheels[5];

    for (int i = 0; i < 5; ++i) {
        fin >> wheels[i].v;
        fin >> wheels[i].w;
        for (int j = 0; j < wheels[i].w; ++j) {
            fin >> wheels[i].start_angle[j];
            fin >> wheels[i].width[j];
        }
    }



    int t = 0;
    int degrees_filled[360];

    for (int p = 0; p < 360; ++p) {
        fill_n(degrees_filled, 360, 0);
        for (int i = 0; i < 5; ++i) {
            int startOffset = t * wheels[i].v;
            for (int j = 0; j < wheels[i].w; ++j) {
                for (int k = startOffset + wheels[i].start_angle[j]; k <= startOffset + wheels[i].start_angle[j] + wheels[i].width[j]; ++k) {
                    if (++degrees_filled[k % 360] == 5) {
                        fout << t << endl;
                        return 0;
                    }
                }
            }
        }
        t++;
    }

    fout << "none" << endl;
    return 0;
}