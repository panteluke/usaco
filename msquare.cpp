/*
ID: ..
TASK: msquare
LANG: C++
*/
/* LANG can be C++11 or C++14 for those more recent releases */
#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
#include <set>
#include <queue>
#include <sstream>

using namespace std;
struct Helper {
    std::string path;
    std::string state;
};


std::string trA(std::string str) {
    auto sh = str.substr(4, 4);
    auto fh = str.substr(0, 4);
    std::reverse(sh.begin(), sh.end());
    std::reverse(fh.begin(), fh.end());
    return sh + fh;
}

std::string trB(std::string str) {
    return str[3] + str.substr(0,3) +  str.substr(5,3) + str[4];
}

std::string trC(std::string str) {
    std::stringstream ss;
    ss << str[0] << str[6] << str[1] << str[3] << str[4] << str[2] << str[5] << str[7];
    return ss.str();
}

int main()
{
    ofstream fout("msquare.out");
    ifstream fin("msquare.in");
    std::string start = "12345678";
    std::string tmp;
    std::string target;
    for (int i = 1; i <= 8; ++i) {
        fin >> tmp;
        target += tmp;
    }
    
    if (start == target) {
        fout << 0 << endl;
        fout << endl;
        return 0;
    }

    std::set<std::string> seen;
    seen.insert(start);

    std::queue<Helper> q;
    Helper h = {"", start};
    q.push(h);
    std::string solution;
    while (!q.empty()) {
        Helper g = q.front(); q.pop();
        
        std::string a = trA(g.state);
        if (a == target) {
            solution = g.path + "A";
            break;
        } else {
            if (seen.find(a) == seen.end()) {
                seen.insert(a);
                q.push({g.path + "A", a});
            }
        }
        std::string b = trB(g.state);
        if (b == target)
        {
            solution = g.path + "B";
            break;
        }
        else
        {
            if (seen.find(b) == seen.end())
            {
                seen.insert(b);
                q.push({g.path + "B", b});
            }
        }
        std::string c = trC(g.state);
        if (c == target)
        {
            solution = g.path + "C";
            break;
        }
        else
        {
            if (seen.find(c) == seen.end())
            {
                seen.insert(c);
                q.push({g.path + "C", c});
            }
        }
    }

    fout << solution.size() << endl;
    for (size_t i = 0; i < solution.size(); ++i) {
        fout << solution[i];
        if (i + 1 % 60 == 0) fout << endl;
    }
    if (solution.size() % 60 != 0) fout << endl;
    
    
    

    return 0;
}
