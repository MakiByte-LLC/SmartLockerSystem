package com.maqi.lockersystem.models;

public class Locker {
    private int id;
    private int size;
    private Package storedPackage;  // null 表示未占用

    public Locker(int id, int size) {
        this.id = id;
        this.size = size;
        this.storedPackage = null;
    }

    public boolean isAvailable() {
        return storedPackage == null;
    }

    public boolean canFit(Package p) {
        return isAvailable() && this.size >= p.getSize();
    }

    public boolean storePackage(Package p) {
        if (canFit(p)) {
            this.storedPackage = p;
            return true;
        }
        return false;
    }

    public Package retrievePackage() {
        Package temp = this.storedPackage;
        this.storedPackage = null;
        return temp;
    }

    public int getSize() { return size; }
    public int getId() { return id; }
}
