package com.spy.apollo.solr;

import com.spy.apollo.test.AbstractApolloBootTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.After;
import org.junit.Before;
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
public abstract class BaseTest extends AbstractApolloBootTest {

    @Autowired
    protected SolrClient solrClient;

    protected QueryResponse response;


    protected void printQueryResponseItem(SolrDocument doc) {
    }

    private void printQueryResponse() {
        if (response == null) {
            return;
        }
        log.debug("response={}", response);


        SolrDocumentList result = response.getResults();
        if (result != null) {
            log.debug("result={}", response.getResults());

            result.stream().forEach(item -> {
                printQueryResponseItem(item);
            });
        }
    }

    private long startTime;

    @Before
    public void before() {
        startTime = System.currentTimeMillis();
    }

    @After
    public void destroy() {

        log.debug("cost {}ms", System.currentTimeMillis() - startTime);


        printQueryResponse();

        if (solrClient != null) {
            try {
                solrClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
