/*
 * @lc app=leetcode id=217 lang=cpp
 *
 * [217] Contains Duplicate
 */

// @lc code=start
#include <vector>
#include <unordered_map>
using namespace std;

class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        unordered_map<int, int> numMap; //nums[index], count
        for(int i=0; i<nums.size(); i++){
            numMap[nums[i]]++; //append element to hashmap;
            if(numMap.find(nums[i]) != numMap.end() && numMap[nums[i]] == 2){
                return true;
            }
        }
        return false;
    }
};
// @lc code=end

