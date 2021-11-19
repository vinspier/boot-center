package com.vinspier.search.convert;

import com.vinspier.search.model.doc.GoodSkuDoc;
import com.vinspier.search.model.po.GoodSkus;

import java.util.ArrayList;
import java.util.List;

public class GoodSkusConvert {

    private GoodSkusConvert() {

    }

    public static GoodSkuDoc convertPOToDoc(GoodSkus sku){
        GoodSkuDoc goodSkuDoc = new GoodSkuDoc();
        goodSkuDoc.setId(sku.getId());
        goodSkuDoc.setStock(sku.getGoodStock());
        return goodSkuDoc;

    }

    public static List<GoodSkuDoc> convertPOListToDocList(List<GoodSkus> skusList){
        List<GoodSkuDoc> resultList = new ArrayList<>(skusList.size());
        skusList.forEach(item -> {
            resultList.add(convertPOToDoc(item));
        });

        return resultList;

    }

}
