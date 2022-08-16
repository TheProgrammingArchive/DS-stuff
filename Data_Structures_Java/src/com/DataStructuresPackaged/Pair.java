package com.DataStructuresPackaged;

/**
 * A container that holds to objects of similar to different types. Similar to tuple but contains stores only 2 objects
 * @param <E> Type of object 1
 * @param <T> Type of object 2
 */
public class Pair <E, T>{
    public E objectE;
    public T objectT;

    public Pair(E objectE, T objectT){
        this.objectE = objectE;
        this.objectT = objectT;
    }

    @Override
    public String toString(){
        return "(" + objectE + ", " + objectT + ")";
    }
}
