/*
 * @lc app=leetcode id=27 lang=cpp
 *
 * [27] Remove Element
 */

// @lc code=start
class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int write = 0;
        for(int read=0; read < nums.size(); read++){
            if(nums[read] != val){
                nums[write] = nums[read];
                write++;
            }
        }
        return write; 
    }
};
// @lc code=end

