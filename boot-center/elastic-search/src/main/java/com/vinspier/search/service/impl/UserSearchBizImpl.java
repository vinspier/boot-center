package com.vinspier.search.service.impl;

import com.vinspier.search.model.doc.UserDoc;
import com.vinspier.search.model.param.UserParam;
import com.vinspier.search.service.UserSearchBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserSearchBizImpl implements UserSearchBiz {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public List<UserDoc> findList(UserParam param) {
        // 制定查询
        CriteriaQuery criteriaQuery = new CriteriaQuery(
                new Criteria()
                .and(new Criteria("username").contains(param.getUsername()))
//                .and(new Criteria("age").is(param.getAge()))
//                .and(new Criteria("age").between(param.getAgeMin(),param.getAgeMax())
                .and(new Criteria("age").greaterThanEqual(param.getAgeMin()))
                .and(new Criteria("age").lessThanEqual(param.getAgeMax()))
                );
        // 设置分页
        criteriaQuery.setPageable(PageRequest.of(0, 10));

        SearchHits<UserDoc> result = elasticsearchRestTemplate.search(criteriaQuery,UserDoc.class);

        return result.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

}
