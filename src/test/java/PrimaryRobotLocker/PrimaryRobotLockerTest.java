package PrimaryRobotLocker;


/*
 * Given:  Robot 管理两个locker， 两个locker都有可用的容量； When: robot 存包；Then: 成功存入第一个Locker， 返回票据。
 * Given:  Robot 管理两个locker，第一个Locker已存满，第二个Locker有可用容量； When: robot 存包；Then: 成功存入第一个Locker， 返回票据。
 * Given:  Robot 管理两个locker，两个Locker都满了； When: robot 存包；Then: 存包失败，提示储物柜已满
 * Given：Robot 管理两个Locker，有一张无效的票；When：robot 取包； Then：取包失败，提示票据不合法
 * Given：Robot 管理两个Locker，有一张已经取过的票；When：robot 取包； Then：取包失败，提示已取包
 * Given：Robot 管理两个locker，有一张有效的票；When：robot 取包;  Then: 取包成功。
 */

/*
given 2个locker,第一个剩余空间大于第二个,when smartLockerRobot存包 then包被存入第一个locker,返回票

given 2个locker, 2个剩余空间相同，when smartLockerRobot存包 then包被存入第一个locker，返回票

given 2个locker, 第2个剩余空间大于第一个，when smartLockerRobot存包 then包被存入第2个locker，返回票

given 2个locker , 没有剩余空间， when smartLockerRobot存包 then存包失败,提示存包失败

given 1个有效票据； when smartLockerRobot取包, then取包成功

given 1个无效票据； when smartLockerRobot取包, then取包失败，提示票据无效

given 1个使用过票据； when smartLockerRobot取包, then取包失败，提示票据已经使用

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
import java.util.List;


public class PrimaryRobotLockerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_return_ticket_given_a_bag_when_two_locker_have_capacity () throws PrimaryRobotLockerException {
        Bag bag = new Bag();
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(2);
        List lockerList = Arrays.asList(firstLocker, secondLocker);
        Robot robot = new Robot(lockerList);

        Ticket ticket = robot.store(bag);
        Assert.assertNotNull(ticket);
        Assert.assertEquals(ticket.getPosition(), 1);
    }

    @Test
    public void should_return_ticket_and_save_bag_to_second_locker_given_a_bag_when_first_locker_is_full_and_second_locker_have_capacity() throws PrimaryRobotLockerException {
        Bag bag = new Bag();
        Locker firstLocker = new Locker(0);
        Locker secondLocker = new Locker(2);
        List lockerList = Arrays.asList(firstLocker, secondLocker);
        Robot robot = new Robot(lockerList);

        Ticket ticket = robot.store(bag);
        Assert.assertNotNull(ticket);
        Assert.assertEquals(ticket.getPosition(), 2);
    }

    @Test()
    public void should_reminder_lockers_are_full_given_a_bag_when_first_locker_and_second_locker_are_full() throws PrimaryRobotLockerException {

        thrown.expect(PrimaryRobotLockerException.class);
        thrown.expectMessage(ExceptionMessages.NO_CAPACITY);

        Bag bag = new Bag();
        Locker firstLocker = new Locker(0);
        Locker secondLocker = new Locker(0);
        List lockerList = Arrays.asList(firstLocker, secondLocker);
        Robot robot = new Robot(lockerList);
        Ticket ticket = robot.store(bag);
    }

    @Test
    public void should_reminder_invalid_ticket_given_invalid_ticket_when_pick_up_bag() throws PrimaryRobotLockerException {

        thrown.expect(PrimaryRobotLockerException.class);
        thrown.expectMessage(ExceptionMessages.INVALID_TICKET);

        Bag bag = new Bag();
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        List lockerList = Arrays.asList(firstLocker, secondLocker);
        Robot robot = new Robot(lockerList);

        robot.pickUp(new Ticket(0));
    }

    @Test
    public void should_reminder_ticket_has_benn_used_given_used_ticket_when_pick_up_bag() throws PrimaryRobotLockerException {

        thrown.expect(PrimaryRobotLockerException.class);
        thrown.expectMessage(ExceptionMessages.TICKET_HAS_BEEN_USED);

        Bag bag = new Bag();
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(2);
        List lockerList = Arrays.asList(firstLocker, secondLocker);
        Robot robot = new Robot(lockerList);
        Ticket ticket = robot.store(bag);

        robot.pickUp(ticket);
        robot.pickUp(ticket);
    }

    @Test
    public void should_get_bag_given_valid_ticket_when_pick_up_bag() throws PrimaryRobotLockerException {
        Bag bag = new Bag();
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(2);
        List lockerList = Arrays.asList(firstLocker, secondLocker);
        Robot robot = new Robot(lockerList);
        Ticket ticket = robot.store(bag);

        Bag bagPickedUp = robot.pickUp(ticket);

        Assert.assertEquals(bagPickedUp, bag);
    }

}
