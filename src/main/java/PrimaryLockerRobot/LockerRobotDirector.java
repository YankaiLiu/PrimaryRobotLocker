package PrimaryLockerRobot;

import Report.LockerInfo;
import Report.Report;
import Report.RebotInfo;
import Report.RebotMangerInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LockerRobotDirector extends LockerRobotManager {
    private List<LockerRobotManager> RobotManagers;

    public LockerRobotDirector(List<LockerRobotManager> robotManagers) {
        super(Arrays.asList(), Arrays.asList()); //locker rebot
        this.RobotManagers = robotManagers;
    }

    //manager =>robot
//    =>locker
//
    protected Report directorReport() {
        // new Report();//rebotInfos managerInfos lockerInfos
        //lockerInfos
        List<LockerInfo> lockerInfos = new ArrayList<LockerInfo>();
        for (Locker locker : this.lockers) {
            lockerInfos.add(new LockerInfo(locker.getCapacity(), locker.getLeftCapacity()));
        }
        //rebotInfos
        List<RebotInfo> rebotInfos = new ArrayList<RebotInfo>();
        for (Robot robot : this.robots) {
            List<LockerInfo> rebotLockerInfos = new ArrayList<LockerInfo>();
            int robotCap = robot.getCapacity();
            int robotLeftCap = robot.getLeftCapacity();
            for (Locker locker : robot.lockers) {
                int lockCap = locker.getCapacity();
                int lockLeftCap = locker.getLeftCapacity();
                rebotLockerInfos.add(new LockerInfo(lockCap, lockLeftCap));
            }
            rebotInfos.add(new RebotInfo(robotCap, robotLeftCap, rebotLockerInfos));
        }

        //reportMangerInfos
        List<RebotMangerInfo> mangerInfos = new ArrayList<RebotMangerInfo>();
        for (LockerRobotManager manager : this.RobotManagers) {
            List<LockerInfo> managerLockerInfos = new ArrayList<LockerInfo>();
            List<RebotInfo> managerRebotInfos = new ArrayList<RebotInfo>();

            int managerCap = manager.getCapacity();
            int managerLeftCap = manager.getLeftCapacity();

            for (Locker managerLocker : manager.lockers) { // manger 管理的locker
                int lockCap = managerLocker.getCapacity();
                int lockLeftCap = managerLocker.getLeftCapacity();
                managerLockerInfos.add(new LockerInfo(lockCap, lockLeftCap));
            }

            for (Robot managerRobot : manager.robots) { // manger 管理的robot
                List<LockerInfo> tmpLockInfos = new ArrayList<LockerInfo>();
                int managerRobotCapacity = managerRobot.getCapacity();
                int managerRobotLeftCapacity = managerRobot.getLeftCapacity();
                for(Locker locker:managerRobot.lockers){
                    int cap = locker.getCapacity();
                    int leftCap = locker.getLeftCapacity();
                    tmpLockInfos.add(new LockerInfo(cap,leftCap));
                }
                managerRebotInfos.add(new RebotInfo(managerRobotCapacity, managerRobotLeftCapacity, tmpLockInfos));
            }

            mangerInfos.add(new RebotMangerInfo(managerCap,managerLeftCap,managerLockerInfos,managerRebotInfos));
        }


        return new Report(rebotInfos, mangerInfos, lockerInfos);
    }

    public String report() {
        return directorReport().toString();
    }
}
