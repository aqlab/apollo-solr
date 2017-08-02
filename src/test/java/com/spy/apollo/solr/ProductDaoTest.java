package com.spy.apollo.solr;

import com.spy.apollo.solr.dao.ProductDao;
import com.spy.apollo.solr.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-02 14:27
 * @since 1.0
 */
@Slf4j
public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void saveTest() throws Exception {
        productDao.save(new Product("1", "Nintendo Entertainment System"));
    }
}
