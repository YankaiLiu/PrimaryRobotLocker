package PrimaryRobotLocker;

import java.util.List;

public class Robot {

    private List<Locker> LockerList;

    public Robot(List<Locker> lockerList) {
        LockerList = lockerList;
    }

    public Ticket store(Bag bag) throws PrimaryRobotLockerException {

        for (int i = 0; i < LockerList.size(); i++) {
            Locker locker = LockerList.get(i);
            if (locker.getCapacity() > 0) {
                return new Ticket(i + 1);
            }
        }
        throw new PrimaryRobotLockerException("all locker has no capacity");
    }

}
