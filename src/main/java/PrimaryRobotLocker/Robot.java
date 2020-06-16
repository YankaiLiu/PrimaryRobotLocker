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
            Ticket ticket =  locker.store(bag, i + 1);
            if (ticket == null) { continue; }
            return ticket;
        }
        throw new PrimaryRobotLockerException("all locker has no capacity");
    }

    public Bag pickUp(Ticket ticket) throws PrimaryRobotLockerException {
        for (Locker locker : LockerList) {
            return locker.getBag(ticket);
        }
        return null;
    }
}
