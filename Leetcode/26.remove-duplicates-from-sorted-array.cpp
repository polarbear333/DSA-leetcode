/*
 * @lc app=leetcode id=26 lang=cpp
 *
 * [26] Remove Duplicates from Sorted Array
 */

// @lc code=start

/*
Idea: deletion in an array is O(N). How can we do this more efficiently?
Use a hashset to store the set, then replace the array with the elements in hashset?

two-pointers approach. start at back to remove ?? 

*/
#include <vector>
using namespace std;
class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        int read,write = nums.size()-1;
        while(read != 0){
            read--;
            if(nums[read] != nums[write]){
                nums.erase(nums.begin() + write);
                write--;
            }
        }
        return nums.size();
    }
};
// @lc code=end

