package com.Tests;

import java.util.*;

public class PriorityQueue <T extends Comparable<T>>{
    private List<T> heap;
    private Map<T, TreeSet<Integer>> indexMap = new HashMap<>();

    private int heapCapacity = 0;
    private int heapSize = 0;

    private void mapAdd(T elem, int index){
        TreeSet<Integer> set = indexMap.get(elem);
        if (set == null){
            set = new TreeSet<>();
            set.add(index);
            indexMap.put(elem, set);
        }
        else{
            set.add(index);
        }
    }

    // Comparator
    private boolean less(int x, int y){
        T x_elem = heap.get(x);
        T y_elem = heap.get(y);

        return x_elem.compareTo(y_elem) <= 0;
    }

    private void mapSwap(T x_elem, T y_elem, int x, int y){
        TreeSet<Integer> set1 = indexMap.get(x_elem);
        TreeSet<Integer> set2 = indexMap.get(y_elem);

        set1.remove(x);
        set2.remove(y);
        set1.add(y);
        set2.add(x);
    }

    private void mapRemove(T elem, int index){
        TreeSet<Integer> set = indexMap.get(elem);
        set.remove(index);

        if (set.size() == 0){
            indexMap.remove(elem);
        }
    }

    private void swap(int x, int y){
        T x_elem = heap.get(x);
        T y_elem = heap.get(y);

        heap.set(x, y_elem);
        heap.set(y, x_elem);

        mapSwap(x_elem, y_elem, x, y);
    }

    private void sink(int index){
        while (true){
            int leftNode = 2*index + 1;
            int rightNode = 2*index + 2;
            int smallest = leftNode; // Assumption

            if (rightNode < heapSize || less(rightNode, leftNode)){
                smallest = rightNode;
            }
            if (leftNode > heapSize || less(index, smallest)){
                break;
            }

            swap(smallest, index);
            index = smallest;
        }
    }

    private void swim(int index){
        int parent = (index - 1)/2;

        while (index > 0 && less(index, parent)){
            swap(parent, index);
            index = parent;

            parent = (index - 1)/2;
        }
    }

    private T removeAt(int index){
        if (isEmpty()){
            return null;
        }
        heapSize--;

        T data = heap.get(index);
        swap(index, heapSize);

        heap.set(index, null);
        mapRemove(data, index);

        if (index == heapSize){
            return data;
        }

        T elem = heap.get(index);
        sink(index);

        if (heap.get(index).equals(elem)){
            swim(index);
        }

        return data;
    }

    public PriorityQueue(int size){
        heap = new ArrayList<>(size);
    }

    public PriorityQueue(){
        this(1);
    }

    public PriorityQueue(T[] elemList){
        heapSize = heapCapacity = elemList.length;
        heap = new ArrayList<>(heapSize);

        for (int i = 0; i < elemList.length; i++){
            heap.add(elemList[i]);
            mapAdd(elemList[i], i);
        }

        // Heapify
        for (int i = Math.max(0, (heapSize/2)-1); i >= 0; i--){
            sink(i);
        }
    }

    public boolean isEmpty(){
        return heapSize == 0;
    }

    public void add(T elem){
        if (heapSize < heapCapacity){
            heap.set(heapSize, elem);
        }
        else{
            heap.add(elem);
            heapCapacity++;
        }

        mapAdd(elem, heapSize);
        swim(heapSize);
        heapSize++;
    }

    public boolean contains(T elem){
        return indexMap.containsKey(elem);
    }

    public T poll(){
        if (isEmpty()){
            return null;
        }

        return removeAt(0);
    }

    public T remove(T elem){
        if (isEmpty()){
            return null;
        }
        else{
            return removeAt(9);
        }
    }

}
