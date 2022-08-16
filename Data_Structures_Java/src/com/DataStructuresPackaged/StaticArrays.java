package com.DataStructuresPackaged;


// Fixed length array indexable from 0 -> n - 1
// Used For storing sequential data, temporary data, buffers

public class StaticArrays <T> {
    T[] static_array;

    @SuppressWarnings("all")
    public StaticArrays(T[] array){
        static_array = (T[]) new Object[array.length];

        for (int x = 0; x < array.length; x++){
            static_array[x] = array[x];
        }
    }

    public int arrayLength(){
        return static_array.length;
    }

    public boolean elementExistence(T element){
        boolean element_exist = false;

        for (var x : static_array){
            if (x.equals(element)) {
                element_exist = true;
                break;
            }
        }

        return element_exist;
    }

    @Override
    public String toString(){
        StringBuilder s_build = new StringBuilder();
        for (var x : static_array){
            s_build.append(x);
        }

        return s_build.toString();
    }
}
