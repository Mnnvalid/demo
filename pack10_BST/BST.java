package solutions.pack10_BST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BST {

    TreeNode root;

    public BST() {
        root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void insert(int d) {
        TreeNode current = root;
        TreeNode prev = null;
        TreeNode newNode = new TreeNode(d);

        while (current != null) {
            prev = current;

            if (d < current.data)
                current = current.left;
            else
                current = current.right;
        }

        if (prev == null) {
            root = newNode;
            return;
        }

        if (d < prev.data)
            prev.left = newNode;
        else
            prev.right = newNode;
        
        newNode.parent = prev;
    }

    public TreeNode search(int d){
        return searchRecurse(d, root);
    }

    private TreeNode searchRecurse(int d, TreeNode node) {
        if (node == null) {
            return null;
        }
        if (d == node.data) {
            return node;
        }
        if (d < node.data) {
            return searchRecurse(d, node.left);
        } else {
            return searchRecurse(d, node.right);
        }
    }

    
    public void delete(int d) {
        TreeNode del = this.search(d);
        delete(d, del);
    }

    private void delete(int d, TreeNode node) {
        if (node == null) {
            return;
        }
        if (d < node.data) {
            delete(d, node.left);
        } else if (d > node.data) {
            delete(d, node.right);
        } else {
            
            if ((node.left == null) || (node.right == null)) {
                TreeNode q = (node.left == null) ? node.right : node.left;
            
                if (node.parent.left == node) {
                    node.parent.left = q;
                } else {
                    node.parent.right = q;
                }
                
                if (q != null) {
                    q.parent = node.parent;
                }
            }
            else {
                TreeNode q = findMaxFrom(node.left);
                delete(q.data, node.left);
                if (node.parent.left == node) {
                    node.parent.left = q;
                } else {
                    node.parent.right = q;
                }
                q.left = node.left;
                q.right = node.right;
                q.parent = node.parent;
                
            }
        }
    }
    public TreeNode findMaxFrom (TreeNode node){
        while (node.right != null)
            node = node.right;
        return node;
    } 

    public void printInOrder() {
        printInOrderRecurse(root);
        System.out.println();
    }

    private void printInOrderRecurse(TreeNode node) {
        if (node == null) {
            return;
        }
        printInOrderRecurse(node.left);
        System.out.println(node.data + " ");
        printInOrderRecurse(node.right);
    }

    public void printPreOrder() {
        printPreOrderRecurse(root);
    }

    private void printPreOrderRecurse(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.data + " ");

        printInOrderRecurse(node.left);
        printInOrderRecurse(node.right);
    }

    public void printPostOrder() {
        printPostOrderRecurse(root);
    }

    private void printPostOrderRecurse(TreeNode node) {
        if (node == null) {
            return;
        }

        printInOrderRecurse(node.left);
        printInOrderRecurse(node.right);

        System.out.println(node.data + " ");
    }

    public int findMin() {
        return findMin(root);
    }

    private int findMin(TreeNode node) {
        if (node.left == null)
            return node.data;

        return findMin(node.left);
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode node) {
        if (node == null)
            return 0;

        return Math.max(height(node.left) + 1, height(node.right) + 1);
    }

    public int count() {
        return count(root);
    }

    private int count(TreeNode node) {
        if (node == null)
            return 0;

        return count(node.left) + 1 + count(node.right);
    }

    public int findMax() {
        return findMax(root);
    }

    private int findMax(TreeNode node) {
        if (node.right == null)
            return node.data;

        return findMax(node.right);
    }

    public double findMedian() {
        List<Integer> datas = getAllNodesData(root);
        int n = datas.size();

        if (n % 2 == 1)
            return datas.get(n/2);
        
        return (datas.get(n/2) + datas.get(n/2-1)) / 2.0;
    }

    
    public int findRank(int data) {
        List<Integer> datas = getAllNodesData(root);
        int position = Collections.binarySearch(datas, data);

        position = (position < 0 ? -1 : position + 1);

        return position;
    }

    private List<Integer> getAllNodesData(TreeNode node) {
        if (node == null) return new ArrayList<>();

        List<Integer> data = new ArrayList<>();
        data.addAll(getAllNodesData(node.left));
        data.add(node.data);
        data.addAll(getAllNodesData(node.right));

        return data;
    }
}
//hello world