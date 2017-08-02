package com.spy.apollo.solr.domain;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.geo.Point;

import java.util.List;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-08-02 14:25
 * @since 1.0
 */
@Data
//@SolrDocument(solrCoreName = "core20170802")
public class Product {

    @Id
    @Field
    private String id;

    @Field
    private String name;

    @Field
    private Double price;

    @Field("cat")
    private List<String> category;

    @Field("store")
    private Point location;

    public Product() {
    }

    public Product(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
