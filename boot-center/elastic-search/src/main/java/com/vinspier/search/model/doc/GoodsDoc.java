package com.vinspier.search.model.doc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * 主档商品索引文档
 * 
 * @author  xiaobiao.fan
 * @date    2021/11/17 5:15 下午
*/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Document(indexName = "itemGoods",shards = 1,replicas = 1)
public class GoodsDoc {

    /**
     * 商品ID
     * */
    @Id
    private Integer id;

    /**
     * 商品名称
     * */
    @Field(type = FieldType.Keyword)
    private String name;

    /**
     * 一级类目
     */
    @Field(type = FieldType.Integer)
    private Integer rootCid;

    /**
     * 二级类目
     */
    @Field(type = FieldType.Integer)
    private Integer secCid;

    /**
     * 三级类目
     */
    @Field(type = FieldType.Integer)
    private Integer leafCid;

    /**
     * 商品类型 1 普通款 2 清仓款 3淘汰款 4断货款 5赠品
     */
    @Field(type = FieldType.Integer)
    private Integer type;

    /**
     * 阿拉伯语言翻译 已翻译1 未翻译 0
     */
    @Field(type = FieldType.Integer)
    private Integer arTranslate;

    /**
     * 供应商
     */
    @Field(type = FieldType.Integer)
    private Integer supplierId;

    /**
     * 开款方向 1婴儿，2小童，3中大童
     */
    @Field(type = FieldType.Integer)
    private Integer openDirection;

    /**
     * 上架时间
     */
    @Field(type = FieldType.Date)
    private Date shelfAt;

    /**
     * skuId集合
     */
    @Field(type = FieldType.Integer)
    private List<Integer> skuIds;

    /**
     * 季节 0春 1夏 2秋 3冬 4四季
     */
    @Field(type = FieldType.Integer)
    private Integer season;

    /**
     * 中东站点归属 2待分配 1是 0否
     */
    @Field(type = FieldType.Integer)
    private Integer siteMe;

    /**
     * 欧美站点归属 2待分配 1是 0否
     */
    @Field(type = FieldType.Integer)
    private Integer siteEa;

    /**
     * 南非站点归属 2待分配 1是 0否
     */
    @Field(type = FieldType.Integer)
    private Integer siteSa;

    /**
     * 东南亚站点归属 2待分配 1是 0否
     */
    @Field(type = FieldType.Integer)
    private Integer siteSe;

}