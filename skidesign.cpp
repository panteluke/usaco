/*
ID: ...
PROG: skidesign
LANG: C++11
*/
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int calculateCost(int *distributed_heights, int first_height, int last_height, int starting_range, int ending_range)
{
    int result = 0;

    for (int i = first_height; i <= last_height; ++i) {
        if (distributed_heights[i] > 0) {
            if (i < starting_range) {
                result = result + distributed_heights[i] * (starting_range-i)*(starting_range-i);
            } else if (i > ending_range) {
                result = result + distributed_heights[i] * (i - ending_range)*(i - ending_range);
            }
        }
    }

    return result;
}

int main() {
    ofstream fout ("skidesign.out");
    ifstream fin ("skidesign.in");
    int N;
    
    int distributed_heights[101] = {0};
    
    fin >> N;
    for (int i =0; i < N; ++i) {
        int height;
        fin >> height;
        distributed_heights[height]++;
    }

    int result = 0;

    int first_height = -1;
    int last_height = -1;
    for (int i = 0; i < 101; ++i) {
        if (distributed_heights[i] > 0) {
            first_height = i;
            break;
        }
    }

    for (int i = 100; i > -1; --i) {
        if (distributed_heights[i] > 0) {
            last_height = i;
            break;
        }
    }

    bool has_min = false;
    if (last_height - first_height > 17) {
        for (int i = first_height; i <= last_height - 17; ++i) {
            int starting_range = i;
            int ending_range = i + 17;
            int cost = calculateCost(distributed_heights, first_height, last_height, starting_range, ending_range);
            
            if (!has_min || cost < result) {
                result = cost;
                has_min = true;
            } 
        }
        
    }
    
    fout << result << endl;
    return 0;
}
