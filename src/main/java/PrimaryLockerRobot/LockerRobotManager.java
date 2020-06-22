package PrimaryLockerRobot;

import PrimaryLockerRobot.Exception.LockerRobotManagerException;
import PrimaryLockerRobot.Exception.PrimaryLockerRobotException;
import PrimaryLockerRobot.Exception.SmartLockerRobotException;

import java.util.List;

public class LockerRobotManager extends Robot {
    public LockerRobotManager(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket store(Bag bag) throws LockerRobotManagerException {
        try {
            Ticket ticket = super.store(bag);
            return  ticket;
        } catch (PrimaryLockerRobotException e) {
            throw new LockerRobotManagerException(e.getMessage());
        }
    }
}
