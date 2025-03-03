package com.maqi.lockersystem.models;

import java.util.*;

public class LockerSystem {
    private List<Locker> lockers;
    private TreeSet<Integer> availableLockers;  // 存放空闲储物柜的 ID（按 size 排序）
    private Map<Integer, Locker> packageMap;  // packageId -> Locker

    public LockerSystem(int[] lockerSizes) {
        lockers = new ArrayList<>();
        availableLockers = new TreeSet<>();
        packageMap = new HashMap<>();

        for (int i = 0; i < lockerSizes.length; i++) {
            lockers.add(new Locker(i, lockerSizes[i]));
            availableLockers.add(i);  // 初始所有柜子都是空的
        }
        lockers.sort(Comparator.comparingInt(Locker::getSize));  // 按 size 排序
    }

    public boolean storePackage(Package p) {
        // 找到第一个 size >= p.getSize() 且空闲的 locker
        for (int id : availableLockers) {
            Locker locker = lockers.get(id);
            if (locker.canFit(p)) {
                locker.storePackage(p);
                packageMap.put(p.getId(), locker);
                availableLockers.remove(id); // 该储物柜不再可用
                return true;
            }
        }
        return false; // 没有合适的柜子
    }

    public Package retrievePackage(int packageId) {
        if (!packageMap.containsKey(packageId)) {
            return null; // 该包裹不存在
        }

        Locker locker = packageMap.get(packageId);
        Package p = locker.retrievePackage();
        packageMap.remove(packageId);
        availableLockers.add(locker.getId()); // 该柜子重新可用
        return p;
    }

    public List<Locker> getAvailableLockers() {
        List<Locker> result = new ArrayList<>();
        for (int id : availableLockers) {
            result.add(lockers.get(id));
        }
        return result;
    }
}
