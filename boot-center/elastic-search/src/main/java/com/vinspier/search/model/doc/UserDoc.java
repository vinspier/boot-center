package com.vinspier.search.model.doc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 用户索引
 *
 * @author  xiaobiao.fan
 * @date    2021/11/9 11:26 上午
*/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Document(indexName = "user",shards = 1,replicas = 1)
public class UserDoc implements Serializable {

    private static final long serialVersionUID = -8891895207285360889L;

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String username;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Text)
    private String phone;

    public UserDoc(Long id, String username, Integer age, String phone) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.phone = phone;
    }

}
