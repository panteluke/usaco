/*
ID: psarog1
PROG: kimbits
LANG: C++11
*/
#include <fstream>
#include <sstream>
#include <string>
#include <algorithm>
#include <vector>
using namespace std;

long long countW(unsigned n, unsigned k)
{
    if (k > n)
        return 0;
    if (k * 2 > n)
        k = n - k;
    if (k == 0)
        return 1;

    long long result = n;
    for (int i = 2; i <= k; ++i)
    {
        result *= (n - i + 1);
        result /= i;
    }
    return result;
}
int main()
{
    ofstream fout("kimbits.out");
    ifstream fin("kimbits.in");

    int N, L;
    long long I;
    fin >> N >> L >> I;
    string result;
    int avail_bits = L;
    for (int i = 1; i <= N; ++i)
    {
        if (avail_bits == 0)
        {

            result.push_back('0');
            continue;
        }

        bool use_zero = true;

        long long ways = 0;
        for (int j = 0; j <= min(avail_bits, N - i); ++j)
        {
            int pos = N - i;
            ways = ways + countW(pos, j);
        }
        //printf("Found %d ways for %d bits while at %d\n", ways, avail_bits, i);
        if (ways < I)
        {
            use_zero = false;
            avail_bits--;
            I = I - ways;
        }

        if (use_zero)
        {
            result.push_back('0');
        }
        else
        {
            result.push_back('1');
        }
    }

    fout << result << endl;
    return 0;
}
