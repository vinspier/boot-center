package com.vinspier.search.convert;
import com.google.common.collect.Lists;

import com.vinspier.search.model.doc.UserDoc;
import com.vinspier.search.model.po.User;

import java.util.List;

/**
 * 用户模型转换
 *
 * @author  xiaobiao.fan
 * @date    2021/11/9 5:43 下午
*/
public class UserConvert {

    private UserConvert() {

    }

    public static UserDoc convertPoToDoc(User user){
        UserDoc userDoc = new UserDoc();
        userDoc.setId(user.getId());
        userDoc.setUsername(user.getUsername());
        userDoc.setAge(user.getAge());
        userDoc.setPhone(user.getPhone());
        return userDoc;
    }

    public static List<UserDoc> convertPoToDocList(List<User> users){
        List<UserDoc> userDocList = Lists.newArrayList();
        for (User user :users) {
            userDocList.add(convertPoToDoc(user));
        }
        return userDocList;

    }

    public static User convertDocToPo(UserDoc doc){
        User user = new User();
        user.setId(doc.getId());
        user.setUsername(doc.getUsername());
        user.setAge(doc.getAge());
        user.setPhone(doc.getPhone());
        return user;
    }

    public static List<User> convertDocToPoList(List<UserDoc> docs){
        List<User> userList = Lists.newArrayList();
        for (UserDoc doc : docs) {
            userList.add(convertDocToPo(doc));
        }
        return userList;

    }

}
