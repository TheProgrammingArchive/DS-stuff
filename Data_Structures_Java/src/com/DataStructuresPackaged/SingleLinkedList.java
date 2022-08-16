package com.DataStructuresPackaged;

public class SingleLinkedList <T>{
    private int size = 0;                   // LL size
    private Node<T> head = null;            // First node

    private static class Node<T>{           // Private node class
        private Node<T> next;               // Points to next node
        T data;                             // Data

        public Node(T data, Node<T> next){  // Constructor
            this.data = data;
            this.next = next;
        }

        @Override                           // Override toString to buffer outpu
        public String toString(){
            return data.toString();
        }
    }

    // Optional Constructor
    public SingleLinkedList(T[] external_arr){
        for (var x : external_arr){
            append(x);
        }
    }

    public SingleLinkedList(){};

    // Modifier Methods
    public void append(T data){             // Append Last
        if (isEmpty()){                     // If list is empty point head to the new Node
            head = new Node<>(data, null);  // Instantiate new node object and assign head to it
        }

        else{                               // If not empty
            Node<T> trav = head;            // Traverse Node created that begins from head
            while (trav.next != null){      // The last node is null. Traverse till trav becomes null
                trav = trav.next;
            }

            trav.next = new Node<>(data, null); // Point trav to the new node instead of null
        }
        size++;                             // Append size
    }

    public void insertAtBeginning(T data){  // Append at Beginning
        if (isEmpty()){                     // Refer above
            head = new Node<>(data, null);
        }

        else {
            Node<T> to_insert = new Node<>(data, head); // to_insert object created with data and points to the head original head
            head = to_insert;                           // First node is now this
        }
        size++;                                         // Size append
    }

    public void insertAtIndex(T data, int index) throws IndexOutOfBoundsException{
        if (isEmpty()){                                 // Refer above
            append(data);
            return;
        }

        if (index < 0 || index >= size){                // Throwing exception on illegal argument
            throw new IndexOutOfBoundsException("Last index: " + size--);
        }

        if (index == 0){                                // If spec index is 0 append at beginning
            insertAtBeginning(data);
            return;
        }

        else if (index == size - 1){                    // If spec index is last append at last
            append(data);
            return;
        }

        Node<T> trav = head;                            // If none of the above create traverser which will traverse till the previous node of required node
        int iterator;

        for (iterator = 0; iterator < index - 1; iterator++){
            trav = trav.next;                           // Keep changing trav till index - 1
        }

        trav.next = new Node<>(data, trav.next);        // Point trav next element to the node. The node next will point to trav.next.next
        size++;
    }

    public void removeEnd() throws IndexOutOfBoundsException{
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }

        Node<T> trav = head;
        for (int i = 0; i < size - 2; i++){
            trav = trav.next;
        }
        trav.next = null;
        size--;

        if (isEmpty()){
            head = null;
        }
    }

    public void removeFirstIndex() throws IndexOutOfBoundsException{
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }

        Node<T> holder = head;
        head = null;
        head = holder.next;
        size--;

        if (isEmpty()){
            head = null;
        }
    }
    
    public void removeElementAtIndex(int index) throws IndexOutOfBoundsException{
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }

        if (index == 0){
            removeFirstIndex();
            return;
        }

        if (index == size - 1){
            removeEnd();
            return;
        }

        Node<T> trav = head;
        for (int i = 0; i < index - 1; i++){
            trav = trav.next;
        }
        trav.next = trav.next.next;
        size--;

        if (isEmpty()){
            head = null;
        }
    }

    public void removeObject(T data) throws IndexOutOfBoundsException{
        int index = getObjectIndex(data);

        removeElementAtIndex(index);
    }

    // Non Modifier
    public int getObjectIndex(T data) throws IndexOutOfBoundsException{
        Node<T> trav = head;
        int index = -1;

        int counter = 0;
        while (trav != null){
            if (trav.toString().equals(data.toString())){
                index = counter;
            }
            trav = trav.next;
            counter++;
        }

        if (index == -1){
            throw new IndexOutOfBoundsException();
        }

        return index;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    public T getHead() throws RuntimeException{
        if (isEmpty()){
            throw new RuntimeException();
        }

        return head.data;
    }

    @Override
    public String toString(){
        if (isEmpty()){
            return "[]";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        Node<T> trav = head;
        while (trav != null){
            builder.append(trav).append(", ");
            trav = trav.next;
        }
        builder.append("]");

        return builder.toString();
    }
}
