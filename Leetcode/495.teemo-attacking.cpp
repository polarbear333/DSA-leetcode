/*
 * @lc app=leetcode id=495 lang=cpp
 *
 * [495] Teemo Attacking
 */

// @lc code=start
class Solution {
public:
    int findPoisonedDuration(vector<int>& timeSeries, int duration) {
        if(timeSeries.empty()){
            return 0;
        }

        int totalDuration = 0;

        for(int i=0; i < timeSeries.size()-1; ++i){
            int diff = timeSeries[i+1] - timeSeries[i];
            totalDuration += std::min(diff, duration);
        }
        totalDuration += duration; //last case
        return totalDuration;
    }
};
// @lc code=end

