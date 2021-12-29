package com.vinspier.search.convert;

import com.google.common.collect.Lists;
import com.vinspier.search.enums.SeparatorEnum;
import com.vinspier.search.model.doc.GoodsDoc;
import com.vinspier.search.model.po.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * 主档商品转换器
 *
 * @author  xiaobiao.fan
 * @date    2021/11/17 7:58 下午
*/
public class GoodsConvert {

    private GoodsConvert() {

    }

    public static GoodsDoc convertPOtoDoc(Goods goods){
        GoodsDoc goodsDoc = new GoodsDoc();
        goodsDoc.setId(goods.getId());
        goodsDoc.setName(goods.getGoodTitle());
        parseCategoryPath(goods.getCategoryPath(),goodsDoc);
        goodsDoc.setStockType(goods.getStockType());
        goodsDoc.setArTranslate(1);
        goodsDoc.setSupplierId(goods.getSupplierId());
        goodsDoc.setOpeningDirection(goods.getOpeningDirection());
        goodsDoc.setShelfAt(goods.getShelfAt());
        goodsDoc.setSkus(Lists.newArrayList());
        goodsDoc.setStatus(goods.getStatus());
        goodsDoc.setSeason(Lists.newArrayList(1,2,3,4,8,10));
        goodsDoc.setSiteMe(1);
        goodsDoc.setSiteEa(1);
        goodsDoc.setSiteSa(1);
        goodsDoc.setSiteSe(1);

        return goodsDoc;

    }

    public static List<GoodsDoc> convertPOListToDocList(List<Goods> goodsList){
        List<GoodsDoc> goodsDocList = new ArrayList<>(goodsList.size());
        goodsList.forEach(item -> {
            goodsDocList.add(convertPOtoDoc(item));
        });

        return goodsDocList;

    }

    /**
     * 解析类目路径
     * path=0，ID1，ID2，ID3
     * @param path
     * @param goodsDoc
     */
    private static void parseCategoryPath(String path,GoodsDoc goodsDoc){
        String[] categoryIds = path.split(SeparatorEnum.COMMA.getSymbol());
        goodsDoc.setRootCid(Integer.valueOf(categoryIds[1]));
        goodsDoc.setSecCid(Integer.valueOf(categoryIds[2]));
        goodsDoc.setLeafCid(Integer.valueOf(categoryIds[3]));
    }

}
