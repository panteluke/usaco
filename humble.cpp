/*
ID: ...
PROG: humble
LANG: C++11
*/
#include <fstream>
#include <set>
#include <algorithm>
#include <climits>

using namespace std;

int main() {
    ofstream fout ("humble.out");
    ifstream fin ("humble.in");

    int K, N;

    fin >> K >> N;
    std::set<int> primes;
    for (int i = 0; i < K; ++i) {
        int x;
        fin >> x;
        if (x != 1) {
            primes.insert(x);
        }
    }

    std::set<int> products(primes.begin(), primes.end());

    int counter = 0;
    int result = 0;
    while (true) {
        auto it = products.begin();
        result = *it;
        counter++;

        if (counter == N) break;

        products.erase(it);
        for (auto prime_it = primes.begin(); prime_it != primes.end(); ++prime_it) {
            if (INT_MAX / result >= *prime_it) {
                int to_add = (*prime_it) * result;
                if ( products.size() < N) {
                    products.insert(to_add);
                } else {
                    int max_ele = *prev(products.end());
                    if (to_add < max_ele) {
                        products.insert(to_add);
                        products.erase(prev(products.end()));
                    }
                }
            }
        }
    }

    fout << result << endl;

    return 0;
}
