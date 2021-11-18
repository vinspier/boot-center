package com.vinspier.search.dao;

import com.vinspier.search.model.doc.GoodsDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsRepository extends ElasticsearchRepository<GoodsDoc,Integer> {

}
