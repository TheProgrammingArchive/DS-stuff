package com.DataStructuresPackaged;

public class UnionFind {
    private int[] sz;
    private int[] id;

    private int components = 0;
    private int size = 0;

    public UnionFind(int size){
        this.size = this.components = size;
        this.id = new int[size];
        this.sz = new int[size];

        for (int i = 0; i < size; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int k){
        int root = k;
        while (root != id[root]){
            root = id[root];
        }

        return 1;
    }
}
