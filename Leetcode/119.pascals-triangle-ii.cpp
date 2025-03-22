/*
 * @lc app=leetcode id=119 lang=cpp
 *
 * [119] Pascal's Triangle II
 */

/*
* Given an index k, return the kth row of the Pascal's triangle.
*
* For example, given k = 3,
* Return [1,3,3,1].
*
* Time complexity: O(k^2)
* Space complexity: O(k)
*/    

#include<vector>
using namespace std;
// @lc code=start
class Solution {
public:
    /* Approach 1: 1D Dynamic programming
    Initialize the vector with all 1s up to rowIndex + 1, the outer for loop starts at 2
    since the base case of 0 and 1 are all 1s. The inner loop of j iterates from the 2nd 
    element from the left upto 2nd last element (elements that need to be updated).alignas

    The most important part is the calculation of ans[i - j] += ans[i - j - 1]. ans[i-j] is
    updated by adding the element to its left using backward iteration.

    Time complexity: O(rowIndex^2)
    Space complexity: O(rowIndex)
    */
   
    vector<int> getRow(int rowIndex) {
        vector<int> ans(rowIndex + 1, 1);

        for (int i = 2; i < rowIndex + 1; i++)
            for (int j = 1; j < i; j++)
                ans[i - j] = ans[i-j] + ans[i - j - 1];
        return ans;
    }
};

// @lc code=end
    /*
    Test cases: rowIndex = 3

    Initialization: ans = [1, 1, 1, 1]

    Outer loop (i=2): 
        Inner loop j = 1: ans[2 - 1] += ans[2 - 1 - 1] => ans[1] += ans[0] => ans = [1, 2, 1, 1]

    Outer loop (i=3):
        Inner loop j = 2: ans[3 - 2] += ans[3 - 2 - 1] => ans[1] += ans[0] => ans = [1, 3, 1, 1]    
        Inner loop j = 1: ans[3 - 1] += ans[3 - 1 - 1] => ans[2] += ans[1] => ans = [1, 3, 3, 1]

    If forward iteration is used ans[j] += ans[j - 1], last case becomes [1, 3, 4, 1] 
    instead as it uses the modified value to update.
    */

