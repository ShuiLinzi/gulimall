package com.shui.gulimall.search.vo;

import lombok.Data;

import java.util.List;

/**
 * 封装页面可能传来的查询条件
 */
@Data
public class SearchParam {
    private String keyword;//全文关键词
    private String catalog3Id;//三级分类id

    private String sort;//排序条件
    /**
     * 一些过滤条件
     */
    private Integer hasStock;//是否只显示有货状态
    private String skuPrice;//价格区间查询
    private List<Long> brandId;//按照品牌进行查询，可以多选
    private Integer pageNum;//页码
    private List<String> attrs; //按照属性进行筛选

}
