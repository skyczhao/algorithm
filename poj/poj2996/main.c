
#include <stdio.h>
#include <string.h>
#include <queue>
#include <vector>

struct position{
    int x;
    int y;
    position(){
        x = -1;
        y = -1;
    }
    position(int xx, int yy){
        x = xx;
        y = yy;
    }
};

struct whitecmp{
    bool operator()(const position &left, const position &right)
    {
        if(left.x == right.x)
            return left.y > right.y;
        else
            return left.x > right.x;
    }
};

struct blackcmp{
    bool operator()(const position &left, const position &right)
    {
        if(left.x == right.x)
            return left.y > right.y;
        else
            return left.x < right.x;
    }
};

char str[35];
position K, Q;
std::priority_queue<position, std::vector<position>, whitecmp> R, B, N, P;
position k, q;
std::priority_queue<position, std::vector<position>, blackcmp> r, b, n, p;

int main()
{
    int row, col, j, length;
    bool flag;
    row = 0;
    while(scanf("%s", str) != EOF && row < 8){
        scanf("%s", str);
        length = strlen(str);
        col = -1;
        for(j = 0; j < length; j++){
            switch(str[j]){
                case 'K':
                    K.x = 7 - row;
                    K.y = col;
                    break;
                case 'Q':
                    Q.x = 7 - row;
                    Q.y = col;
                    break;
                case 'R':
                    R.push(position(7 - row, col));
                    break;
                case 'B':
                    B.push(position(7 - row, col));
                    break;
                case 'N':
                    N.push(position(7 - row, col));
                    break;
                case 'P':
                    P.push(position(7 - row, col));
                    break;
                case 'k':
                    k.x = 7 - row;
                    k.y = col;
                    break;
                case 'q':
                    q.x = 7 - row;
                    q.y = col;
                    break;
                case 'r':
                    r.push(position(7 - row, col));
                    break;
                case 'b':
                    b.push(position(7 - row, col));
                    break;
                case 'n':
                    n.push(position(7 - row, col));
                    break;
                case 'p':
                    p.push(position(7 - row, col));
                    break;
            }
            if(str[j] == '|')
                col++;
        }
        row++;
    }

    flag = false;
    printf("White: ");
    if(flag)
        printf(",");
    if(K.x != -1){
        printf("K%c%d", K.y + 'a', K.x + 1);
        flag = true;
    }
    if(flag)
        printf(",");
    if(Q.x != -1){
        printf("Q%c%d", Q.y + 'a', Q.x + 1);
        flag =  true;
    }
    while(!R.empty()){
        if(flag)
            printf(",");
        flag = true;
        printf("R%c%d", R.top().y + 'a', R.top().x + 1);
        R.pop();
    }
    while(!B.empty()){
        if(flag)
            printf(",");
        flag = true;
        printf("B%c%d", B.top().y + 'a', B.top().x + 1);
        B.pop();
    }
    while(!N.empty()){
        if(flag)
            printf(",");
        flag = true;
        printf("N%c%d", N.top().y + 'a', N.top().x + 1);
        N.pop();
    }
    while(!P.empty()){
        if(flag)
            printf(",");
        flag = true;
        printf("%c%d", P.top().y + 'a', P.top().x + 1);
        P.pop();
    }
    printf("\n");

    flag = false;
    printf("Black: ");
    if(flag)
        printf(",");
    if(k.x != -1){
        printf("K%c%d", k.y + 'a', k.x + 1);
        flag = true;
    }
    if(flag)
        printf(",");
    if(q.x != -1){
        printf("Q%c%d", q.y + 'a', q.x + 1);
        flag =  true;
    }
    while(!r.empty()){
        if(flag)
            printf(",");
        flag = true;
        printf("R%c%d", r.top().y + 'a', r.top().x + 1);
        r.pop();
    }
    while(!b.empty()){
        if(flag)
            printf(",");
        flag = true;
        printf("B%c%d", b.top().y + 'a', b.top().x + 1);
        b.pop();
    }
    while(!n.empty()){
        if(flag)
            printf(",");
        flag = true;
        printf("N%c%d", n.top().y + 'a', n.top().x + 1);
        n.pop();
    }
    while(!p.empty()){
        if(flag)
            printf(",");
        flag = true;
        printf("%c%d", p.top().y + 'a', p.top().x + 1);
        p.pop();
    }
    printf("\n");
    return 0;
}
