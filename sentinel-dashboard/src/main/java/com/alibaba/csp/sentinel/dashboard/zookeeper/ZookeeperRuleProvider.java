/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.zookeeper;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.util.PathUtils;
import com.alibaba.fastjson.JSON;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Component("ZookeeperRuleProvider")
public class ZookeeperRuleProvider implements DynamicRuleProvider<List<FlowRuleEntity>> {
    @Autowired
    private CuratorFramework zkClient;
    @Value("${zookeeper.path}")
    private String path;

    @Override
    public List<FlowRuleEntity> getRules(String appName) throws Exception {
        String p = PathUtils.concat(path, appName);
        byte[] data = zkClient.getData().forPath(p);
        if (data == null) {
            return Collections.emptyList();
        }
        String json = new String(data, StandardCharsets.UTF_8);
        return JSON.parseArray(json, FlowRuleEntity.class);
    }
}
