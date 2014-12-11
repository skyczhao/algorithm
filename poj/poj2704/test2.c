#include<iostream>
#include<cstdio>
#include<cstring>
using namespace std;
int n;
long long f[50][50];
char map[50][50];
int main()
{
while(scanf("%d",&n),~n)
{
memset(f,0,sizeof f);
for(int i=1;i<=n;i++)
scanf("%s",map[i]+1);
f[1][1]=1;
for(int i=1;i<=n;i++)
for(int j=1;j<=n;j++)
{
if(i==n&&j==n) break;
if(i+map[i][j]-'0'<=n)
f[i+map[i][j]-'0'][j]+=f[i][j];
if(j+map[i][j]-'0'<=n)
f[i][j+map[i][j]-'0']+=f[i][j];
}
cout << f[n][n] << endl;
}
return 0;
}
