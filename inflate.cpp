/*
ID: ...
PROG: inflate
LANG: C++11
*/
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ofstream fout ("inflate.out");
    ifstream fin ("inflate.in");

    int minutes, classes;

    fin >> minutes >> classes;

    int points_per_minute[10001] = {0};

    for (int i = 0; i < classes; ++i) {
        int class_points, class_minutes;
        fin >> class_points >> class_minutes;
        for (int j = class_minutes; j <= minutes; ++j) {
            points_per_minute[j] = std::max(points_per_minute[j], points_per_minute[j - class_minutes] + class_points);
        }
        
    }

    fout << points_per_minute[minutes] << endl;

    return 0;
}
