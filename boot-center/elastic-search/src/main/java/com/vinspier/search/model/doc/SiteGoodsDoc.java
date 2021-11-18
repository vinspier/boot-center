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
 * 站点商品索引文档
 * 
 * @author  xiaobiao.fan
 * @date    2021/11/17 5:15 下午
*/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Document(indexName = "itemSiteGoods",shards = 1,replicas = 1)
public class SiteGoodsDoc {

    /**
     * 商品ID
     * */
    @Id
    private Integer id;

    /**
     * 站点ID
     * */
    @Field(type = FieldType.Integer)
    private Integer siteId;

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
     * 0待上架 1上架 2下架
     * */
    @Field(type = FieldType.Integer)
    private Integer status;

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
     * 信息变更标签 1 供货价修改 2 图片修改
     */
    @Field(type = FieldType.Integer)
    private Integer infoChangeType;

}
