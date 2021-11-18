package com.vinspier.search.service.impl;

import com.vinspier.search.dao.GoodsRepository;
import com.vinspier.search.service.GoodsSearch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoodsSearchImpl implements GoodsSearch {

    @Autowired
    private GoodsRepository goodsRepository;

}
