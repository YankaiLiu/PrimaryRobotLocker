package PrimaryRobotLocker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

public class Locker {

    private int Capacity;
    private Map<Ticket, Bag> Bags = new HashMap<>();

    public Locker(int capacity) {
        Capacity = capacity;
    }

    public Locker() {
        Capacity = 2;
    }

    public int getCapacity() {
        return Capacity;
    }

    public Ticket store(Bag bag, int position) {
        if (Bags.size() == Capacity) { return null; }
        Ticket ticket = new Ticket(position);
        Bags.put(ticket, bag);
        return ticket;
    }

    public Bag getBag(Ticket ticket) throws PrimaryRobotLockerException {
        Bag bag = Bags.get(ticket);
        if (bag != null) {
            Bags.remove(ticket);
            return bag;
        }
        throw new PrimaryRobotLockerException("invalid ticket");
    }
}
