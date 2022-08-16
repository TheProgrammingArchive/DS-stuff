package com.Tests;

import java.util.Collection;
import java.util.Collections;

/*
Priority Queue implemented using Double Linked List
Generic Queue
 */
public class PriorityQueueLL<T>{
    private int queueSize = 0;                                                // Queue Size
    private Node<T> head = null;                                              // Highest Priority Node or Head Node

    private static class Node<T>{                                             // Internal Node class
        private T data;                                                       // Data of generic type T
        private int priorityKey;                                              // Priority Key where Priority is inv-proportional to priorityKey
        private Node<T> next, prev;                                           // Pointers to next and previous node

        public Node(T data, int priorityKey, Node<T> prev, Node<T> next){     // Constructor
            this.data = data;
            this.priorityKey = priorityKey;
            this.next = next;
            this.prev = prev;
        }

        public void modifyKeyData(T replacementData){                         // Modifiy data for a PQ node
            this.data = replacementData;
        }

        @Override
        public String toString(){
            return data.toString();
        }
    }
    // Modifier
    private void removeNode(Node<T> node){
        if (node.prev == null){
            head = head.next;
            queueSize--;

            if (isEmpty()){
                head = null;
            }
            else{
                head.prev = null;
            }
        }
        else if (node.next == null){
            Node<T> trav = head;
            for (int i = 0; i < queueSize - 2; i++){
                trav = trav.next;
            }
            trav.next = null;
            queueSize--;
        }
        else{
            node.next.prev = node.prev;
            node.prev.next = node.next;
            queueSize--;
        }
    }

    public void addSet(int priorityKey, T payload) throws IllegalArgumentException{
        if (payload == null){
            throw new IllegalArgumentException("Priority Queue Does not support null values");
        }
        if (isEmpty()){
            head = new Node<>(payload, priorityKey, null, null);
        }
        else{
            if (priorityKey < head.priorityKey){
                head.prev = new Node<>(payload, priorityKey, null, head);
                head = head.prev;
            }
            else{
                if (keyExistence(priorityKey)){
                    Node<T> trav = head;
                    while (trav.priorityKey != priorityKey){
                        trav = trav.next;
                    }
                    trav.modifyKeyData(payload);
                    return;
                }
                Node<T> trav = head;
                while (trav.next != null && trav.next.priorityKey < priorityKey){
                    trav = trav.next;
                }
                trav.next = new Node<>(payload, priorityKey, trav, trav.next);
            }
        }
        queueSize++;
    }

    public T poll() throws RuntimeException{
        if (isEmpty()){
            throw new RuntimeException();
        }

        Node<T> trav = head;
        T data = head.data;
        head = head.next;
        queueSize--;

        if (isEmpty()){
            head = null;
        }
        else{
            head.prev = null;
        }
        return data;
    }

    public void remove(int priorityKey) throws IllegalArgumentException{
        if (!keyExistence(priorityKey)){
            throw new IllegalArgumentException();
        }
        Node<T> trav = head;
        while (trav != null && trav.priorityKey != priorityKey){
            trav = trav.next;
        }

        removeNode(trav);
    }

    public void remove(T objectData) throws RuntimeException{
        if (!objectExistence(objectData)){
            throw new RuntimeException();
        }
        Node<T> trav = head;
        while (trav.data != objectData){
            trav = trav.next;
        }
        removeNode(trav);
    }

    // Non Modifier
    public boolean isEmpty(){
        return queueSize == 0;
    }

    public T peekHead(){
        return head.data;
    }

    public int getQueueSize(){
        return queueSize;
    }

    public boolean keyExistence(int priorityKey){
        boolean exists = false;

        Node<T> trav = head;
        while (trav != null){
            if (priorityKey == trav.priorityKey){
                exists = true;
                break;
            }
            trav = trav.next;
        }
        return exists;
    }

    public boolean objectExistence(T data){
        boolean exists = false;

        Node<T> trav = head;
        while (trav != null){
            if (trav.data.equals(data)){
                exists = true;
                break;
            }
            trav = trav.next;
        }
        return exists;
    }

    public int getPositionData(T data){
        if (!objectExistence(data)){
            throw new IllegalStateException("Object out of bounds");
        }
        Node<T> trav = head;
        int pos = 0;
        while (trav.data != data){
            trav = trav.next;
            pos++;
        }
        return pos;
    }

    public int getPositionPriority(int priorityIndex){
        if (!keyExistence(priorityIndex)){
            throw new IllegalStateException("Object out of bounds");
        }
        Node<T> trav = head;
        int pos = 0;
        while (trav.priorityKey != priorityIndex){
            pos++;
            trav = trav.next;
        }
        return pos;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        Node<T> trav = head;
        while (trav != null) {
            builder.append("(").append(trav.priorityKey).append(", ").append(trav).append(")").append(", ");
            trav = trav.next;
        }

        builder.append("]");
        return builder.toString();
    }
}
