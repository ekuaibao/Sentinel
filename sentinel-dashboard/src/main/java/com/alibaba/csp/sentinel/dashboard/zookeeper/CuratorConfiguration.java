package com.alibaba.csp.sentinel.dashboard.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorConfiguration {
    @Bean
    public CuratorFramework createZkClient(@Value("${zookeeper.server}") String serverAddr,
                                           @Value("${zookeeper.path}") String path) {
        CuratorFramework zkClient = CuratorFrameworkFactory.newClient(
                serverAddr, new ExponentialBackoffRetry(10000, 29));
        zkClient.start();
        try {
            Stat stat = zkClient.checkExists().forPath(path);
            if (stat == null) {
                zkClient.create()
                        .creatingParentContainersIfNeeded()
                        .withMode(CreateMode.PERSISTENT).forPath(path, null);
            }
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }
        return zkClient;
    }
}
