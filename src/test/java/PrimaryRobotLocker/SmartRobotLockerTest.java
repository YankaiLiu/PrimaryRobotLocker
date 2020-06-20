package PrimaryRobotLocker;

/*
given 2个locker,第一个剩余空间大于第二个,when smartLockerRobot存包 then包被存入第一个locker,返回票 -------done
given 2个locker, 2个剩余空间相同，when smartLockerRobot存包 then包被存入第一个locker，返回票 -------done
given 2个locker, 第2个剩余空间大于第一个，when smartLockerRobot存包 then包被存入第2个locker，返回票 ------done
given 2个locker , 没有剩余空间， when smartLockerRobot存包 then存包失败,提示存包失败
given 1个有效票据； when smartLockerRobot取包, then取包成功, 票据回收
given 1个无效票据； when smartLockerRobot取包, then取包失败，提示票据无效
given primaryLockerRobot和smartLockerRobot同时管理locker，提供1个smartLockerRobot的存包票；when primaryLockerRobot取包；then取包成功
given primaryLockerRobot和smartLockerRobot同时管理locker，提供1个primaryLockerRobot的存包票；when smartLockerRobot取包；then取包成功
 */

import PrimaryRobotLocker.Exception.ExceptionMessages;
import PrimaryRobotLocker.Exception.PrimaryRobotLockerException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class SmartRobotLockerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_return_a_ticket_given_2_lockers_and_the_first_has_more_capacity_than_2nd_when_smartRobot_save_bag() throws PrimaryRobotLockerException {
        Locker locker1 = new Locker(10);
        Locker locker2 = new Locker(2);
        SmartRobot smartRobot = new SmartRobot(Arrays.asList(locker1,locker2));
        Ticket ticket = smartRobot.store(new Bag());
        Assert.assertEquals(1,ticket.getPosition());
    }

    @Test
    public void should_return_a_ticket_given_2_lockers_and_the_1st_has_same_capacity_with_2nd_when_smartRobot_save_bag() throws PrimaryRobotLockerException {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        SmartRobot smartRobot = new SmartRobot(Arrays.asList(locker1,locker2));
        Ticket ticket = smartRobot.store(new Bag());
        Assert.assertEquals(1,ticket.getPosition());
    }

    @Test
    public void should_return_a_ticket_given_2_lockers_and_the_first_has_less_capacity_than_2nd_when_smartRobot_save_bag() throws PrimaryRobotLockerException {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(10);
        SmartRobot smartRobot = new SmartRobot(Arrays.asList(locker1,locker2));
        Ticket ticket = smartRobot.store(new Bag());
        Assert.assertEquals(2,ticket.getPosition());
    }

    @Test
    public void should_return_a_ticket_given_2_lockers_and_the_1st_and_2nd_has_no_capacity_when_smartRobot_save_bag() throws PrimaryRobotLockerException {
        thrown.expect(PrimaryRobotLockerException.class);
        thrown.expectMessage(ExceptionMessages.NO_CAPACITY);

        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(0);
        SmartRobot smartRobot = new SmartRobot(Arrays.asList(locker1,locker2));
        smartRobot.store(new Bag());
    }

}
