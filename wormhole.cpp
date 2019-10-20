/*
ID: ...
TASK: wormhole
LANG: C++
*/
/* LANG can be C++11 or C++14 for those more recent releases */
#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <climits>

using namespace std;


bool isLoop(vector<int> pairs, int right[12])
{
    bool result = false;
    int loops[12] = {0};
    for (size_t i = 0; i < pairs.size(); ++i) {
        if (i % 2 == 0) {
            loops[pairs[i]] = pairs[i + 1];
        }     else {
            loops[pairs[i]] = pairs[i - 1];
        }
    }

    for (int i = 0; i < pairs.size() && !result; i++) {
        bool passed[12][2] = {false};
        passed[i][1] = true;
        int current = i;
        while (right[current] != -1) {
            current = right[current];
            if (passed[current][0]) {
                result = true;
                break;
            }
            passed[current][0] = true;

            current = loops[current];
            if (passed[current][1]) {
                result = true;
                break;
            }
            passed[current][1] = true;
        }

    }

    return result;
}


void buildCases(vector<int> numbers, vector<int> sol, vector<vector<int>> &cases)
{
    if (numbers.size() == 2) {
        sol.push_back(numbers[0]);
        sol.push_back(numbers[1]);
        cases.push_back(sol);
    } else {



        for (size_t i = 1; i < numbers.size(); ++i) {
            vector<int> tmp = sol;
            tmp.push_back(numbers[0]);
            tmp.push_back(numbers[i]);
            vector<int> new_numbers = numbers;
            new_numbers.erase(std::remove(new_numbers.begin(), new_numbers.end(), numbers[0]));
            new_numbers.erase(std::remove(new_numbers.begin(), new_numbers.end(), numbers[i]));

            buildCases(new_numbers, tmp, cases);
        }
    }



}

int main() {
    ofstream fout ("wormhole.out");
    ifstream fin ("wormhole.in");
    int n;
    fin >> n;
    int coords[12][2];
    for (int i = 0; i < n; ++i) {
        fin >>  coords[i][0]  >> coords[i][1];
    }

    int right[12];
    for (int i = 0; i < 12; ++i) right[i] = -1;
    for (int i = 0; i < n; ++i) {
        int right_x = INT_MAX;
        for (int j = 0; j < n; ++j) {

            if (coords[i][1] == coords[j][1] && coords[j][0] > coords[i][0] && coords[j][0] < right_x) {
                right_x = coords[j][0];
                right[i] = j;
            }
        }
    }

    int counter = 0;
    std::vector<int> numbers;
    for (int i = 0; i < n; ++i) {
        numbers.push_back(i);
    }

    std::vector<vector<int>> cases;
    std::vector<int> empty;
    buildCases(numbers, empty, cases);

    for (size_t i = 0; i < cases.size(); ++i) {
        if (isLoop(cases[i], right)) {
            counter++;
        }
    }

    fout << counter << endl;
    return 0;
}
