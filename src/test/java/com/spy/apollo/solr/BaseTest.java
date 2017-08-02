package com.spy.apollo.solr;

import com.spy.apollo.test.AbstractApolloBootTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-02 14:06
 * @since 1.0
 */
@Slf4j
public class BaseTest extends AbstractApolloBootTest {

    @Autowired
    protected SolrClient solrClient;


    @After
    public void destroy() {
        if (solrClient != null) {
            try {
                solrClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
