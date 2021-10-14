package com.shui.gulimall.search.vo;

import com.shui.common.to.es.SkuEsModel;
import lombok.Data;

import java.util.List;
@Data
public class SearchResult {
    private List<SkuEsModel> products;
    /**
     * 下面是分页信息
     */
    private Integer pageNum;//当前页码
    private Long total;//总记录数
    private Integer totalPages;//总页码
    /**
     * 下面是封装的属性信息
     */
    private List<BrandVo> brands;//所设计的品牌信息
    private List<AttrVo> attrs;//当前查询到的信息，所设计到的所有属性
    private List<CatalogVo> catalogs;


    @Data
    public static class BrandVo{
        private Long brandId;
        private String brandName;
        private String brandImg;
    }
    @Data
    public static class AttrVo{
        private Long attrId;
        private String attrName;
        private List<String> attrValue;
    }

    @Data
    public static class CatalogVo{
        private Long catalogId;
        private String catalogName;
    }

}
