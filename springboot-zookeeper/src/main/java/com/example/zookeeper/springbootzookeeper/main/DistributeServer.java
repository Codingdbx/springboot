package com.example.zookeeper.springbootzookeeper.main;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2021/3/20 23:36
 * @since JDK1.8
 */
public class DistributeServer {
    private static String connectString = "192.168.1.8:2181,192.168.1.9:2181,192.168.1.10:2181";
    private static int sessionTimeout = 2000;
    private ZooKeeper zk = null;
    private String parentNode = "/servers";

    // 创建到 zk 的客户端连接
    public void getConnect() throws IOException {
        zk = new ZooKeeper(connectString, sessionTimeout, event -> {

        });
    }

    // 注册服务器
    public void registerServer(String hostname) throws Exception{
        String create = zk.create(parentNode + "/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println(hostname +" is online "+ create);
    }

    // 业务功能
    public void business(String hostname) throws Exception{
        System.out.println(hostname+" is working ...");

        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        // 1 获取 zk 连接
        DistributeServer server = new DistributeServer();
        server.getConnect();

        // 2 利用 zk 连接注册服务器信息
        server.registerServer("192.168.0.110");

        // 3 启动业务功能
        server.business("192.168.0.110");
    }
}
