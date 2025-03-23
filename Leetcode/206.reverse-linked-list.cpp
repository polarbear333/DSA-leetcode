/*
 * @lc app=leetcode id=206 lang=cpp
 *
 * [206] Reverse Linked List
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
        //approach 1: iterative method with constant space
        ListNode* reverseList(ListNode* head) {
            ListNode* prev = nullptr;
            ListNode* current = head; 

            while(current){
                ListNode* nextNode = current->next; //saving next node
                current->next = prev; 
                prev = current;
                current = nextNode;
            }
            return prev; //return prev as the new reversed head
        }
};

// @lc code=end
/* Approach 2: reversing the list without modifying the original list   

        ListNode* reverseList(ListNode* head) {
            ListNode* result = nullptr;
            ListNode* current = head; //copy of the head

            while(current != nullptr){
                ListNode* newNode = new ListNode(current->val);
                newNode->next = result; //prepend to new list
                result = newNode;
                current = current->next; // move to the next node in the original list
            }

            return result; //free this list for memory management
        }
        /* freeing the list, alternative approach: use `std::unique_ptr`
        void freeList(ListNode* head) {
            while (head) {
                ListNode* temp = head;
                head = head->next;
                delete temp;
            }
        }
        */
