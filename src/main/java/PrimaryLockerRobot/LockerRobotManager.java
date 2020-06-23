package PrimaryLockerRobot;

import PrimaryLockerRobot.Exception.ExceptionMessages;
import PrimaryLockerRobot.Exception.LockerRobotManagerException;
import PrimaryLockerRobot.Exception.PrimaryLockerRobotException;
import PrimaryLockerRobot.Exception.SmartLockerRobotException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LockerRobotManager extends Robot {

    private List<Robot> robots;
    private HashMap<Ticket, Robot> management = new HashMap<>();

    public LockerRobotManager(List<Locker> lockers, List<Robot> robots) {
        super(lockers);
        this.robots = robots;
    }

    @Override
    public Ticket store(Bag bag) throws LockerRobotManagerException {

        Ticket ticket = null;
        if (robots != null) {
            for (Robot robot : robots) {
                try {
                    ticket = robot.store(bag);
                    management.put(ticket, robot);
                    break;
                } catch (PrimaryLockerRobotException e) {
                    continue;
                }
            }
        }

        if (ticket == null) {
            try {
                ticket = super.store(bag);
                return ticket;
            } catch (PrimaryLockerRobotException e) {
                throw new LockerRobotManagerException(e.getMessage());
            }
        } else {
            return ticket;
        }
    }

    public int managedBy(Ticket ticket) {
        return robots.indexOf(management.get(ticket)) + 1;
    }
}
