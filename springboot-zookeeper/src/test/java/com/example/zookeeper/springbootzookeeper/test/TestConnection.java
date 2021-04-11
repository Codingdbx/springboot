package com.example.zookeeper.springbootzookeeper.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2021/3/20 23:17
 * @since JDK1.8
 */

@SpringBootTest
public class TestConnection {

    private static String connectString = "192.168.1.8:2181,192.168.1.9:2181,192.168.1.10:2181";

    private static int sessionTimeout = 2000;

    private ZooKeeper zkClient = null;

    @Before
    public void init() throws Exception {
        zkClient = new ZooKeeper(connectString, sessionTimeout, event -> {
                    // 收到事件通知后的回调函数（用户的业务逻辑）
                    System.out.println(event.getType() + "--" + event.getPath());
                    // 再次启动监听
                    try {
                        zkClient.getChildren("/", true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    // 创建子节点
    @Test
    public void create() throws Exception {
        // 参数 1：要创建的节点的路径
        // 参数 2：节点数据
        // 参数 3：节点权限
        // 参数 4：节点的类型
        String nodeCreated = zkClient.create("/danny01", "test01".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    // 获取子节点
    @Test
    public void getChildren() throws Exception {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }

        // 延时阻塞
        Thread.sleep(Long.MAX_VALUE);
    }

    // 判断 znode 是否存在
    @Test
    public void exist() throws Exception {
        Stat stat = zkClient.exists("/danny", false);

        System.out.println(stat == null ? "not exist" : "exist");
    }

}
