package com.vinspier.search.model.doc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 商品站点语言
 *
 * @author  xiaobiao.fan
 * @date    2021/12/1 5:10 下午
*/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Document(indexName = "item_language")
public class ItemLanguageDoc {

    public static final String CODE = "code";

    /**
     * 语言编码(es ar....)
     **/
    private String code;


}
