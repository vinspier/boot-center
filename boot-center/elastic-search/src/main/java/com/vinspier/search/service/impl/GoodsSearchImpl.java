package com.vinspier.search.service.impl;

import com.vinspier.search.dao.GoodsRepository;
import com.vinspier.search.model.doc.GoodsDoc;
import com.vinspier.search.model.param.GoodsParam;
import com.vinspier.search.service.GoodsSearch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsSearchImpl implements GoodsSearch {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<GoodsDoc> queryList(GoodsParam param) {
        Criteria root = new Criteria();
        if (StringUtils.hasText(param.getName())){
            root.and(new Criteria(GoodsDoc.TITLE).matches(param.getName()));
        }
        if (Objects.nonNull(param.getStockType())){
            root.and(new Criteria(GoodsDoc.TYPE).is(param.getStockType()));
        }

        CriteriaQuery query = new CriteriaQuery(root);

        SearchHits<GoodsDoc> result = elasticsearchRestTemplate.search(query,GoodsDoc.class);

        return result.get().map(SearchHit::getContent).collect(Collectors.toList());
    }


}
