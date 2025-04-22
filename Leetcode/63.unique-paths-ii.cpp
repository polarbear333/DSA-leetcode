/*
 * @lc app=leetcode id=63 lang=cpp
 *
 * [63] Unique Paths II
 */

// @lc code=start
using namespace std;
#include <vector>

class Solution {
public:
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int rows = obstacleGrid.size();
        int cols = obstacleGrid[0].size();


        //create 2d vector for storing number of ways to reach the cell
        vector<vector<int>> dp(rows, vector<int>(cols, 0));

        //checking obstacle s.t can only be reached from above, all cells to the right is 0 if there are obstacles
        for(int i=0; i < rows && obstacleGrid[i][0] == 0; i++){
            dp[i][0] = 1;
        }

        //simiarly, row from the left
        for(int j=0; j < cols && obstacleGrid[0][j] == 0; j++){
            dp[0][j] = 1;
        }

        // Start from cell (1, 1) and fill in the dp matrix until the bottom-right corner of the grid.
        // recurrence formula: dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        for(int i = 1; i < rows; i++){
            for(int j = 1; j < cols; j++){
                // if no obstacles in cell, calculate no. of paths
                if(obstacleGrid[i][j] == 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
                // If there's an obstacle, the number of paths to the current cell will be 0.
            }
        }
        // The bottom-right cell of the dp matrix contains the number of unique paths
        // from the top-left corner to the bottom-right corner, which we return.
        return dp[rows - 1][cols - 1];
    }
    

};
// @lc code=end

