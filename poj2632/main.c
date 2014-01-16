
#include <stdio.h>
#include <string.h>

bool flag[105][105];
int robot[105][3];
int move[4][2]= {0, 1, 1, 0, 0, -1, -1, 0};

int main()
{
    int size, length, width;
    int i, j, x, y, num_rob, num_mov, steps, direct;
    int cas;
    char c;
    scanf("%d", &size);
    while(size--){
        memset(flag, 0, sizeof(flag));
        scanf("%d %d", &length, &width);
        scanf("%d %d", &num_rob, &num_mov);
        for(i = 0; i < num_rob; i++){
            scanf("%d %d %c", &x, &y, &c);
            robot[i][0] = x;
            robot[i][1] = y;
            switch(c){
                case 'N':
                    robot[i][2] = 0;
                    break;
                case 'E':
                    robot[i][2] = 1;
                    break;
                case 'S':
                    robot[i][2] = 2;
                    break;
                case 'W':
                    robot[i][2] = 3;
                    break;
            }
            flag[x][y] = true;
        }
        cas = -1;
        while(num_mov--){
            scanf("%d %c %d", &i, &c, &steps);
            if(cas == -1){
                i--;
                switch(c){
                    case 'L':
                        robot[i][2] += 100;
                        robot[i][2] -= steps;
                        robot[i][2] %= 4;
                        break;
                    case 'R':
                        robot[i][2] += steps;
                        robot[i][2] %= 4;
                        break;
                    case 'F':
                        direct = robot[i][2];
                        while(steps--){
                            x = robot[i][0] + move[direct][0];
                            y = robot[i][1] + move[direct][1];
                            if(x > length || x < 1){
                                cas = 0;
                                break;
                            }
                            if(y > width || y < 1){
                                cas = 1;
                                break;
                            }
                            if(flag[x][y]){
                                cas = 2;
                                break;
                            }
                            flag[ robot[i][0] ][ robot[i][1] ] = false;
                            flag[x][y] = true;
                            robot[i][0] = x;
                            robot[i][1] = y;
                        }
                        break;
                }
                if(cas == 0 || cas == 1)
                    printf("Robot %d crashes into the wall\n", i + 1);
                if(cas == 2){
                    for(j = 0; j < num_rob; j++)
                        if(robot[j][0] == x && robot[j][1] == y)
                            break;
                    printf("Robot %d crashes into robot %d\n", i + 1, j + 1);
                }
            }// end of if
        }
        if(cas == -1)
            printf("OK\n");
    }
    return 0;
}
