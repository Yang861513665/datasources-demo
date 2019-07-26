import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author yangxj
 * @date 2019/6/11-20:43
 */
public class ZookeeperClientDemo {
    String coonectString="119.23.57.79:2181";
    int sessionTimeout = 2000;
    ZooKeeper zkClient =null;
    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper(coonectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("--->"+watchedEvent.getPath());
                System.out.println("--->"+watchedEvent.getState());
                System.out.println("--->"+watchedEvent.getType());
            }
        });
    }
    // 获取子节点
    @Test
    public void getChildren() throws Exception {
        System.out.println("获取子节点.....");
        List<String> children = zkClient.getChildren("/admin", false);
        for (String child : children) {
            System.out.println(child);
        }
        // 延时阻塞
//        Thread.sleep(Long.MAX_VALUE);
    }
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        System.out.println("创建zNode.....");
        // 参数1：要创建的节点的路径； 参数2：节点数据 ； 参数3：节点权限 ；参数4：节点的类型
        String nodeCreated = zkClient.create("/atguigu", "yangxj".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(nodeCreated);
    }


}
