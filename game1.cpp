/*
ID: ...
PROG: game1
LANG: C++11
*/
#include <iostream>
#include <fstream>
#include <vector>
#include <numeric>

using namespace std;

int compute(const vector<int>& nums, int a, int b, vector<vector<int>>& maxrange) {
    if (a>b) {
        return 0;
    }
    
    if (maxrange[a][b] == 0) {
        int pick_left = nums[a] +min(compute(nums, a + 2, b, maxrange), compute(nums, a + 1, b-1, maxrange));
        
        int pick_right = nums[b] + min(compute(nums,a,b-2,maxrange), compute(nums, a+1,b-1,maxrange));
        
        maxrange[a][b] = max(pick_left, pick_right);
    }
    
    return maxrange[a][b];
}

int main() {
    ofstream fout ("game1.out");
    ifstream fin ("game1.in");
    int N;
    fin >> N;
    std::vector<int> numbers;
    for (int i = 0; i < N; ++i) {
        int tmp;
        fin >> tmp;
        numbers.push_back(tmp);
    }

    int total = std::accumulate(numbers.begin(), numbers.end(), 0);
    vector<vector<int>> maxrange(numbers.size(), vector<int>(numbers.size(), 0));
    int player1max = compute(numbers, 0, numbers.size() - 1, maxrange);

    fout << player1max << " " << total - player1max << endl;
    return 0;
}
