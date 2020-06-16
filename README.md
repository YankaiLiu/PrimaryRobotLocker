# Primary Locker Robot Tasking

* Given:  Robot 管理两个locker， 两个locker都有可用的容量； When: robot 存包；Then: 成功存入第一个Locker， 返回票据。
* Given:  Robot 管理两个locker，第一个Locker已存满，第二个Locker有可用容量； When: robot 存包；Then: 成功存入第一个Locker， 返回票据。
* Given:  Robot 管理两个locker，两个Locker都满了； When: robot 存包；Then: 存包失败，提示储物柜已满
* Given：Robot 管理两个Locker，有一张无效的票；When：robot 取包； Then：取包失败，提示票据不合法
* Given：Robot 管理两个Locker，有一张已经取过的票；When：robot 取包； Then：取包失败，提示已取包
* Given：Robot 管理两个locker，有一张有效的票；When：robot 取包;  Then: 取包成功。

