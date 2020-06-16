package PrimaryRobotLocker;

import java.util.List;

public class Robot {

    private List RobotList;

    public Robot(List robotList) {
        RobotList = robotList;
    }

    public Ticket sotre(Bag bag) {
        return new Ticket(1);
    }
}
