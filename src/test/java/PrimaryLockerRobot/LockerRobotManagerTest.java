package PrimaryLockerRobot;


/*
 * Given Locker Robot Manager 没有管理Locker，且管理2个robot且票据无效，When Locker Robot Manager取包 Then 取包失败，提示无效票据
 * Given Locker Robot Manager 管理1个Locker，且管理1个robot且票据有效，When Locker Robot Manager取包 Then 取包成功
 * Given Locker Robot Manager 管理1个Locker，且管理1个robot且票据无效，When Locker Robot Manager取包 Then 取包失败，提示无效票据
 * Given Locker Robot Manager 管理一个locker，一个robot； When Locker Robot Manager 拿locker的票去 robot 取 robot存的包 ；Then 取包失败，提示票据无效；
 * Given Locker Robot Manager 管理一个locker，一个robot； When Locker Robot Manager 拿robot的票去 locker 取 robot存的包 ；Then 取包失败，提示票据无效；*/

import PrimaryLockerRobot.Exception.ExceptionMessages;
import PrimaryLockerRobot.Exception.LockerRobotManagerException;
import PrimaryLockerRobot.Exception.PrimaryLockerRobotException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

public class LockerRobotManagerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static final List emptyLockers = Arrays.asList();

    @Test
    public void should_return_ticket_and_saved_in_1st_locker_when_manager_save_bag_given_manger_managed_two_locker_and_all_have_valid_capacity() throws PrimaryLockerRobotException {
        Bag bag = new Bag();
        Locker locker1 = new Locker(5);
        Locker locker2 = new Locker(5);
        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(locker1,locker2), null);
        Ticket ticket = manager.store(bag);
        Assert.assertNotNull(ticket);
        Assert.assertEquals(1,ticket.getPosition());
    }

    @Test
    public void should_return_ticket_and_saved_in_2nd_locker_when_manager_save_bag_given_manger_managed_two_locker_and_1st_locker_is_full_2nd_locker_have_valid_capacity() throws PrimaryLockerRobotException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(5);
        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(locker1,locker2), null);
        manager.store(new Bag());
        Ticket ticket = manager.store(new Bag());
        Assert.assertNotNull(ticket);
        Assert.assertEquals(2, ticket.getPosition());
    }

    @Test
    public void should_reminder_no_valid_capacity_when_manager_save_bag_given_manger_managed_two_locker_and_all_is_full() throws PrimaryLockerRobotException {

        thrown.expect(LockerRobotManagerException.class);
        thrown.expectMessage(ExceptionMessages.NO_CAPACITY);

        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(locker1,locker2), null);
        manager.store(new Bag());
        manager.store(new Bag());
        manager.store(new Bag());
    }

    @Test
    public void should_saved_by_1st_robot_when_manager_save_bag_given_manger_managed_two_robot_and_all_have_capacity() throws PrimaryLockerRobotException {

        Locker locker1 = new Locker(2);
        Robot robot1 = new Robot(Arrays.asList(locker1));
        Locker locker2 = new Locker(2);
        Robot robot2 = new Robot(Arrays.asList(locker2));

        LockerRobotManager manager = new LockerRobotManager(emptyLockers, Arrays.asList(robot1,robot2));
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        LockerRobotManager.Management management = manager.managedBy(ticket);
        Assert.assertNotNull(ticket);
        Assert.assertEquals(LockerRobotManager.Management.Owner.robot, management.owner);
        Assert.assertEquals(1, management.position);
    }

    @Test
    public void should_saved_by_2st_robot_when_manager_save_bag_given_manger_managed_two_robot_and_1st_robot_is_full_2nd_robot_has_capacity() throws PrimaryLockerRobotException {

        Locker locker1 = new Locker(1);
        Robot robot1 = new Robot(Arrays.asList(locker1));
        Locker locker2 = new Locker(1);
        Robot robot2 = new Robot(Arrays.asList(locker2));

        LockerRobotManager manager = new LockerRobotManager(emptyLockers, Arrays.asList(robot1,robot2));

        manager.store(new Bag());

        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        LockerRobotManager.Management management = manager.managedBy(ticket);
        Assert.assertNotNull(ticket);
        Assert.assertEquals(LockerRobotManager.Management.Owner.robot, management.owner);
        Assert.assertEquals(2, management.position);
    }

    @Test
    public void should_reminder_has_no_capacity_when_manager_save_bag_given_manger_managed_two_robot_and_all_is_full() throws LockerRobotManagerException {

        thrown.expect(LockerRobotManagerException.class);
        thrown.expectMessage(ExceptionMessages.NO_CAPACITY);

        Locker locker1 = new Locker(1);
        Robot robot1 = new Robot(Arrays.asList(locker1));
        Locker locker2 = new Locker(1);
        Robot robot2 = new Robot(Arrays.asList(locker2));

        LockerRobotManager manager = new LockerRobotManager(emptyLockers, Arrays.asList(robot1,robot2));

        manager.store(new Bag());
        manager.store(new Bag());

        manager.store(new Bag());
    }

    @Test
    public void should_saved_by_robot_when_manager_save_bag_given_manger_managed_1_robot_and_1_locker_and_all_has_capacity() throws PrimaryLockerRobotException {

        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Robot robot1 = new Robot(Arrays.asList(locker2));

        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(locker1), Arrays.asList(robot1));

        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        LockerRobotManager.Management management = manager.managedBy(ticket);
        Assert.assertNotNull(ticket);
        Assert.assertEquals(LockerRobotManager.Management.Owner.robot, management.owner);
        Assert.assertEquals(1, management.position);
    }

    @Test
    public void should_saved_by_locker_when_manager_save_bag_given_manger_managed_1_robot_and_1_locker_and_robot_is_full_locker_has_capacity() throws PrimaryLockerRobotException {

        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Robot robot1 = new Robot(Arrays.asList(locker2));

        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(locker1), Arrays.asList(robot1));

        manager.store(new Bag());

        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        LockerRobotManager.Management management = manager.managedBy(ticket);

        Assert.assertNotNull(ticket);
        Assert.assertEquals(LockerRobotManager.Management.Owner.locker, management.owner);
        Assert.assertEquals(1, management.position);
    }

    @Test
    public void should_reminder_has_no_capacity_when_manager_save_bag_given_manger_managed_1_robot_and_1_locker_all_is_full() throws LockerRobotManagerException {

        thrown.expect(LockerRobotManagerException.class);
        thrown.expectMessage(ExceptionMessages.NO_CAPACITY);

        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Robot robot1 = new Robot(Arrays.asList(locker2));

        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(locker1), Arrays.asList(robot1));

        manager.store(new Bag());
        manager.store(new Bag());

        manager.store(new Bag());
    }

    @Test
    public void should_return_bag_when_manager_pick_up_bag_given_manger_managed_two_lockers_and_give_valid_ticket() throws PrimaryLockerRobotException {
        Bag bag = new Bag();
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(locker1,locker2), null);
        Ticket ticket = manager.store(bag);

        Bag pickedBag = manager.pickUp(ticket);

        Assert.assertNotNull(pickedBag);
        Assert.assertEquals(bag,pickedBag);
    }

    @Test
    public void should_return_bag_when_manager_pick_up_bag_given_manger_managed_two_lockers_and_give_invalid_ticket() throws LockerRobotManagerException {

        thrown.expect(LockerRobotManagerException.class);
        thrown.expectMessage(ExceptionMessages.INVALID_TICKET);

        Bag bag = new Bag();
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(locker1,locker2), null);

        manager.pickUp(new Ticket(0));
    }

    @Test
    public void should_return_bag_when_manager_pick_up_bag_given_manger_managed_two_robots_and_give_valid_ticket() throws PrimaryLockerRobotException {
        Locker locker1 = new Locker(2);
        Robot robot1 = new Robot(Arrays.asList(locker1));
        Locker locker2 = new Locker(2);
        Robot robot2 = new Robot(Arrays.asList(locker2));

        LockerRobotManager manager = new LockerRobotManager(emptyLockers, Arrays.asList(robot1,robot2));
        Bag bag = new Bag();
        Ticket ticket = manager.store(bag);

        Bag pickedBag = manager.pickUp(ticket);

        Assert.assertNotNull(pickedBag);
        Assert.assertEquals(bag,pickedBag);
    }

    @Test
    public void should_return_bag_when_manager_pick_up_bag_given_manger_managed_two_robots_and_give_invalid_ticket() throws LockerRobotManagerException {
        thrown.expect(LockerRobotManagerException.class);
        thrown.expectMessage(ExceptionMessages.INVALID_TICKET);

        Locker locker1 = new Locker(2);
        Robot robot1 = new Robot(Arrays.asList(locker1));
        Locker locker2 = new Locker(2);
        Robot robot2 = new Robot(Arrays.asList(locker2));

        LockerRobotManager manager = new LockerRobotManager(emptyLockers, Arrays.asList(robot1,robot2));

        manager.pickUp(new Ticket(0));
    }


}
