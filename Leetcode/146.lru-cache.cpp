/*
 * @lc app=leetcode id=146 lang=cpp
 *
 * [146] LRU Cache
 */

// @lc code=start
#include <unordered_map>

struct Node{
    int key;
    int value;
    Node *prev;
    Node *next;

    Node(int key, int value){
        this->key = key;
        this->value = value;
        prev = nullptr;
        next = nullptr;
    }
};

class LRUCache {
public:
    int capacity;
    std::unordered_map<int, Node*> cacheMap;
    Node *head;
    Node *tail;
    
    LRUCache(int capacity) {
        this->capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head->next = tail;
        tail->prev = head;
    }
    
    int get(int key) {
        if(cacheMap.find(key) == cacheMap.end()){
            return -1;
        }

        Node *node = cacheMap[key];
        remove(node);
        add(node);
        return node -> value;
    }
    
    void put(int key, int value) {
        if (cacheMap.find(key) != cacheMap.end()) {
            Node *oldNode = cacheMap[key];
            remove(oldNode);
          	delete oldNode;
          
        }

        Node *node = new Node(key, value);
        cacheMap[key] = node;
        add(node);
       
       
        if (cacheMap.size() > capacity) {
            Node *nodeToDelete = tail->prev;
            remove(nodeToDelete);
            cacheMap.erase(nodeToDelete->key);
          	delete nodeToDelete;
        }
    }

    void add(Node *node){
        Node *nextNode = head->next;
        head->next = node;
        node->prev = head;
        node->next = nextNode;
        nextNode->prev = node;

    }

    void remove(Node *node) {
        Node *prevNode = node->prev;
        Node *nextNode = node->next;
        prevNode->next = nextNode;
        nextNode->prev = prevNode;
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
// @lc code=end

