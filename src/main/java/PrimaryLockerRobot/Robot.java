package PrimaryLockerRobot;

import PrimaryLockerRobot.Exception.ExceptionMessages;
import PrimaryLockerRobot.Exception.PrimaryLockerRobotException;
import PrimaryLockerRobot.Exception.SmartLockerRobotException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Robot {

    protected List<Locker> lockers;
    protected Set<Ticket> oldTickets = new HashSet<>();

    public int getCapacity() {
        int result = 0;
        for (Locker locker : lockers) {
            result += locker.getCapacity();
        }
        return result;
    }

    public int getLeftCapacity(){
        int result = 0;
        for (Locker locker : lockers) {
            result += locker.getLeftCapacity();
        }
        return result;
    }

    public Robot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) throws PrimaryLockerRobotException {

        for (int i = 0; i < lockers.size(); i++) {
            Locker locker = lockers.get(i);
            Ticket ticket = locker.store(bag, i + 1);
            if (ticket != null) {
                return ticket;
            }
        }
        throw new PrimaryLockerRobotException(ExceptionMessages.NO_CAPACITY);
    }

    public Bag pickUp(Ticket ticket) throws PrimaryLockerRobotException {
        if (oldTickets.contains(ticket)) {
            throw new PrimaryLockerRobotException(ExceptionMessages.TICKET_HAS_BEEN_USED);
        }
        for (Locker locker : lockers) {
            Bag bag = locker.getBag(ticket);
            if (bag == null) {
                continue;
            }
            oldTickets.add(ticket);
            return bag;
        }
        throw new PrimaryLockerRobotException(ExceptionMessages.INVALID_TICKET);
    }
}
