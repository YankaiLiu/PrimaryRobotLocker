package PrimaryRobotLocker;

import java.util.concurrent.locks.Lock;

public class Locker {

    private int Capacity;

    public Locker(int capacity) {
        Capacity = capacity;
    }

    public Locker() {
        Capacity = 2;
    }
}
