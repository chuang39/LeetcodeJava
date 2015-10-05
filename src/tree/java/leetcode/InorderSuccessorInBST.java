package tree.java.leetcode;

/*
    Leetcode: https://leetcode.com/problems/inorder-successor-in-bst/

    Since TreeNode doesn't have parent, the idea is to first find the p in the tree.
    In the process of searching for p, we record the successor for case 2.

    // Two cases:
    // 1. If right child exists, the minimum element of right tree is successor
    // 2. If right child doesn't exist, the first parent who
 */
public class InorderSuccessorInBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;

        while (root != null && root.val !== p.val) {
            if (root.val > p.val) {
                successor = root;   // Save node for case 2.
                root = root.left;
            } else {
                root = root.right;
            }
        }

        if (root == null)
            return null;

        if (root.right == null)
            return successor;

        root = root.right;
        while (root.left != null)
            root = root.left;
        return root;
    }
}
