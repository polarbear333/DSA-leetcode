/*
 * @lc app=leetcode id=15 lang=cpp
 *
 * [15] 3Sum
 * 
 * 
 */

// @lc code=start
#include <vector>;
#include <algorithm>;
using namespace std;

class Solution {
public:
    /* Approach 1: Two-pointer approach
   Time complexity: O(N^2)
   Space complexity: O(1), constant space, where extra space is only allocated for the vector output.
    */
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> result;
        int n = nums.size();
        
        //sorting the array
        sort(nums.begin(), nums.end());

        // iterate over the array using two pointers
        for (int i = 0; i < n - 2; i++) {
            // skip duplicate elements for the first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int low = i + 1, high = n - 1;
            
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                
                if (sum < 0) {
                    low++;  //  move left pointer right as sum smaller than 0
                } else if (sum > 0) {
                    high--; // move right pointer left as sum larger than 0
                } else {
                    result.push_back({nums[i], nums[low], nums[high]});
                    
                    // skip duplicate elements for the second and third numbers
                    while (low < high && nums[low] == nums[low + 1]) low++;
                    while (low < high && nums[high] == nums[high - 1]) high--;

                    // move pointers for next unique triplet
                    low++;
                    high--;
                }
            }
        }
        return result;
    }
};
// @lc code=end

    /* 
    Approach 2: Sorted array and using Hashmap
    Time complexity: O(N^2)
    Space complexity: O(N) for hashmap(set) space.
    

    vector<vector<int>> threeSum(vector<int>& nums) {
        
        vector<vector<int>> results;
        sort(nums.begin(), nums.end());

        for(int i=0; i<nums.size()-2; i++){
            if(i>0 && nums[i] - nums[i-1] == 0) continue;
        
            unordered_map<int, int> map;
            set<vector<int>> uniqueTriplets;

            for(int j= i+1; j < nums.size(); j++){
                int complements = -(nums[i] + nums[j]);
                if(map.count(complements)){ // Check if complement exists in hashmap
                    vector<int> triplet = {nums[i], nums[j], complements};
                    sort(triplet.begin(), triplet.end()); //sort triplet for uniqueness
                    
                    uniqueTriplets.insert(triplet); //add to set to handle duplicates
                }
                map[nums[j]] = j; //add current number to hashmap 
            }
            results.insert(results.end(), uniqueTriplets.begin(), uniqueTriplets.end());
        }
        return results;
    }
    */