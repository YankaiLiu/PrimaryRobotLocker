package PrimaryLockerRobot;

import java.util.HashMap;
import java.util.Map;

public class Locker {

    private int capacity;
    private Map<Ticket, Bag> bags = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public Ticket store(Bag bag, int position) {
        if (bags.size() == capacity) { return null; }
        Ticket ticket = new Ticket(position);
        bags.put(ticket, bag);
        return ticket;
    }

    public Bag getBag(Ticket ticket) {
        return bags.get(ticket);
    }
}
