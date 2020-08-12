#include <iostream>
#include <cstring>
// #include <Queue>

using namespace std;

// int SSavep(char *visited, int t, int n, int m)
// { 
//     // find S & P
//     // 大小写都有可能
//     int s_idx = -1;
//     int p_idx = -1;
//     for (int i = 0; i < m * n; i++) {
//         if (visited[i] == 'S' || visited[i] == 's') {
//             s_idx = i;
//         }
//         if (visited[i] == 'P' || visited[i] == 'p') {
//             p_idx = i;
//         }
//     }
//     if (s_idx == -1 || p_idx == -1) {
//         return -1;
//     }

//     // create dist & init
//     int *dist = new int[m * n + 1];
//     memset(dist, -1, (m * n + 1) * sizeof(int));

//     queue<int> nodes;
//     nodes.push(s_idx);
//     dist[s_idx] = 0;
//     while (!nodes.empty()) {
//         int curr = nodes.front();
//         nodes.pop();

//         // skip wall
//         if (visited[curr] == '*') {
//             continue;
//         }
//         if (curr == p_idx) {
//             continue;
//         }

//         // calculate for next value
//         int row = curr / n;
//         int col = curr % n;
//         for (int i = 0; i < 4; i++) {
//             int t_row = row + direct_row[i];
//             int t_col = col + direct_col[i];
//             if (t_row >= 0 && t_row < m && t_col >= 0 && t_col < n) {
//                 int t_idx = t_row * n + t_col;
//                 // 判断是否访问
//                 if (dist[t_idx] == -1 || dist[t_idx] > dist[curr] + 1) {
//                     dist[t_idx] = dist[curr] + 1;
//                     nodes.push(t_idx);
//                 }
//             }
//         }

//     }

//     // check
//     bool flag = false;
//     if (dist[p_idx] > -1 && dist[p_idx] <= t) {
//         flag = true;
//     }

//     delete [] dist;
//     return flag ? 0 : -1;
// }  

int direct_row[] = {0, 1, 0, -1};
int direct_col[] = {1, 0, -1, 0};

void dfs(int curr, int* dist, char* visited, int limit, int row, int col, int end) {
    // skip wall
    if (visited[curr] == '*') {
        return;
    }
    if (curr == end) {
        return;
    }
    if (dist[curr] >= limit) {
        return;
    }

    // for (int i = 0; i < row; i ++) {
    //     for (int j = 0; j < col; j++) {
    //         cout << dist[i * col + j] << ", ";
    //     }
    //     cout << endl;
    // }

    // calculate for next value
    int c_row = curr / col;
    int c_col = curr % col;
    // cout << c_row << ", " << c_col << endl;
    // cout << endl;

    for (int i = 0; i < 4; i++) {
        int t_row = c_row + direct_row[i];
        int t_col = c_col + direct_col[i];
        if (t_row >= 0 && t_row < row && t_col >= 0 && t_col < col) {
            int t_idx = t_row * col + t_col;
            // 判断是否更新
            if (visited[t_idx] == '*') {
                continue;
            }
            if (dist[t_idx] == -1 || dist[t_idx] > dist[curr] + 1) {
                dist[t_idx] = dist[curr] + 1;
                dfs(t_idx, dist, visited, limit, row, col, end);
            }
        }
    }
}

int SSavep(char *visited, int t, int n, int m)
{ 
    // find S & P
    // 大小写都有可能
    int start = -1;
    int end = -1;
    for (int i = 0; i < m * n; i++) {
        if (visited[i] == 'S' || visited[i] == 's') {
            start = i;
        }
        if (visited[i] == 'P' || visited[i] == 'p') {
            end = i;
        }
    }
    if (start == -1 || end == -1) {
        return -1;
    }

    // create dist & init
    int *dist = new int[m * n + 1];
    memset(dist, -1, (m * n + 1) * sizeof(int));

    dist[start] = 0;
    dfs(start, dist, visited, t, m, n, end);

    // check
    bool flag = false;
    if (dist[end] > -1 && dist[end] <= t) {
        flag = true;
    }

    delete [] dist;
    return flag ? 0 : -1;
}  

int main() {
    int n = 4;
	int m = 5; 
	int t = 7;
    char a[5][4] = {
        '.', '.', '.','.',
        '.', '*', '.','.',
        '.', '.', '.', '.', 
        's', '*', '.', '.',
        '.', '*', '.','P'};

    char *p = &a[0][0];
    cout << SSavep(p, t, n, m) << endl;

    return 0;
}
