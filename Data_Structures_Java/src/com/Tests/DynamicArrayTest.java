package com.Tests;

import java.util.Arrays;
import java.util.Iterator;

public class DynamicArrayTest<T>{
    private T[] dynamic_array_holder;
    private int array_size = 0;
    private int user_len = 0;

    @SuppressWarnings("unchecked")
    private T[] reshapeArray(T[] array, int operation){ // 0 -> Reduce size, 1 -> Increase Size
        assert (operation == 0 || operation == 1);

        if (operation == 0){
            ;
        }

        else {
            T[] temporary_array = (T[]) new Object[dynamic_array_holder.length + 5];
            System.arraycopy(dynamic_array_holder, 0, temporary_array, 0, dynamic_array_holder.length);

            dynamic_array_holder = temporary_array;

            array_size = dynamic_array_holder.length;
        }

        return dynamic_array_holder;
    }

    public DynamicArrayTest(T[] array_){
        this(array_.length);
        System.arraycopy(array_, 0, dynamic_array_holder, 0, array_.length);
    }

    public DynamicArrayTest(){
        this(5);
    }

    @SuppressWarnings("unchecked")
    public DynamicArrayTest(int size_reqd) throws IndexOutOfBoundsException{
        if (size_reqd < 0){
            throw new IndexOutOfBoundsException();
        }
        else {
            dynamic_array_holder = (T[]) new Object[size_reqd];
            array_size = dynamic_array_holder.length;
        }
    }

    public void appendEnd(T object_to_append){
        if (user_len == array_size){
            dynamic_array_holder = reshapeArray(dynamic_array_holder, 1);
            dynamic_array_holder[user_len] = object_to_append;

            user_len = user_len + 1;
        }

        else{
            dynamic_array_holder[user_len] = object_to_append;

            user_len = user_len + 1;
        }
    }

    public void removeEnd(){
        dynamic_array_holder[user_len - 1] = null;
        user_len = user_len - 1;
    }

    public void removeObject(T object){

    }


    @Override
    public String toString(){
        StringBuilder new_builder = new StringBuilder();
        new_builder.append("[");

        for (int i = 0; i < user_len; i++){
            new_builder.append(dynamic_array_holder[i]).append(", ");
        }

        new_builder.append("]");

        return new_builder.toString();
    }
}
