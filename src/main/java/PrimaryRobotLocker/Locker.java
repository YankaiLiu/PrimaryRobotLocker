package PrimaryRobotLocker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public Bag getBag(Ticket ticket) {
        return Bags.get(ticket);
    }
}
