package com.DataStructuresPackaged;

public class DoubleLinkedList <T>{
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private class Node <E>{
        private Node<E> prev, next;
        private E data;

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
    // Modifier methods
    private void removeNode(Node<T> node){
        if (node.next == null){
            removeEnd();
            return;
        }
        if (node.prev == null){
            removeBeginning();
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;

        size--;
    }

    public void appendEnd(T payload){
        if (isEmpty()){
            head = tail = new Node<>(payload, null, null);
        }
        else{
            tail.next = new Node<>(payload, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public void appendBeginning(T payload){
        if (isEmpty()){
            head = tail = new Node<>(payload, null, null);
        }
        else{
            head.prev = new Node<>(payload, null, head);
            head = head.prev;
        }
        size++;
    }

    public void appendAtIndex(T payload, int index) throws IndexOutOfBoundsException{
        if (isEmpty()){
            appendEnd(payload);
            return;
        }
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index is out of bounds for list of size: " + size);
        }
        if (index == 0){
            appendBeginning(payload);
            return;
        }
        if (index == size - 1){
            appendEnd(payload);
            return;
        }

        Node<T> trav = head;
        for (int i = 0; i < index - 1; i++){
            trav = trav.next;
        }
        trav.next = new Node<>(payload, trav, trav.next);
        size++;
    }

    public void removeEnd() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("remove() called on empty list");
        }
        tail = tail.prev;
        size--;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }
    }

    public void removeBeginning() throws RuntimeException{
        if (isEmpty()){
            throw new RuntimeException("remove() called on empty list");
        }
        head = head.next;
        size--;

        if (isEmpty()){
            tail = null;
        }
        else {
            head.prev = null;
        }
    }

    public void removeIndex(int index) throws RuntimeException{
        if (isEmpty()){
            throw new RuntimeException("remove() called on empty list");
        }
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index out of bounds for list of size: " + size);
        }
        if (index == 0){
            removeBeginning();
            return;
        }
        if (index == size - 1){
            removeEnd();
            return;
        }
        Node<T> trav = head;
        for (int i = 0; i < index; i++){
            trav = trav.next;
        }
        removeNode(trav);
    }

    // Non modifier methods
    public boolean isEmpty(){
        return size == 0;
    }

    public boolean objectExistence(T payload){
        Node<T> trav = head;
        boolean exists = false;
        while (trav != null){
            if (trav.data.toString().equals(payload.toString())){
                exists = true;
                break;
            }
            trav = trav.next;
        }
        return exists;
    }

    public int getObjectIndex(T payload) throws IndexOutOfBoundsException{
        if (!objectExistence(payload)){
            throw new IndexOutOfBoundsException();
        }
        Node<T> trav = head;
        int index = 0;
        int counter = 0;

        while (trav != null){
            if (trav.data.toString().equals(payload.toString())){
                index = counter;
                break;
            }
            trav = trav.next;
            counter++;
        }
        return index;
    }

    public T getHeadObject() throws RuntimeException{
        if (isEmpty()){
            throw new RuntimeException("get() called on empty list");
        }
        return head.data;
    }

    public T getTailObject() throws RuntimeException{
        if (isEmpty()){
            throw new RuntimeException("get() called on empty list");
        }
        return tail.data;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node<T> trav = head;
        while (trav != null){
            builder.append(trav.data).append(", ");
            trav = trav.next;
        }
        builder.append("]");

        return builder.toString();
    }
}
