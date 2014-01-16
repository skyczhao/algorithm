
#include <stdio.h>
#include <string.h>

char game[10][10];

int main()
{
    int i, j, length;
    char chess, div;
    int x, y;
    char str[70];
    memset(game, 0, 100 * sizeof(char));
    scanf("%s", str);
    scanf("%s", str);
    length = strlen(str);
    for(i = 0; i < length; i++){
        if(str[i] >= 'A' && str[i] <= 'Z')
            chess = str[i++];
        else
            chess = 'P';
        y = str[i++] - 'a';
        x = str[i++] - '0';
        x = 8 - x;
        game[x][y] = chess;
    }
    scanf("%s", str);
    scanf("%s", str);
    for(i = 0; i < length; i++){
        if(str[i] >= 'A' && str[i] <= 'Z')
            chess = str[i++];
        else
            chess = 'P';
        chess = chess - 'A' + 'a';
        y = str[i++] - 'a';
        x = str[i++] - '0';
        x = 8 - x;
        game[x][y] = chess;
    }
    for(i = 0; i < 8; i++){
        printf("+---+---+---+---+---+---+---+---+\n");
        for(j = 0; j < 8; j++){
            if((i + j) % 2 == 0)
                div = '.';
            else
                div = ':';
            if(game[i][j] != 0)
                printf("|%c%c%c", div, game[i][j], div);
            else
                printf("|%c%c%c", div, div, div);
        }
        printf("|\n");
    }
    printf("+---+---+---+---+---+---+---+---+\n");
    return 0;
}
