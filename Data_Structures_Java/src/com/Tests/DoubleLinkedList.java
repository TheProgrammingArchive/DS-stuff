package com.Tests;

public class DoubleLinkedList <T>{
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private class Node <E>{
        private E data;
        private Node<E> prev, next = null;

        public Node(E data, Node<E> prev, Node<E> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString(){
            return data.toString();
        }
    }
    // Modifier
    private void removeNode(Node<T> node){
        if (node.next == null){
            removeLast();
        }
        if (node.prev == null) {
            removeFirst();
        }
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        size--;
    }

    public void appendEnd(T data){
        if (isEmpty()){
            head = tail = new Node<>(data, null, null);
        }
        else{
            tail.next = new Node<>(data, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public void appendBeginning(T data){
        if (isEmpty()){
            head = tail = new Node<>(data, null, null);
        }
        else{
            head.prev = new Node<>(data, null, head);
            head = head.prev;
        }
        size++;
    }

    public void appendAtIndex(T data, int index) throws IllegalArgumentException{
        if (isEmpty()){
            appendEnd(data);
            return;
        }
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Index out of bounds for list of size: " + size);
        }
        if (index == 0){
            appendBeginning(data);
            return;
        }
        if (index == size - 1){
            appendEnd(data);
            return;
        }

        Node<T> trav = head;
        for (int i = 0; i < index - 1; i++){
            trav = trav.next;
        }
        trav.next = new Node<>(data, trav, trav.next);

        size++;
    }

    public void removeLast() throws RuntimeException{
        if (isEmpty()){
            throw new RuntimeException("remove called on empty list");
        }
        tail = tail.prev;
        size--;

        if (isEmpty()){
            head = null;
        }
        else {
            tail.next = null;
        }
    }

    public void removeFirst() throws RuntimeException{
        if (isEmpty()){
            throw new RuntimeException();
        }
        head = head.next;
        size--;

        if (isEmpty()){
            tail = null;
        }
        else{
            head.prev = null;
        }
    }

    public void removeIndex(int index) throws RuntimeException{
        if (isEmpty() || index < 0 || index >= size){
            if (isEmpty()){
                throw new RuntimeException();
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        if (index == 0){
            removeFirst();
            return;
        }
        if (index == size - 1){
            removeLast();
            return;
        }

        Node<T> trav = head;
        for (int i = 0; i < index; i++){
            trav = trav.next;
        }
        removeNode(trav);
    }

    // Non modifier
    public int getObjectIndex(T data) throws IndexOutOfBoundsException{
        int index = -1;
        Node<T> trav = head;

        int counter = 0;
        while (trav != null){
            if (trav.toString().equals(data.toString())){
                index = counter;
                break;
            }
            trav = trav.next;
            counter++;
        }

        if (index == -1){
            throw new IndexOutOfBoundsException();
        }
        return counter;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node<T> trav = head;
        while (trav != null){
            builder.append(trav).append(' ');
            trav = trav.next;
        }
        builder.append("]");

        return builder.toString();
    }
}
