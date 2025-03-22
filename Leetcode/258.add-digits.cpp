/*
 * @lc app=leetcode id=258 lang=cpp
 *
 * [258] Add Digits
 */


// @lc code=start
class Solution {
    /* Approach 1: Digit Root
    Constant time method of using digital root property in modular arithmetic to compute in constant space.
    */      
    public:
        int addDigits(int num) {
            if (num == 0) return 0;
            else if (num % 9 == 0) return 9;
            else return num % 9;
        }
};

// @lc code=end

/*
Approach 2: Iterative
Since we need to divide all the digits until we get a final single digit, we need a loop that
continues to run until we finally have a single digit, while adding the remainders as a sum. 

   int addDigits(int num) {
       while(num >= 10){
           int sum = 0;
           while(num != 0){
               sum += num % 10;
               num /= 10;
           }
           num = sum; // replace num with sum for next iteration until single digit;
       }
       return num;
   }
*/

/* 
Approach 3: Recursion
Base case: Single digit, then just return the value
Other cases that has more than 1 digit: Use modulo 10 to get the remainder of the last digit, 
then divide by 10 until we finally get the base case.

    int addDigits(int num) {
        if(num < 10){
            return num;
        }
        int sum = 0;
        while(num !=0){
            sum += num % 10;
            num /= 10;
        }
        return addDigits(sum);
    }
*/


