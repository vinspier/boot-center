package com.vinspier.search.dao;

import com.vinspier.search.model.doc.UserDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<UserDoc,Long> {

}
