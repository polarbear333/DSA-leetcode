/*
 * @lc app=leetcode id=118 lang=cpp
 *
 * [118] Pascal's Triangle
 */

#include<vector>
using namespace std;

// @lc code=start
    class Solution {
    public:
        /* For Pascal Triangle, we use a matrix (2d array) such that each number
        is the sum of the two num directly above it.

        
        numRows[i][j] = numRows[i-1][j] + numRows[i-1][j]
        */
       vector<vector<int>> generate(int numRows) {
            vector<vector<int>> results(numRows);  // Pre-allocate space for rows
            
            for (int i = 0; i < numRows; i++) {
                results[i].resize(i + 1, 1);  // Resize and initialize with 1s
                
                // Compute inner values using previous row
                for (int j = 1; j < i; j++) {
                    results[i][j] = results[i - 1][j - 1] + results[i - 1][j];
                }   
            }
            return results;
        }
    };

// @lc code=end
