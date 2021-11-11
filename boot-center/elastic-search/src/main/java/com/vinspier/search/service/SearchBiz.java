package com.vinspier.search.service;

import com.vinspier.search.model.doc.UserDoc;

import java.util.List;

public interface SearchBiz {

    /**
     * 创建索引
     *
     * @param
     * @return  void
     * @author  xiaobiao.fan
     * @date    2021/11/9 5:47 下午
    */
    boolean initUserIndex(String indexName);

    /**
     * 保存数据至es
     *
     * @param   userId
     * @return  void
     * @author  xiaobiao.fan
     * @date    2021/11/9 5:47 下午
    */
    UserDoc saveUser(Integer userId);

    List<UserDoc> initUsers(List<Integer> userIds);
}
