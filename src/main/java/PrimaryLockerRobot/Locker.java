package PrimaryLockerRobot;

import java.util.HashMap;
import java.util.Map;

public class Locker {

    private int capacity;
    private Map<Ticket, Bag> bags = new HashMap<>();
    private int LeftCapacity;

    public Locker(int capacity) {
        this.capacity = capacity;
        this.LeftCapacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getLeftCapacity() {
        return this.LeftCapacity;
    }

    public Ticket store(Bag bag, int position) {
        if (bags.size() == capacity) {
            return null;
        }
        LeftCapacity -= 1;
        Ticket ticket = new Ticket(position);
        bags.put(ticket, bag);
        return ticket;
    }

    public Bag getBag(Ticket ticket) {
        Bag bag = bags.get(ticket);
        if (bag != null) {
            LeftCapacity += 1;
        }
        return bag;
    }
}
