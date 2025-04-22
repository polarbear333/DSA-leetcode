/*
 * @lc app=leetcode id=62 lang=cpp
 *
 * [62] Unique Paths
 * 
 * Action space: m * n (m for rows, n for cols)
 * Begin state: grid[0][0]
 * End state: grid[m-1][n-1]
 */
// @lc code=start
#include <iostream>
#include <stdint.h>
#include <Windows.h>
#include <algorithm> // For the min function

class Solution {
/*
By observation, total possible steps are (m-1)+(n-1) = (m+n-2),
Hence we can compute the total combinations using direct computation 
or using pascal triangle.

Approach 1: Direct Computation
We can directly calculate the combinations with the combination formula,
however we have to calculate it in a smart way to avoid overflow (2 * 1e9);
The approach I chose is to divide the numerator and denominator one by one 
instead of all at once:
*/
public:
    int uniquePaths(int m, int n) {
        int64_t ans = 1;
        for (int i=0; i < min(m,n)-1; i++){
            ans *= m + n - 2 - i;
            ans /= i + 1;
        }
        return ans;
    }
};
// @lc code=end

// Approach 2: Pascal Triangle:
/* Create a 2d vector (matrix) to represent pascal triangle,
where we will use a DP approach as the matrix stores the binomial 
coefficients and will be reused. `C[i][j]` depends on previous values
of `C[i-1][j-1]` and `C[i-1][j]`, which satisfies the overlapping subproblem
property.


int uniquePaths(int m, int n) {
    vector<vector<uint32_t>> C(n+m, vector<uint32_t>(n+m));
    for(int i=0; i < n+m; i++){
        for(int j=0; j<=i; j++){
            if(j ==0 || j == 1) C[i][j] = 1;
            else C[i][j] = C[i-1][j-1] + C[i-1];
        }
    }
    return C[n+m-2][n-1];
}
*/
