package com.spy.apollo.solr;

import com.spy.apollo.solr.common.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocument;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-03 13:05
 * @since 1.0
 */
@Slf4j
public class AdanceQueryTest extends BaseTest {

    @Test
    public void queryTest() throws Exception {

        SolrQuery params = new SolrQuery();

        params.setQuery("chi_name:宝盈* OR chi_name:上海*");
        params.setStart(0);
        params.setRows(20);

        params.addSort("id", SolrQuery.ORDER.asc);


        params.setHighlight(true);
        params.addHighlightField("chi_name");// 高亮字段
        params.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
        params.setHighlightSimplePost("</font>");//后缀


        response = solrClient.query(Const.COLLECTION_FUND_INFO, params);


        // 输出高亮模块
        Map<String, Map<String, List<String>>>                 highLight = response.getHighlighting();
        Set<Map.Entry<String, Map<String, List<String>>>>      sets      = highLight.entrySet();
        Iterator<Map.Entry<String, Map<String, List<String>>>> it        = sets.iterator();

        while (it.hasNext()) {
            Map.Entry<String, Map<String, List<String>>> item = it.next();

            log.debug("key={},value={}", item.getKey(), item.getValue());
        }

    }

    @Override
    protected void printQueryResponseItem(SolrDocument doc) {
        log.debug("id={},inner_code={},chi_name={},secu_code={}",
            doc.get("id"),
            doc.get("inner_code"),
            doc.get("chi_name"),
            doc.get("secu_code"));
    }
}
