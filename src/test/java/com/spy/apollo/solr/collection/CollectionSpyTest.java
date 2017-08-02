package com.spy.apollo.solr.collection;

import com.spy.apollo.solr.BaseTest;
import com.spy.apollo.solr.common.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-02 15:47
 * @since 1.0
 */
@Slf4j
public class CollectionSpyTest extends BaseTest {


    @Test
    public void run26() throws Exception {
        for (int i = 0; i < 20; i++) {
            run24();
        }
    }

    @Test
    public void run24() throws Exception {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "" + Math.round(Math.random() * 1000));
        doc.addField("name", "spy-" + UUID.randomUUID().toString());
        doc.addField("desc", "this is  content");

        UpdateResponse response = solrClient.add(Const.COLLECTION_SPY, doc);

        log.debug("response={}", response);
    }

    @After
    public void destroy() {

        // 将pending状态的内容立即刷到index中
        try {
            solrClient.commit(Const.COLLECTION_SPY);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        super.destroy();
    }

}
