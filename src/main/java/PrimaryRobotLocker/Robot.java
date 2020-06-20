package PrimaryRobotLocker;

import PrimaryRobotLocker.Exception.ExceptionMessages;
import PrimaryRobotLocker.Exception.PrimaryRobotLockerException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Robot {

    private List<Locker> LockerList;
    private Set<Ticket> oldTickets = new HashSet<>();

    public Robot(List<Locker> lockerList) {
        LockerList = lockerList;
    }

    public Ticket store(Bag bag) throws PrimaryRobotLockerException {

        for (int i = 0; i < LockerList.size(); i++) {
            Locker locker = LockerList.get(i);
            Ticket ticket =  locker.store(bag, i + 1);
            if (ticket != null) { return ticket; }
        }
        throw new PrimaryRobotLockerException(ExceptionMessages.NO_CAPACITY);
    }

    public Bag pickUp(Ticket ticket) throws PrimaryRobotLockerException {
        if (oldTickets.contains(ticket)) {
            throw new PrimaryRobotLockerException(ExceptionMessages.TICKET_HAS_BEEN_USED);
        }
        for (Locker locker : LockerList) {
            Bag bag = locker.getBag(ticket);
            if (bag == null) { continue; }
            oldTickets.add(ticket);
            return bag;
        }
        throw new PrimaryRobotLockerException(ExceptionMessages.INVALID_TICKET);
    }
}
