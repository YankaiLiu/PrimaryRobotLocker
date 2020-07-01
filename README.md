# Locker Robor Manager

* Given:  Locker Robot Manager 管理两个locker， 且两个locker 均有可用空间， Locker Robot Manger 未管理robot; When: Locker Robot manager 存包； Then: 成功存入第一个locker， 返回票据
* Given: Locker Robot Manager 管理两个locker， 且第一个 locker已满， 第二个locker有可用空间， Locker Robot Manager 未管理 robot； When： Locker Robot Manager 存包； Then：成功存入第二个locker,返回票据；
* Given： Locker Robot Manager 管理两个locker，且两个locker均已满，Locker Robot Manager未管理robot； When： Locker Robot Manager 存包； Then： 存包失败，提示储物柜已满；
* --
* Given： Locker Robot Manager 未管理locker， Locker Robot Manager 管理两个robot， 且两个robot管理的locker均有可用空间。 When： Locker Robot Manager 存包； Then： 成功有第1个robot存入，返回票据；
* Given： Locker Robot Manager 未管理locker， Locker Robot Manager 管理两个robot，第1个robot的locker已存满，第2个robot的locker有可用空间 ； When： Locker Robot Manager 存包； Then： 成功有第2个robot存入，返回票据；
* Given： Locker Robot Manager 未管理locker， Locker Robot Manager 管理两个robot，两个robot管理的locker均已满 ； When： Locker Robot Manager 存包； Then： 存包失败，提示储物柜已满；
* Given： Locker Robot Manager 管理一个locker 和1个robot，且均有可用空间； When： Locker Robot Manager 存包； Then： 成功由robot存入，返回票据；
* Given： Locker Robot Manager 管理一个locker 和1个robot，locker有可用空间，但robot的locker已满； When： Locker Robot Manager 存包； Then： 成功存入locker，返回票据；
* Given： Locker Robot Manager 管理一个locker 和1个robot，且均已存满； When： Locker Robot Manager 存包； Then： 存包失败，提示储物柜已满；
* Given Locker Robot Manager 管理2个Locker，没有管理robot且票据有效，When Locker Robot Manager取包 Then 取包成功
* Given Locker Robot Manager 管理2个Locker，没有管理robot且票据无效，When Locker Robot Manager取包 Then 取包失败，提示无效票据
* Given Locker Robot Manager 没有管理Locker，且管理2个robot且票据有效，When Locker Robot Manager取包 Then 取包成功
* Given Locker Robot Manager 没有管理Locker，且管理2个robot且票据无效，When Locker Robot Manager取包 Then 取包失败，提示无效票据
* Given Locker Robot Manager 管理1个Locker，且管理1个robot且票据有效，When Locker Robot Manager取包 Then 取包成功
* Given Locker Robot Manager 管理1个Locker，且管理1个robot且票据无效，When Locker Robot Manager取包 Then 取包失败，提示无效票据
* Given Locker Robot Manager 管理一个locker，一个robot； When Locker Robot Manager 拿locker的票去 robot 取 robot存的包 ；Then 取包失败，提示票据无效；
* Given Locker Robot Manager 管理一个locker，一个robot； When Locker Robot Manager 拿robot的票去 locker 取 robot存的包 ；Then 取包失败，提示票据无效；

# Locker Rebot Director 
Task1:
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
- Given: LockerRobotDirector 管理两个 LockerRobotManager,


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
