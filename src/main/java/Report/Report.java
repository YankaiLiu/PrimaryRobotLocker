package Report;

import java.util.List;

public class Report {
    public List<RebotInfo> rebotInfos;
    public List<RebotMangerInfo> rebotMangerInfos;
    public List<LockerInfo> lockerInfos;

    public Report(List<RebotInfo> rebotInfos, List<RebotMangerInfo> rebotMangerInfos, List<LockerInfo> lockerInfos) {
        this.rebotInfos = rebotInfos;
        this.rebotMangerInfos = rebotMangerInfos;
        this.lockerInfos = lockerInfos;
    }

    @Override
    public String toString() {
        String result = "";
        // locker info
        if (lockerInfos != null) {
            for (LockerInfo lockerin : lockerInfos) {
                result += String.format("L:    %d    %d\n", lockerin.capacity, lockerin.leftCapacity);
            }
        }
        // rebot info
        if (rebotInfos != null) {
            for (RebotInfo rebotInfo : rebotInfos) {
                result += String.format("R:    %d    %d\n", rebotInfo.capacity, rebotInfo.leftCapacity);
                for (LockerInfo lockerInfo : rebotInfo.lockerInfos) {
                    result += String.format("     L:    %d    %d\n", lockerInfo.leftCapacity, lockerInfo.capacity);
                }
            }
        }
        // manager info
        if (rebotMangerInfos != null) {
            for (RebotMangerInfo mangerInfo : rebotMangerInfos) {
                result += String.format("M:    %d    %d\n", mangerInfo.capacity, mangerInfo.leftCapacity);
                for (LockerInfo lockerInfo : mangerInfo.lockerInfos) {
                    result += String.format("     L:    %d    %d\n", lockerInfo.capacity, lockerInfo.leftCapacity);
                }
                for (RebotInfo rebotInfo : mangerInfo.rebotInfos) {
                    result += String.format("     R:    %d    %d\n", rebotInfo.capacity, rebotInfo.leftCapacity);
                    for (LockerInfo lockerInfo : rebotInfo.lockerInfos) {
                        result+=String.format("         L:    %d    %d\n",lockerInfo.capacity,lockerInfo.leftCapacity);
                    }
                }

            }
        }

        return result.substring(0,result.length()-1);
    }

}
