package com.shui.gulimall.search;

import com.shui.gulimall.search.config.ElasticSearchConfig;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GuliSearchApplicationTests {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void contextLoads() {
        System.out.println(restHighLevelClient);
    }

    @Test
    void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        indexRequest.source("username", "cgq", "age", "19");
        restHighLevelClient.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);

    }

}
