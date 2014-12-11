
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct node{
    char a;
    node *left;
    node *right;
};

char pre[28], ino[28];
node *root;

int subtree(node *now, int index, int begin, int end)
{
    int i;
    now->a = pre[index];
    for(i = begin; i <= end; i++)
        if(ino[i] == pre[index])
            break;
    if(i != begin){
        now->left = (node*)malloc(sizeof(node));
        index = subtree(now->left, index + 1, begin, i - 1);
    }
    else
        now->left = NULL;
    if(i != end){
        now->right = (node*)malloc(sizeof(node));
        index = subtree(now->right, index + 1, i + 1, end);
    }
    else
        now->right = NULL;
    return index;
}

void freetree(node *now)
{
    if(now == NULL)
        return;
    freetree(now->left);
    freetree(now->right);
    printf("%c", now->a);
    free(now);
}

int main()
{
    while(scanf(" %s %s", pre, ino) != EOF){
        root = (node*)malloc(sizeof(node));
        subtree(root, 0, 0, strlen(ino) - 1);
        freetree(root);
        root = NULL;
        printf("\n");
    }
    return 0;
}
