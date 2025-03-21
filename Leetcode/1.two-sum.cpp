/*
 * @lc app=leetcode id=1 lang=cpp
 *
 * [1] Two Sum
 */

// @lc code=start

#include <vector>
#include <unordered_map>
using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        /* 
        // First approach with two for loops, O(N^2)
        for(int i =0; i < nums.size(); i++){
            for(int j = i + 1; j < nums.size(); j++){
                if(nums[i] + nums[j] == target){
                    return {i, j};
                }
            }
        }
        return {};
        */

        // Second approach using a hashmap (unordered_map):
        unordered_map<int, int> numMap;
        for (int i=0; i <nums.size(); i++){
            int complement = target - nums[i];
            if (numMap.count(complement)){
                return {numMap[complement], i};
            }
            numMap[nums[i]] = i;
        }
        return {};
    }
};
// @lc code=end

