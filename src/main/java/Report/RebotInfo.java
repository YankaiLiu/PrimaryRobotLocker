package Report;

import java.util.List;

public class RebotInfo extends LockerInfo{
    public List<LockerInfo> lockerInfos;
    public RebotInfo(int cap,int leftCap,List<LockerInfo> lockerInfos){
        super(cap,leftCap);
        this.lockerInfos = lockerInfos;
    }
}
