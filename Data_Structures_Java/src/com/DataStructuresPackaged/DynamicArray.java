package com.DataStructuresPackaged;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class DynamicArray<T> {
    private T[] array_;  // Array of T[] for class Dynamic Array
    int array_size = 0;  // Actual size
    int user_len = 0;    // Perceived length

    private T[] changeArraySize(T[] array) throws IllegalArgumentException{
        T[] temporary_array = (T[]) new Object[array_size + 5];    // Resize array to hold 10 elements if final node is reached
        System.arraycopy(array_, 0, temporary_array, 0, array_.length);

        array_ = temporary_array;
        array_size = array_.length;

        return array_;
    }

    private DynamicArray(int instantiate_specific_size) throws ArrayIndexOutOfBoundsException{   // Private Constructor overload to initialise array of size when given. User denied access
        if (instantiate_specific_size < 0){
            throw new ArrayIndexOutOfBoundsException();
        }

        array_ = (T[]) new Object[instantiate_specific_size];
        array_size = array_.length;
    }

    public DynamicArray(T[] external_array){    // Constructor overload for external array instantiation
        this(external_array.length);
        for (var x : external_array){
            append(x);                          // Iterate and append all elements of external list
        }
    }

    public DynamicArray(){  // Static array of 5 to be instantiated during def constructor creation
        this(5);
    }

    // Modifier Methods
    public void append(T object){
        if (array_size == user_len){           // If obs length reaches stack limit resize array to n + 5
            array_ = changeArraySize(array_);
        }
        array_[user_len] = object;             // Assign Type object to observed final length
        user_len++;                            // Increment observed length
    }

    public void setIndex(int index, T object) throws ArrayIndexOutOfBoundsException{   // Set index[n] where n belongs to [0, size - 1]
        if (index >= array_.length){
            throw new ArrayIndexOutOfBoundsException();                                // Throw exception if index is out of bounds
        }

        else {
            array_[index] = object;                                                    // Set index
        }
    }

    public void removeEnd(){                                                           // Sets observed length to null
        array_[user_len] = null;
        user_len--;                                                                    // Observed length reduced
    }

    public void removeObjectAtIndex(int index) throws IllegalArgumentException{        // Removes object of index n where n belongs to [0, size - 1]
        if (index < 0 || index >= array_.length){
            throw new IllegalArgumentException();                                      // Index validation
        }

        else{
            T[] array_start = Arrays.copyOfRange(array_, 0, index);
            T[] array_end = Arrays.copyOfRange(array_, index + 1, array_.length);

            System.arraycopy(array_start, 0, array_, 0, array_start.length);
            System.arraycopy(array_end, 0, array_, array_start.length, array_end.length);

            user_len--;
        }
    }

    public void removeObject(T object) throws ArrayIndexOutOfBoundsException{
        int object_index = objectIndex(object);
        removeObjectAtIndex(object_index);
    }

    public void eraseArray(){
        array_ = null;
        array_ = (T[]) new Object[1];
        user_len = 0;
        array_size = array_.length;
    }

    // Non Modifier Methods

    public int objectIndex(T object) throws ArrayIndexOutOfBoundsException{
        int pos_index = -1;

        for (int i = 0; i < user_len; i++){
            if (array_[i].equals(object)){
                pos_index = i;
                break;
            }
        }

        if (pos_index == -1){
            throw new ArrayIndexOutOfBoundsException();
        }

        else{
            return pos_index;
        }
    }

    public boolean isEmpty(){
        return user_len == 0;
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append("[");
        for (int i = 0; i < user_len; i++){
            string.append(array_[i]).append(", ");
        }
        string.append("]");

        return string.toString();
    }

    // Debug
    public String getArrayActualSize(){
        return array_.length + ":" + array_size;
    }

}
