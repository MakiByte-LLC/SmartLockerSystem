package com.maqi.lockersystem.models;

public class Package {
    private int id;
    private int size;

    public Package(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() { return id; }
    public int getSize() { return size; }
}
