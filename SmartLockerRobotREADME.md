# Smart Robot Locker Tasking

* given 2个locker,第一个剩余空间大于第二个,when smartLockerRobot存包 then包被存入第一个locker,返回票 -------done
* given 2个locker, 2个剩余空间相同，when smartLockerRobot存包 then包被存入第一个locker，返回票 -------done
* given 2个locker, 第2个剩余空间大于第一个，when smartLockerRobot存包 then包被存入第2个locker，返回票 ------done
* given 2个locker , 没有剩余空间， when smartLockerRobot存包 then存包失败,提示存包失败 -----done
* given 1个有效票据； when smartLockerRobot取包, then取包成功, 票据回收 ----done
* given 1个无效票据； when smartLockerRobot取包, then取包失败，提示票据无效 -----done
* given primaryLockerRobot和smartLockerRobot同时管理locker，提供1个smartLockerRobot的存包票；when primaryLockerRobot取包；then取包成功
* given primaryLockerRobot和smartLockerRobot同时管理locker，提供1个primaryLockerRobot的存包票；when smartLockerRobot取包；then取包成功

