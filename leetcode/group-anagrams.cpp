#include <iostream>
#include <vector>
#include <map>
using namespace std;

class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        int charCount[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
                            0, 0, 0, 0, 0, 0};
        char countBuffer[50];

        map<string, vector<string>> resMap = map<string, vector<string>>();
        for (int i = 0; i < strs.size(); i++) {
            // count
            for (int j = 0; j < strs[i].length(); j++) {
                int c_idx = strs[i][j] - 'a';
                charCount[c_idx]++;
            }
            // generate
            string fingerprint = "";
            for (int j = 0; j < 26; j++) {
                if (charCount[j] > 0) {
                    fingerprint += ('a' + j);
                    sprintf(countBuffer, "%d", charCount[j]);
                    fingerprint += countBuffer;

                    charCount[j] = 0;
                }
            }

            // store
            if (resMap.find(fingerprint) == resMap.end()) {
                resMap[fingerprint] = vector<string>();
            }
            resMap[fingerprint].push_back(strs[i]);
        }

        // build result
        vector<vector<string>> res = vector<vector<string>>();
        map<string, vector<string>>::iterator it = resMap.begin();
        while (it != resMap.end()) {
            res.push_back(it->second);
            it++;
        }
        return res;
    }
};

int main() {
    string ori_arr[] = {"eat", "tea", "tan", "ate", "nat", "bat", "uuuuuuuuuuuuuuuuuu"};
    vector<string> ori = vector<string>(ori_arr, ori_arr + 7);

    Solution s = Solution();
    vector<vector<string>> result = s.groupAnagrams(ori);

    for (int i = 0; i < result.size(); i++) {
        for (int j = 0; j < result[i].size(); j++) {
            cout << result[i][j] << " ";
        }
        cout << endl;
    }
}