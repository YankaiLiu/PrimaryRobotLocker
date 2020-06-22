package PrimaryLockerRobot;

import PrimaryLockerRobot.Exception.ExceptionMessages;
import PrimaryLockerRobot.Exception.PrimaryLockerRobotException;

import java.util.List;

public class SmartRobot extends Robot{
    public SmartRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket store(Bag bag) throws PrimaryLockerRobotException {

        Locker savedLocker = lockers.get(0);
        for (int i = 0; i < lockers.size(); i++) {
            Locker locke = lockers.get(i);
            if(savedLocker.getCapacity() < locke.getCapacity()){
                savedLocker = locke;
            }
        }
        Ticket ticket = savedLocker.store(bag,lockers.indexOf(savedLocker)+1);
        if(ticket==null) {throw new PrimaryLockerRobotException(ExceptionMessages.NO_CAPACITY);}
        return ticket;
    }
}
