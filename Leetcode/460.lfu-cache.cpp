/*
 * @lc app=leetcode id=460 lang=cpp
 *
 * [460] LFU Cache
 */

// @lc code=start
using namespace std;
#include <unordered_map>

class Node{
public:
    int count;
    int key;
    int value;
    Node* prev;
    Node* next;

    Node(int key, int value){
        this->key = key;
        this->value = value;

        count = 1;
    }

};


class LFUCache {
public:
    int capacity; //max capacity of the cache.
    int minCount; //minimum freq of the cache.

    unordered_map<int, Node*> cacheMap; //maps key with corresponding nodes
    unordered_map<int, pair<Node*, Node*>> freqMap; //maps freq with head and tail of the corresponding nodes.

    LFUCache(int capacity) {
        this->capacity = capacity;
        minCount = 0;
    }
    
    int get(int key) {
        if(cacheMap.find(key) == cacheMap.end()){
            return -1;
        }
        Node* node = cacheMap[key];
        updateCount(node);
        return node->value;
    }

    //references pointer to move through current Node.
    void remove(Node* node){
        Node* prevNode = node->prev;
        Node* nextNode = node->next;
        nextNode->prev = prevNode;
        prevNode->next = nextNode;
    }


    void updateCount(Node* node){
        int oldCount = node->count;
        node->count++;
        remove(node);
        if(freqMap[oldCount].first->next == freqMap[oldCount].second){
            freqMap.erase(oldCount);

            //update minimum count if same
            if(minCount == oldCount){
                minCount++;
            }
        }
        add(node, node->count);
    }

    //adds a node to the freq list
    void add(Node *node, int count){

        //initialize the freq map, if it does not exist
        if(freqMap.find(count) == freqMap.end()){
            
            //dummy head node
            Node *head = new Node(-1,-1);

            //dummy tail node
            Node *tail = new Node(-1,-1);
            head->next = tail; // Link head to tail
            tail->prev = head; // Link tail back to head
            freqMap[count] = {head,tail};
        }

        //insert node after head 
        Node *head = freqMap[count].first;
        Node *temp = head->next;
        node->next = temp;
        node->prev = head;
        head->next = node;
        temp->prev = node;
    }

    // add or update key-value pair in the cache.
    void put(int key, int value) {

        //edge case: 0 capacity
        if(capacity == 0){
            return;
        }

        //update key-value pair if it exists in cache:
        if(cacheMap.find(key) != cacheMap.end()){
            Node *node = cacheMap[key];
            node->value = value;
            updateCount(node);
        }

        //add key-value pair to the cache
        else{
            //remove least frequently used node if cache is full;
            if(cacheMap.size() == capacity){
                Node *node = freqMap[minCount].second->prev;    
                cacheMap.erase(node->key);
                remove(node);

                if(freqMap[minCount].first->next == freqMap[minCount].second){
                    freqMap.erase(minCount);
                }
                delete node;
            }
            //create a new node for key-value pair
            Node *node = new Node(key, value);
            cacheMap[key] = node;

            minCount = 1;
            add(node, 1);
        }
    }
};

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache* obj = new LFUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
// @lc code=end

