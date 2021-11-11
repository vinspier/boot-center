package com.vinspier.search.service;

import com.vinspier.search.model.doc.UserDoc;
import com.vinspier.search.model.param.UserParam;

import java.util.List;

/**
 * 用户es查询业务
 * */
public interface UserSearchBiz {

    /**
     * 条件搜索es
     *
     * @param   param
     * @return  java.util.List<com.vinspier.search.model.doc.UserDoc>
     * @author  xiaobiao.fan
     * @date    2021/11/9 8:03 下午
    */
    List<UserDoc> findList(UserParam param);

}
