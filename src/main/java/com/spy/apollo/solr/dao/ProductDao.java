package com.spy.apollo.solr.dao;

import com.spy.apollo.solr.domain.Product;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-02 14:26
 * @since 1.0
 */
public interface ProductDao extends SolrCrudRepository<Product, String> {

    List<Product> findByNameStartingWith(String name);
}
