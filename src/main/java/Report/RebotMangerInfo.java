package Report;

import java.util.List;

public class RebotMangerInfo extends LockerInfo {
    public List<LockerInfo> lockerInfos;
    public List<RebotInfo> rebotInfos;

    public RebotMangerInfo(int cap, int leftCap, List<LockerInfo> lockerInfos, List<RebotInfo> rebotInfos) {
        super(cap, leftCap);
        this.lockerInfos = lockerInfos;
        this.rebotInfos = rebotInfos;
    }
}
