/*
 * @lc app=leetcode id=169 lang=cpp
 *
 * [169] Majority Element
 */

// @lc code=start
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int count = 0, curr = 0;
        for(int i=0; i<nums.size(); i++){
            if(count == 0){
                curr = nums[i]; //base case, holding this in constant memory
            }
            if(curr == nums[i]){ //using current counter and comparing to next element
                count++;
            }else{
                count--;
            }
        }
        return curr;
    }
    
};
// @lc code=end

