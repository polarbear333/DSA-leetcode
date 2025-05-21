/*
 * @lc app=leetcode id=283 lang=cpp
 *
 * [283] Move Zeroes
 */

// @lc code=start
#include<vector>
using namespace std;

class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int write = 0; //read to iterate the array, comparing with write to swap non-zero
        for(int read=0; read < nums.size(); read++){
            if(nums[read] != 0){
                int temp = nums[read]; //swap(nums[read], nums[write])
                nums[read] = nums[write];
                nums[write] = temp;
                write++;
            }
        }
    }
};
// @lc code=end

