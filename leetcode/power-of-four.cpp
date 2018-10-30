#include <iostream>

using namespace std;

class Solution {
public:
    bool isPowerOfFour(int num) {
        if (num <= 0) return false;
        if (num == 1) return true;
        if (num % 2 != 0) return false;

        int left = num;
        while (left > 1) {
            if (left % 4 > 0) return false;

            left = left / 4;
        }
        return true;
    }
};

int main() {
    Solution x;
    cout << x.isPowerOfFour(2) << endl;
    cout << x.isPowerOfFour(3) << endl;
    cout << x.isPowerOfFour(4) << endl;
    cout << x.isPowerOfFour(5) << endl;
    return 0;
}



// 4的整数次幂的二进制数都为 (4)100、(16)10000、(64)1000000......
// 二进制中只有一个1（1在奇数位置），并且1后面跟了偶数个0

bool fn(unsigned int x)      //判断x是否是4的幂次方
{
  if ( x & (x - 1) )         //判断x是否为2的幂次方
	  return false;
  return x & 0x55555555;     //判断1是否在奇数位置上
}
 
int log4(int value)   //非递归判断一个数是4的多少次方   
{
	int x=0;
	while(value>1)
	{
		value>>=1;      //往右移位
		value>>=1;
		x++;
	}
	return x;
}  
