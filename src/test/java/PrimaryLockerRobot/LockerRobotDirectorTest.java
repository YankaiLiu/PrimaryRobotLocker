package PrimaryLockerRobot;

/*
*   Tssking
* Task1:
- Given: LockerRobotDirector 管理一个 LockerRobotManager, LockerRobotManager 管理一个 Locker 总容量为2，可用容量为1, 不管理 Robot
- When: LockerRobotDirector 查看报表
- Then: 返回报表
    M 1 2
        L 1 2

Task2:
- Given: LockerRobotDirector 管理一个 LockerRobotManager, LockerRobotManager 管理两个 Locker ，每个总容量为5，可用容量为2, 不管理 Robot
- When: LockerRobotDirector 查看报表
- Then: 返回报表
    M 4 10
        L 2 5
        L 2 5

Task3:
- Given: LockerRobotDirector 管理一个 LockerRobotManager, LockerRobotManager 管理两个 Locker ，每个总容量为5，可用容量为2, 管理一个 Robot,
    Robot 管理两个 Locker，一个总容量为5，可用容量为3，一个总容量为8，可用容量为5
- When: LockerRobotDirector 查看报表
- Then: 返回报表
    M 12 23
        L 2 5
        L 2 5
        R 8 13
            L 3 5
            L 5 8

Task4:
- Given: LockerRobotDirector 管理一个 LockerRobotManager, LockerRobotManager 管理两个 Locker ，每个总容量为5，可用容量为2, 管理两个 Robot,
    第一个 Robot 管理两个 Locker，一个总容量为5，可用容量为3，一个总容量为8，可用容量为5；
    第二个 Robot 管理两个 Locker，一个总容量为4，可用容量为2，一个总容量为7，可用容量为6。
- When: LockerRobotDirector 查看报表
- Then: 返回报表
    M 20 34
        L 2 5
        L 2 5
        R 8 13
            L 3 5
            L 5 8
        R 8 11
            L 2 4
            L 6 7
Task5:
- Given: LockerRobotDirector 管理一个 LockerRobotManager,
    第一个 LockerRobotManager 管理两个 Locker ，每个总容量为5，可用容量为2, 管理两个 Robot,
        第一个 Robot 管理两个 Locker，一个总容量为5，可用容量为3，一个总容量为8，可用容量为5；
        第二个 Robot 管理两个 Locker，一个总容量为4，可用容量为2，一个总容量为7，可用容量为6。
    第二个 LockerRobotManager 管理两个 Locker ，每个总容量为7，可用容量为4, 管理两个 Robot,
        第一个 Robot 管理两个 Locker，一个总容量为7，可用容量为2，一个总容量为9，可用容量为5；
        第二个 Robot 管理两个 Locker，一个总容量为11，可用容量为2，一个总容量为8，可用容量为6。
- When: LockerRobotDirector 查看报表
- Then: 返回报表
    M 20 34
        L 2 5
        L 2 5
        R 8 13
            L 3 5
            L 5 8
        R 8 11
            L 2 4
            L 6 7
    M 23 49
        L 4 7
        L 4 7
        R 7 16
            L 2 7
            L 5 9
        R 8 19
            L 2 11
            L 6 8
* */

import PrimaryLockerRobot.Exception.LockerRobotManagerException;
import Report.Report;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class LockerRobotDirectorTest {
    @Test
    public void should_return_report_when_director_see_report_given_director_managed_1_manager_and_have_valid_capacity_1_and_total_capacity_2() throws LockerRobotManagerException {
        //given
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(new Locker(2)),Arrays.asList());
        lockerRobotManager.store(new Bag());

        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(Arrays.asList(lockerRobotManager));

        //when
        String report = lockerRobotDirector.report();
        //then
        Assert.assertEquals("M:    2    1\n" + "     L:    2    1",report);
    }

    @Test
    public void should_return_report_when_director_see_report_given_director_managed_1_manager_and_manager_managed_2_lockers_and_twice_of_them_have_total_capaction_is_5_and_valid_capaction_is_2have_valid_capacity_1_and_total_capacity_2() throws LockerRobotManagerException {
        //given
        Locker locker1 = new Locker(5);
        Locker locker2 = new Locker(5);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1,locker2),Arrays.asList());
        for(int i = 0;i<3;i++){
            locker1.store(new Bag(),0);
            locker2.store(new Bag(),1);
        }

        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(Arrays.asList(lockerRobotManager));

        //when
        String report = lockerRobotDirector.report();
        //then
        Assert.assertEquals(
                "M:    10    4\n" + "     L:    5    2\n" + "     L:    5    2",
                report);
    }

    /*
    * Task3:
- Given: LockerRobotDirector 管理一个 LockerRobotManager, LockerRobotManager 管理两个 Locker ，每个总容量为5，可用容量为2, 管理一个 Robot,
    Robot 管理两个 Locker，一个总容量为5，可用容量为3，一个总容量为8，可用容量为5
- When: LockerRobotDirector 查看报表
- Then: 返回报表
    M 12 23
        L 2 5
        L 2 5
        R 8 13
            L 3 5
            L 5 8
    * */
    @Test
    public void should_return_report_when_director_see_report_given_director_managed_1_manager_and_manager_managed_2_lockers_and_1_robot_and_twice_of_locker_have_total_capaction_is_5_and_valid_capaction_is_2_have_valid_capacity_1_and_total_capacity_2_and_robot_have_2_locker_and_them_also_have_5_total_caption_and_2_valid_caption() throws LockerRobotManagerException {
        //given
        Locker locker1 = new Locker(5);
        Locker locker2 = new Locker(5);
        for(int i = 0;i<3;i++){
            locker1.store(new Bag(),0);
            locker2.store(new Bag(),1);
        }

        Locker robotLocker1 = new Locker(5);
        Locker robotLocker2 = new Locker(8);
        for(int i = 0;i <2;i++){
            robotLocker1.store(new Bag(),0);
            robotLocker2.store(new Bag(),1);
        }
        robotLocker2.store(new Bag(),1);
        Robot robot = new Robot(Arrays.asList(robotLocker1,robotLocker2));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1,locker2),Arrays.asList(robot));


        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(Arrays.asList(lockerRobotManager));

        //when
        String report = lockerRobotDirector.report();
        //then
        Assert.assertEquals(
                "M:    23    12\n" +
                        "     L:    5    2\n" +
                        "     L:    5    2\n" +
                        "     R:    13    8\n" +
                        "         L:    5    3\n" +
                        "         L:    8    5",
                report);
    }

}
