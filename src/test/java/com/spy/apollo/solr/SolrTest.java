package com.spy.apollo.solr;

import com.spy.apollo.solr.common.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-02 14:06
 * @since 1.0
 */
@Slf4j
public class SolrTest extends BaseTest {


    @Test
    public void addTest() throws Exception {
        log.debug("solrClient={}", solrClient);

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "" + Math.random() * 1000);
        doc.addField("name", "Solr Input Documents 1");
        doc.addField("desc", "this is  content");

        UpdateResponse response = solrClient.add(Const.COLLECTION_20170802, doc);

        log.debug("response={}", response);
    }

    @Test
    public void queryTest() throws Exception {

        SolrQuery query = new SolrQuery();

        // 查询所有
//        query.set("q", "*:*");
        // 模糊查询
        query.setQuery("desc:*is*"); // => query.set("q","desc:*is*");

        response = solrClient.query(Const.COLLECTION_20170802, query);


    }

    @Override
    protected void printQueryResponseItem(SolrDocument doc) {
        log.debug("id={},name={},desc={}", doc.get("id"), doc.get("name"), doc.get("desc"));
    }

    @After
    @Override
    public void destroy() {

        // 立即刷新
        try {
            solrClient.commit(Const.COLLECTION_20170802);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.destroy();
    }
}
