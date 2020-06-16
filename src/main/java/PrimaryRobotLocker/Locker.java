package PrimaryRobotLocker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

public class Locker {

    private int Capacity;
    private Map<Ticket, Bag> Bags = new HashMap<>();
    private Set<Ticket> oldTickets = new HashSet<>();

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

        if (oldTickets.contains(ticket)) {
            throw new PrimaryRobotLockerException("this ticket has been used");
        }

        Bag bag = Bags.get(ticket);
        if (bag != null) {
            Bags.remove(ticket);
            oldTickets.add(ticket);
            return bag;
        }

        throw new PrimaryRobotLockerException("invalid ticket");
    }
}
