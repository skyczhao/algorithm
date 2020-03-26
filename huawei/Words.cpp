#include <iostream>
#include <string> 
#include <iomanip>

using namespace std;

// we have defined the necessary header files here for this problem.
// If additional header files are needed in your program, please import here.

int main()
{  
  // please define the C++ input here. For example: int a,b; cin>>a>>b;;  
  // please finish the function body here.  
  // please define the C++ output here. For example:cout<<____<<endl; 

  string str; 
  getline(cin, str);

  int length = 0;
  int size = 1; 
  for (int i = 0; i < str.length(); i++) {
      if (str[i] == ' ') {
          size += 1;
      } else {
          length += 1;
      }
  }

  double avg = 1.0 * length / size;
  cout << setiosflags(ios::fixed) << setprecision(2) << avg << endl;

  return 0;
}
