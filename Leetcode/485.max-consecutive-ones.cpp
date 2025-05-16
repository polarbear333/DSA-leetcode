/*
 * @lc app=leetcode id=485 lang=cpp
 *
 * [485] Max Consecutive Ones
 */

// @lc code=start
#include <vector>

class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int maxCount = 0, count = 0;
        for(int i=0; i<nums.size();i++){
            if(nums[i] == 1){
                count++;
            }else{
                count = 0;
            }
            if(count>=maxCount){
                maxCount = count;
            }
        }
        return maxCount;
    }
};
// @lc code=end

/* //maxConsecutiveOnes of either 1s or 0s

    int findMaxConsecutiveOnes(vector<int>& nums) {
        int maxCount = 0, count = 0, temp = -1;
        for(int i=0; i<nums.size();i++){
            if(temp == nums[i]){
                count++;
            }else{
                count = 1;
            }
            if(count>=maxCount){
                maxCount = count;
            }
            temp = nums[i];
        }
        return maxCount;
    }
*/