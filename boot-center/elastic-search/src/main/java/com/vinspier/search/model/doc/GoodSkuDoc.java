package com.vinspier.search.model.doc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * sku商品索引文档
 *
 * @author  xiaobiao.fan
 * @date    2021/11/19 10:30 上午
*/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Document(indexName = "item_goods_sku")
public class GoodSkuDoc {

    public static final String ID = "id";
    public static final String STOCK = "stock";

    /**
     * ID
     * */
    @Id
    private Integer id;

    /**
     * 库存
     * */
    @Field(type = FieldType.Integer)
    private Integer stock;

}
