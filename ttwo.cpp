/*
ID: ...
PROG: ttwo
LANG: C++11
*/
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int find_in_trip(int pos[3], int trip[400][3])
{
    int result = -1;

    for (int i = 0; i < 400; ++i) {
        if (trip[i][0] == pos[0] && trip[i][1] == pos[1] && trip[i][2] == pos[2]) {
            result = i;
            break;
        }
    }

    return result;
}

void find_next_pos(int pos[3], int field[12][12])
{

    int row = pos[0];
    int col = pos[1];

    if (pos[2] == 1) {
        if (field[row - 1][col] == 0) {
            pos[2] = 2;
        } else {
            pos[0] = row - 1;
        }
    } else if (pos[2] == 2) {
        if (field[row][col + 1] == 0) {
            pos[2] = 3;
        } else {
            pos[1] = col + 1;
        }
    } else if (pos[2] == 3) {
        if (field[row + 1][col] == 0) {
            pos[2] = 4;
        } else {
            pos[0] = row + 1;
        }
    } else {
        if (field[row][col - 1] == 0) {
            pos[2] = 1;
        } else {
            pos[1] = col - 1;
        }
    }
}

int main() {
    ofstream fout ("ttwo.out");
    ifstream fin ("ttwo.in");

    int field[12][12] = {0};

    int cow_position[3] = {0, 0, 1};
    int farmer_position[3] = {0, 0, 1};


    for (int i = 1; i <= 10; ++i) {
        string s;
        fin >> s;

        for (size_t letter = 0; letter < s.size(); ++letter) {
            if (s[letter] == '.') {
                field[i][(int)letter+1] = 1;
            } else if (s[letter] == 'C') {
                cow_position[0] = i;
                cow_position[1] = (int)letter + 1;
                field[i][(int)letter+1] = 1;
            } else if (s[letter] == 'F') {
                farmer_position[0] = i;
                farmer_position[1] = (int)letter + 1;
                field[i][(int)letter+1] = 1;
            }

        }
    }

    int cow_trip[400][3];
    int farmer_trip[400][3];

    cow_trip[0][0] = cow_position[0];
    cow_trip[0][1] = cow_position[1];
    cow_trip[0][2] = cow_position[2];

    farmer_trip[0][0] = farmer_position[0];
    farmer_trip[0][1] = farmer_position[1];
    farmer_trip[0][2] = farmer_position[2];

    int cow_start_cycle = -1;
    int cow_last = 0;

    int farmer_start_cycle = -1;
    int farmer_last = 0;


    while (cow_start_cycle == -1 && cow_last < 399) {
        find_next_pos(cow_position, field);

        int previous_visit = find_in_trip(cow_position, cow_trip);

        if (previous_visit != -1) {
            cow_start_cycle = previous_visit;
        } else {
            cow_last++;

            cow_trip[cow_last][0] = cow_position[0];
            cow_trip[cow_last][1] = cow_position[1];
            cow_trip[cow_last][2] = cow_position[2];
        }
    }

    while (farmer_start_cycle == -1 && farmer_last < 399) {
        find_next_pos(farmer_position, field);

        int previous_visit = find_in_trip(farmer_position, farmer_trip);

        if (previous_visit != -1) {
            farmer_start_cycle = previous_visit;
        } else {
            farmer_last++;

            farmer_trip[farmer_last][0] = farmer_position[0];
            farmer_trip[farmer_last][1] = farmer_position[1];
            farmer_trip[farmer_last][2] = farmer_position[2];
        }
    }

    if (cow_start_cycle == -1) cow_start_cycle = 0;
    if (farmer_start_cycle == -1) farmer_start_cycle = 0;

    int max_counter =  std::max(cow_start_cycle, farmer_start_cycle) + (cow_last - cow_start_cycle + 1) * (farmer_last - farmer_start_cycle + 1);
    int counter = 1;

    int result = 0;
    int cow_index = 1;
    int farmer_index = 1;

    while (counter < max_counter) {
        if (cow_trip[cow_index][0] == farmer_trip[farmer_index][0] && cow_trip[cow_index][1] == farmer_trip[farmer_index][1]) {
            result = counter;
            break;
        } else {
            counter++;

            cow_index++;
            if (cow_index > cow_last) cow_index = cow_start_cycle;

            farmer_index++;
            if (farmer_index > farmer_last) farmer_index = farmer_start_cycle;
        }
    }

    fout << result << endl;
    return 0;
}
