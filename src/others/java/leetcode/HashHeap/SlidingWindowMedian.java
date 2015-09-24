package others.java.leetcode.HashHeap;

import array.java.SlidingWindowMaximum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


class Node {
    Integer id;
    Integer num;

    Node(Node now) {
        id = now.id;
        num = now.num;  // numbers of elements with the same value
    }

    Node(Integer first, Integer second) {
        this.id = first;
        this.num = second;
    }
}

class HashHeap {
    ArrayList<Integer> heap;
    String mode;    // decide min heap or max heap
    int size_t;
    HashMap<Integer, Node> hash;

    HashHeap(String mod) {
        heap = new ArrayList<Integer>();
        mode = mod;
        hash = new HashMap<Integer, Node>();
        size_t = 0;
    }

    int peak() {
        return heap.get(0);
    }
    int size() {
        return size_t;
    }
    boolean isEmpty() {
        return heap.size() == 0;
    }
    int parent(int id) {
        if (id == 0)
            return -1;
        return (id - 1) / 2;
    }
    int lson(int id) {
        return id * 2 + 1;
    }
    int rson(int id) {
        return id * 2 + 2;
    }
    boolean comparesmall(int a, int b) {
        if (a <= b) {
            if (mode == "min")
                return true;
            else
                return false;
        } else {
            if (mode == "min")
                return false;
            else
                return true;
        }
    }


    void siftup(int id) {
        while (parent(id) > -1) {
            int parentId = parent(id);
            // If parent is less than current (default is min heap)
            if (comparesmall(heap.get(parentId), heap.get(id)) == true) {
                break;
            } else {
                swap(id, parentId);
                id = parentId;
            }
        }
    }

    void siftdown(int id) {
        while (lson(id) < heap.size()) {
            int left = lson(id);
            int right = rson(id);
            int son;

            if (right >= heap.size() || comparesmall(heap.get(left), heap.get(right))) {
                son = left;
            } else {
                son = right;
            }
            if (comparesmall(heap.get(id), heap.get(son))) {
                break;
            } else {
                swap(id, son);
            }
            id = son;
        }
    }

    void swap(int a, int b) {
        int vala = heap.get(a);
        int valb = heap.get(b);

        int numa = hash.get(vala).num;
        int numb = hash.get(valb).num;
        hash.put(valb, new Node(a, numb));
        hash.put(vala, new Node(b, numa));
        heap.set(a, valb);
        heap.set(b, vala);
    }

    Integer poll() {
        // poll the head element of the heap
        size_t--;
        Integer cur = heap.get(0);
        Node curNode = hash.get(cur);
        if (curNode.num == 1) {
            swap(0, heap.size()-1);
            hash.remove(cur);
            heap.remove(heap.size()-1);
            if (heap.size() > 0)
                siftdown(0);
        } else {
            curNode.num--;
        }
        return cur;
    }

    void add(int cur) {
        size_t++;
        if (hash.containsKey(cur)) {
            Node curNode = hash.get(cur);
            curNode.num++;
        } else {
            heap.add(cur);
            hash.put(cur, new Node(heap.size()-1, 1));
        }
        siftup(heap.size()-1);
    }

    void delete(int cur) {
        // difference between delete and poll
        // poll only polls head
        // delete can delete any element.
        size_t--;
        Node curNode = hash.get(cur);
        int id = curNode.id;
        int num = curNode.num;
        if (num == 1) {
            swap(id, heap.size()-1);
            hash.remove(cur);
            heap.remove(heap.size()-1);
            if (heap.size() > id) {
                siftup(id);
                siftdown(id);
            }
        } else {
            curNode.num--;
        }
    }
}

public class SlidingWindowMedian {
    /**
     * @param nums
     *            : A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (nums.length == 0)
            return res;

        int median = nums[0];
        HashHeap minheap = new HashHeap("min");
        HashHeap maxheap = new HashHeap("max");
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                // max heap <-> median <-> min heap
                if (nums[i] > median) // larger element added to min heap
                    minheap.add(nums[i]);
                else
                    maxheap.add(nums[i]);
            }
            if (i >= k) {
                if (median == nums[i-k]) {
                    if (maxheap.size() > 0) {
                        median = maxheap.poll();
                    } else if (minheap.size() > 0) {
                        median = minheap.poll();
                    }
                } else if (median < nums[i-k]) {
                    minheap.delete(nums[i-k]);
                } else {
                    maxheap.delete(nums[i-k]);
                }
            }

            while (maxheap.size() > minheap.size()) {
                minheap.add(median);
                median = maxheap.poll();
            }
            while (minheap.size() > maxheap.size()+1) {
                maxheap.add(median);
                median = minheap.poll();
            }

            if (i >= k-1) {
                res.add(median);
            }


        }
        return res;
    }

    public static void main(String[] args) {
        SlidingWindowMedian s = new SlidingWindowMedian();
        int[] input = {142,38,100,53,22,84,168,50,194,136,111,13,47,45,151,164,126,47,106,124,183,8,87,38,91,121,102,46,82,195,53,18,11,165,61};
        s.medianSlidingWindow(input, 35);
    }
}
