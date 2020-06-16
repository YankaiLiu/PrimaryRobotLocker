package PrimaryRobotLocker;


/*
 * Given:  Robot 管理两个locker， 两个locker都有可用的容量； When: robot 存包；Then: 成功存入第一个Locker， 返回票据。
 * Given:  Robot 管理两个locker，第一个Locker已存满，第二个Locker有可用容量； When: robot 存包；Then: 成功存入第一个Locker， 返回票据。
 * Given:  Robot 管理两个locker，两个Locker都满了； When: robot 存包；Then: 存包失败，提示储物柜已满
 * Given：Robot 管理两个Locker，有一张无效的票；When：robot 取包； Then：取包失败，提示票据不合法
 * Given：Robot 管理两个Locker，有一张已经取过的票；When：robot 取包； Then：取包失败，提示已取包
 * Given：Robot 管理两个locker，有一张有效的票；When：robot 取包;  Then: 取包成功。
 */

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;


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
        thrown.expectMessage("all locker has no capacity");

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
        thrown.expectMessage("invalid ticket");

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
        thrown.expectMessage("this ticket has been used");

        Bag bag = new Bag();
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(2);
        List lockerList = Arrays.asList(firstLocker, secondLocker);
        Robot robot = new Robot(lockerList);
        Ticket ticket = robot.store(bag);

        robot.pickUp(ticket);
        robot.pickUp(ticket);
    }
}
