package com.Tests;

public class LinkedList <T>{
    private int size = 0;
    private Node<T> head = null; // First element

    private static class Node <T> {
        private Node<T> next;
        T data;

        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString(){
            return data.toString();
        }
    }

    // Modifier
    public void appendList(T data){
        if (isEmpty()){
            head = new Node<>(data, null);
        }

        else {
            Node<T> trav = head;

            while (trav.next != null){
                trav = trav.next;
            }
            trav.next = new Node<>(data, null);
        }
        size++;
    }

    public void insertFirst(T data){
        if (isEmpty()){
            head = new Node<>(data, null);
        }

        else {
            Node<T> node = new Node<>(data, null);
            node.next = head;

            head = node;
        }

        size++;
    }

    public void insertAtIndex(T data, int index) throws IndexOutOfBoundsException{
        if (isEmpty()){
            insertFirst(data);
        }

        else {
            if (index >= size || index < 0){
                throw new IndexOutOfBoundsException();
            }

            if (index == 0){
                insertFirst(data);
            }

            else if(index == size - 1){
                appendList(data);
            }

            else{
                Node<T> trav = head;

                for (int i = 0; i < index - 1; i++){
                    trav = trav.next;
                }

                Node<T> insertion_node = new Node<>(data, trav.next);
                trav.next = insertion_node;
            }
            size++;
        }
    }

    public void removeLast() throws IndexOutOfBoundsException{
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        Node<T> trav = head;

        for (int i = 0; i < size - 2; i++){
            trav = trav.next;
        }

        trav.next = null;

        if (isEmpty()){
            head = null;
        }

        size--;
    }

    public void removeFirst() throws IndexOutOfBoundsException{
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }

        var x = head;
        head = null;
        head = x.next;

        size--;
    }

    public void removeAtIndex(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }

        if (index == 0){
            removeFirst();
        }

        else if(index == size - 1){
            removeLast();
        }

        else {
            Node<T> trav = head;
            for (int i = 0; i < index - 1; i++) {
                trav = trav.next;
            }

            trav.next = trav.next.next;
        }
        size--;
    }

    // Non modifier
    public int getSize(){
        return size;
    }

    public int getObjectPosition(T data) throws IndexOutOfBoundsException{
        Node<T> trav = head;

        int counter_pos = 0;
        int index_pos = -1;

        while (trav != null){
            if (trav.toString().equals(data.toString())){
                index_pos = counter_pos;
                break;
            }
            trav = trav.next;
            counter_pos++;
        }

        if (index_pos == -1){
            throw new IndexOutOfBoundsException();
        }

        return index_pos;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public String toString(){
        if (isEmpty()){
            return "[]";
        }

        else {
            StringBuilder arr_str = new StringBuilder();
            arr_str.append("[");

            Node<T> trav = head;
            while (trav != null){
                arr_str.append(trav).append(", ");
                trav = trav.next;
            }

            arr_str.append("]");

            return arr_str.toString();
        }
    }

    // Debug
}
