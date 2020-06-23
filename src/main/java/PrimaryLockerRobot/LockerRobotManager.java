package PrimaryLockerRobot;

import PrimaryLockerRobot.Exception.ExceptionMessages;
import PrimaryLockerRobot.Exception.LockerRobotManagerException;
import PrimaryLockerRobot.Exception.PrimaryLockerRobotException;

import java.util.HashMap;
import java.util.List;

public class LockerRobotManager extends Robot {

    static class Management {

        enum Owner {
            robot,
            locker,
        }

        int position;
        Owner owner;

        Management(Owner owner, int position) {
            this.owner = owner;
            this.position = position;
        }
    }

    private List<Robot> robots;
    private HashMap<Ticket, Robot> management = new HashMap<>();
    private HashMap<Ticket, Locker> lockerManagement = new HashMap<>();

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
                Locker locker = lockers.get(ticket.getPosition() - 1);
                lockerManagement.put(ticket, locker);
                return ticket;
            } catch (PrimaryLockerRobotException e) {
                throw new LockerRobotManagerException(e.getMessage());
            }
        } else {
            return ticket;
        }
    }

    @Override
    public Bag pickUp(Ticket ticket) throws LockerRobotManagerException {

        for (Robot robot : robots) {
            try {
                Bag bag =  robot.pickUp(ticket);
                if (bag == null) { continue; }
                oldTickets.add(ticket);
                return bag;
            } catch (PrimaryLockerRobotException e) {
                continue;
            }
        }

        try {
            return super.pickUp(ticket);
        } catch (PrimaryLockerRobotException e) {
            throw new LockerRobotManagerException(e.getMessage());
        }

    }

    public Management managedBy(Ticket ticket) throws LockerRobotManagerException {
        int indexOfRobot =  robots.indexOf(management.get(ticket));
        if (indexOfRobot >= 0) {
            //find
            return new Management(Management.Owner.robot, indexOfRobot + 1);
        }

        if (lockerManagement.get(ticket) != null) {
            return new Management(Management.Owner.locker, ticket.getPosition());
        }

        throw new LockerRobotManagerException(ExceptionMessages.NO_VALID_OWNER);
    }
}

