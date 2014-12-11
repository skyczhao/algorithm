
#include <iostream>
#include <cstring>
#include <stack>
#include <list>
using namespace std;

int direct[][2] = {1, 2, 2, 1, 2, -1, 1, -2, -1, -2, -2, -1, -2, 1, -1, 2};
struct node {
    int row;
    int col;
};

stack<node> sn; // stack of node
const int size_row = 8;
const int size_col = 8;
bool state[size_row][size_col];

bool dfs() {
    bool flag;
    int i, j;
    int row, col;
    node next, temp;
    int num;
    list<node> lnode;
    list<node>::iterator lit;

    // check end
    flag = true;
    for (i = 0; i < size_row; i++)
        for (j = 0; j < size_col; j++)
            if (!state[i][j])
                flag = false;
    if (flag)
        return true;

    // predict one step
    for (i = 0; i < 8; i++) {
        next.row = sn.top().row + direct[i][0];
        next.col = sn.top().col + direct[i][1];
        // check overflow
        if (next.row < 0 || next.col < 0)
            continue;
        if (next.row >= size_row || next.col >= size_col)
            continue;

        num = 0;
        for (j = 0; j < 8; j++) {
            row = next.row + direct[j][0];
            col = next.col + direct[j][1];
            // check overflow
            if (row < 0 || col < 0)
                continue;
            if (row >= size_row || col >= size_col)
                continue;
            num++;
        }

        // the min move number firstly
        temp.row = i;
        temp.col = num;
        lit = lnode.begin();
        while (lit != lnode.end()) {
            if (lit->col > temp.col)
                break;
            lit++;
        }
        lnode.insert(lit, temp);
    }

    // select better first
    lit = lnode.begin();
    while (lit != lnode.end()) {
        // cout << lit->row << " " << lit->col << endl;
        i = lit->row;
        lit++;
        // cout << i << endl;
    //for (i = 0; i < 8; i++) {
        next.row = sn.top().row + direct[i][0];
        next.col = sn.top().col + direct[i][1];
        // check overflow
        if (next.row < 0 || next.col < 0)
            continue;
        if (next.row >= size_row || next.col >= size_col)
            continue;

        // check available
        if (!state[next.row][next.col]) {
            sn.push(next);
            state[next.row][next.col] = true;
            if (dfs()) {
                return true;
            } else {
                state[next.row][next.col] = false;
                sn.pop();
            }
        }
    }
    return false;
}

int main()
{
    int start;
    node first;
    stack<int> res;
    bool flag;
    while (cin >> start) {
        if (start == -1)
            break;

        // initialize
        memset(state, 0, size_col * size_row * sizeof(bool));
        start--;

        // first node
        first.row = start / size_col;
        first.col = start % size_col;
        sn.push(first);
        state[first.row][first.col] = true;
        // result
        if (dfs()) {
            while (!sn.empty()) {
                res.push(sn.top().row * size_col + sn.top().col + 1);
                sn.pop();
            }

            flag = false;
            while (!res.empty()) {
                if (flag) {
                    cout << " ";
                }
                flag = true;
                cout << res.top();
                res.pop();
            }
            cout << endl;
        } else {
            state[first.row][first.col] = false;
            sn.pop();
        }

    }
    return 0;
}
