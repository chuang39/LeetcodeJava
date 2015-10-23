package system.java.leetcode.iterator;

import java.util.Iterator;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}

public class BSTInOrderTraversal implements Iterable<TreeNode> {
    TreeNode root;

    @Override
    public Iterator<TreeNode> iterator() {
        return new BinaryTreeIterator(root);
    }

    class BinaryTreeIterator implements Iterator<TreeNode> {
        TreeNode root;
        TreeNode cursor;
        Stack<TreeNode> stack;

        public BinaryTreeIterator(TreeNode root){
            this.root = root;
            this.cursor = root;
            this.stack = new Stack<TreeNode>();
        }


        @Override
        public boolean hasNext() {
            return (!stack.empty() || cursor != null);
        }

        @Override
        public TreeNode next() {
            TreeNode res;
            while (cursor != null) {
                stack.push(cursor);
                cursor = cursor.left;
            }
            cursor = stack.pop();
            res = cursor;
            cursor = cursor.right;
            return res;
        }
    }
}
