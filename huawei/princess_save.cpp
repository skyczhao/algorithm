#include <iostream>
#include <cstring>

using namespace std;

int direct_row[] = {0, 1, 0, -1};
int direct_col[] = {1, 0, -1, 0};

int SSavep(char *visited, int t, int n, int m)
{ 
    // find S & P
    int s_idx = -1;
    int p_idx = -1;
    for (int i = 0; i < m * n; i++) {
        if (visited[i] == 'S') {
            s_idx = i;
        }
        if (visited[i] == 'P') {
            p_idx = i;
        }
    }
    if (s_idx == -1 || p_idx == -1) {
        return -1;
    }

    // create dist & init
    int *dist = new int[m * n + 1];
    memset(dist, -1, (m * n + 1) * sizeof(int));
    int *todo = new int[m * n + 1]; // 原来用的queue, 改成用array
    memset(todo, -1, (m * n + 1) * sizeof(int)); // BUG1: 到达尽头后没有及时跳出

    int totalIdx = 0;
    todo[totalIdx++] = s_idx;
    dist[s_idx] = 0;
    for (int idx = 0; idx < m * n; idx++) {
        int curr = todo[idx];
        if (curr == -1) { // BUG1: 到达尽头后没有及时跳出
            break;
        }

        // skip wall
        if (visited[curr] == '*') {
            continue;
        }

        // break in time
        if (curr == p_idx) {
            break;
        }
        if (dist[curr] >= t) {
            break;
        }

        // calculate for next value
        int row = curr / n;
        int col = curr % n;
        for (int i = 0; i < 4; i++) {
            int t_row = row + direct_row[i];
            int t_col = col + direct_col[i];
            if (t_row >= 0 && t_row < m && t_col >= 0 && t_col < n) {
                int t_idx = t_row * n + t_col;
                // 判断是否访问
                if (dist[t_idx] == -1) {
                    dist[t_idx] = dist[curr] + 1;
                    todo[totalIdx++] = t_idx;
                }
            }
        }

    }

    // check
    bool flag = false;
    if (dist[p_idx] > -1 && dist[p_idx] <= t) {
        flag = true;
    }

    delete [] dist;
    delete [] todo;
    return flag ? 0 : -1;
}  

int main() {
    int n = 4;
	int m = 4; 
	int t = 7;
    char a[4][4] = {
        '.', '.', '.','.',
        '.', '*', '.', '.', 
        'S', '*', '.', '.',
        '.', '*', '*','P'};

    char *p = &a[0][0];
    cout << SSavep(p, t, n, m) << endl;

    return 0;
}
