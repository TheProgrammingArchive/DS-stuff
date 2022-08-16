package com.expmnt;

import com.DataStructuresPackaged.Pair;

import java.util.NoSuchElementException;

/**
 * Binary search tree using iterations instead of recursions, duplicate values not allowed
 * @param <T>
 */
public class BinarySearchTree <T extends Comparable<T>>{
    private int nodeCount = 0;
    private Node<T> root = null;

    // Each node of the tree
    private class Node<E>{
        private E data;
        private Node<E> left, right;

        public Node(E data, Node<E> left, Node<E> right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // Tree usr methods
    public boolean exists(T elem){
        Node<T> trav = root;
        boolean found = false;

        while (true){
            if (elem.compareTo(trav.data) < 0){
                trav = trav.left;
            }
            else if (elem.compareTo(trav.data) > 0){
                trav = trav.right;
            }
            else{
                found = true;
                break;
            }
        }

        return found;
    }

    public boolean add(T elem){
        if (exists(elem)){
            return false;
        }
        else{
            if (nodeCount == 0){
                root = new Node<>(elem, null, null);
            }
            else{
                Node<T> trav = root;
                Node<T> parent = null;

                while (trav != null){
                    parent = trav;

                    if (elem.compareTo(trav.data) < 0){
                        trav = trav.left;
                    }
                    else{
                        trav = trav.right;
                    }
                }

                trav = new Node<>(elem, null, null);
                if (trav.data.compareTo(parent.data) < 0){
                    parent.left = trav;
                }
                else{
                    parent.right = trav;
                }
            }
            nodeCount++;
            return true;
        }
    }

    public void remove(T elem){
        if (nodeCount == 0){
            throw new NoSuchElementException("Remove called on empty list");
        }
        else{
            Node<T> trav = root;
            boolean found = false;
            Node<T> parent = null;

            while (true){
                parent = trav;

                if (elem.compareTo(trav.data) < 0){
                    trav = trav.left;
                }
                else if (elem.compareTo(trav.data) > 0){
                    trav = trav.right;
                }
                else{
                    found = true;
                    break;
                }
            }
            if (!found){
                throw new NoSuchElementException("Object not found");
            }

            // Case 1 : TLD node has no children / leaf node
            if (!hasLeft(trav) && !hasRight(trav)){
                
            }

            // Either of 2 subtrees, right/left present
            else if (hasRight(trav) || hasLeft(trav)){

            }
        }
    }

    // Tree hlpr methods
    private boolean hasLeft(Node<T> node){
        return node.left != null;
    }

    private boolean hasRight(Node<T> node){
        return node.right != null;
    }

    // Searches for the smallest value in a subtree
    private Node<T> getMin(Node<T> node){
        Node<T> trav = node;
        while (trav != null){
            trav = trav.left;
        }

        return trav;
    }
}
