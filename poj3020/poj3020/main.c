
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int left[202][2];
int left_num;
int right[202][2];
int mark[202];
int right_num;
int mark_num;
bool visit[202];
bool pairs[202][202];

bool hungarian(int index)
{
	int i;
	// search its pairs
	for(i = 0; i < right_num; i++){
		if(pairs[index][i] && mark[i] == -1){
			// not used and not visited
			if(!visit[i]){
				mark[i] = index;
				return true;
			}
		}
		// if(pairs[index][i] && !visit[i]){
		// 	visit[i] = true;
		// 	if(mark[i] == -1 || hungarian(mark[i])){
		// 		mark[i] = index;
		// 		return true;
		// 	}
		// }
	}
	for(i = 0; i < right_num; i++){
		if(pairs[index][i] && !visit[i]){
			visit[i] = 1;
			if(hungarian(mark[i])){
				mark[i] = index;
				return true;
			}
			//visit[i] = 0;
		}
	}
	return false;
}

int main()
{
	int size, row, col;
	int i, j;
	char str[12];
	
	scanf("%d", &size);
	while(size--){
		memset(left, 0, sizeof(left));
		left_num = 0;
		memset(right, 0, sizeof(right));
		right_num = 0;
		mark_num = 0;
		memset(pairs, 0, sizeof(pairs));

		// get matrix size
		scanf("%d %d", &row, &col);
		// read points
		for(i = 0; i < row; i++){
			scanf("%s", &str);
			for(j = 0; j < col; j++){
				if(str[j] == '*'){
					// divide into two groups
					if((j + i % 2) % 2 == 1){
						left[left_num][0] = i;
						left[left_num][1] = j;
						left_num++;
					}
					else{
						right[right_num][0] = i;
						right[right_num][1] = j;
						right_num++;
					}
				}
			}
		}

		// set relationship
		for(i = 0; i < left_num; i++){
			for(j = 0; j < right_num; j++){
				if(abs(left[i][0] - right[j][0]) == 1 && left[i][1] == right[j][1])
					pairs[i][j] = 1;
				if(abs(left[i][1] - right[j][1]) == 1 && left[i][0] == right[j][0])
					pairs[i][j] = 1;
			}
		}

		// find max-pairs
		for(i = 0; i < right_num; i++)
			mark[i] = -1;
		for(i = 0; i < left_num; i++){
			memset(visit, 0, sizeof(visit));
			if(hungarian(i))
				mark_num++;
		}

		// result
		printf("%d\n", left_num + right_num - mark_num);
		// for(i = 0; i < right_num; i++)
		// 	printf("%d ", mark[i]);
		// printf("\n");
	}
    return 0;
}
