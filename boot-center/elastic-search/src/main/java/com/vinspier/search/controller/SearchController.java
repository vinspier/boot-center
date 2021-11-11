package com.vinspier.search.controller;

import com.vinspier.search.common.ResponseTemplate;
import com.vinspier.search.model.doc.UserDoc;
import com.vinspier.search.service.SearchBiz;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/search")
@Api(value = "索引模块",tags = "索引模块")
public class SearchController {

    @Autowired
    private SearchBiz searchBiz;

    @GetMapping("/initIndex/{indexName}")
    public ResponseTemplate<Boolean> initIndex(@PathVariable("indexName") String indexName){

        return ResponseTemplate.ok(searchBiz.initUserIndex(indexName));
    }

    @PostMapping("/initUser/{userId}")
    public ResponseTemplate<UserDoc> initUser(@PathVariable("userId") Integer userId){

        return ResponseTemplate.ok(searchBiz.saveUser(userId));
    }

    @PostMapping("/initUsers")
    public ResponseTemplate<List<UserDoc>> initUsers(@RequestBody List<Integer> userIds){

        return ResponseTemplate.ok(searchBiz.initUsers(userIds));
    }

}
