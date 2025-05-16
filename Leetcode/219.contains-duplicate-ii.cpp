/*
 * @lc app=leetcode id=219 lang=cpp
 *
 * [219] Contains Duplicate II
 */

// @lc code=start
#include <unordered_map>
using namespace std;


class Solution {
public:
    bool containsNearbyDuplicate(vector<int>& nums, int k) {
        unordered_map<int, int> map;
        for(int i = 0; i < nums.size(); i++){
            int val = nums[i];
            if(map.find(val) != map.end() && i - map[val] <= k){
                return true;
            }
        map[val] = i;
        }
        return false;
    }
};
// @lc code=end

