
#include <iostream>
#include <vector>
#include <list>
#include <queue>
#include <cstring>
using namespace std;

struct cost {
    int b;
    int c;
};

int n;
vector<cost> graph[100001];

void insertList(vector<cost> &l, int b, int c)
{
    list<cost>::iterator lit;
    bool flag;

    // find the old cost
    for (int i = 0; i < l.size(); i++) {
        if (l[i].b == b) {
            // replace if smaller
            if (l[i].c > c) {
                l[i].c = c;
            }
            return;
        }
    }

    cost x;
    x.b = b;
    x.c = c;
    l.push_back(x);
}

void insert(int a, int b, int c)
{
    insertList(graph[a], b, c);
    insertList(graph[b], a, c);
}

vector<int> paths[100001];
bool visited[100001];

bool isSmall(vector<int> left, vector<int> right) 
{
    if (left.size() < right.size()) {
        return true;
    } else if (left.size() > right.size()) {
        return false;
    } else {
        for (int i = 0; i < left.size(); i++) {
            if (left[i] < right[i]) {
                return true;
            } else if (left[i] > right[i]) {
                return false;
            }
        }
        return false;
    }
}

void dijkstra() 
{
    queue<int> spfa;
    paths[0].push_back(0);
    spfa.push(0);
    visited[0] = false;
    while (!spfa.empty()) {
        int index = spfa.front();
        spfa.pop();
        visited[index] = false;
        vector<int> min = paths[index];

        for (int i = 0; i < graph[index].size(); i++) {
            vector<int> next = min;
            next.push_back(graph[index][i].c);
            if (paths[graph[index][i].b].size() == 0 || isSmall(next, paths[graph[index][i].b])) {
                paths[graph[index][i].b] = next;
                if (!visited[graph[index][i].b]) {
                    spfa.push(graph[index][i].b);
                    visited[graph[index][i].b] = true;
                }
            }
        }

        // cout << "===" << endl;
        // for (int i = 0; i < n; i++) {
        //     cout << paths[i] << endl;
        // }
    }
}

int main()
{
    int size, m;
    int i, a, b, c;
    cin >> size;
    while (size--) {
        cin >> n >> m;

        for (i = 0; i < 100005; i++)
            graph[i].clear();
        for (i = 0; i < 100005; i++)
            paths[i].clear();
        memset(visited, 0, sizeof(visited));

        for (i = 0; i < m; i++) {
            cin >> a >> b >> c;
            if (a != b)
                insert(a - 1, b - 1, c);
        }

        // for (i = 0; i < n; i++) {
        //     cout << i << ": ";
        //     list<cost>::iterator lit = graph[i].begin();
        //     while (lit != graph[i].end()) {
        //         cout << lit->b << "." << lit->c << " ";
        //         lit++;
        //     }
        //     cout << endl;
        // }

        dijkstra();

        cout << paths[n - 1].size() - 1 << endl;
        for (i = 1; i < paths[n - 1].size(); i++) {
            if (i != 1)
                cout << " ";
            cout << paths[n - 1][i];
        }
        cout << endl;
    }
    return 0;
}                                 

