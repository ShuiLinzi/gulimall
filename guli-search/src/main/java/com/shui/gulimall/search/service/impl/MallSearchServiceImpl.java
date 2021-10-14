package com.shui.gulimall.search.service.impl;

import com.shui.gulimall.constant.EsConstant;
import com.shui.gulimall.search.config.ElasticSearchConfig;
import com.shui.gulimall.search.service.MallSearchService;
import com.shui.gulimall.search.vo.SearchParam;
import com.shui.gulimall.search.vo.SearchResult;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MallSearchServiceImpl implements MallSearchService {
    @Autowired
    RestHighLevelClient highLevelClient;

    @Override
    public SearchResult search(SearchParam param) {
        //1.动态构建查询需要的dsl语句
        SearchResult result = null;
        //准备检索请求
        SearchRequest searchRequest = buildSearchRequest(param);
        try {
            //执行检索请求
            SearchResponse searchResponse = highLevelClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
            //分析响应数据封装成我们需要的格式
            result = bulidSearchResult(searchResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 准备检索请求
     * #模糊匹配，过滤(按照属性，分类，品牌，价格区间，库存)，排序，分页，高亮，聚合分析
     *
     * @return
     */
    private SearchRequest buildSearchRequest(SearchParam param) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();//构建dsl语句
        /**
         * 构建DSL语句，首先写模糊匹配，过滤(按照属性，分类，品牌，价格区间，库存)，
         */
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        //查询传递过来的参数是否为空
        if (!StringUtils.isEmpty(param.getKeyword())) {//模糊匹配
            queryBuilder.must(QueryBuilders.matchQuery("skuTitle", param.getKeyword()));
        }
        if (param.getCatalog3Id() != null) {//按照三级分类id查询
            queryBuilder.filter(QueryBuilders.termQuery("catalogId", param.getCatalog3Id()));

        }
        if (param.getBrandId() != null && param.getBrandId().size() > 0) {//按照评判的id查询
            queryBuilder.filter(QueryBuilders.termsQuery("brandId", param.getBrandId()));

        }
        //按仓库是否有库存查询
        queryBuilder.filter(QueryBuilders.termsQuery("hasStock", param.getHasStock() == 1));
        //按照价格区间查询
        if (!StringUtils.isEmpty(param.getSkuPrice())) {
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("skuPrice");
            String[] s = param.getSkuPrice().split("_");
            if (s.length == 2) {
                rangeQuery.gte(s[0]).lte(s[1]);
            } else if (s.length == 1) {
                if (param.getSkuPrice().startsWith("_")) {
                    rangeQuery.lte(s[0]);
                }
                if (param.getSkuPrice().endsWith("_")) {
                    rangeQuery.gte(s[0]);
                }
            }
            queryBuilder.filter(rangeQuery);
        }
        //按照所指定的属性进行查询
        if (param.getAttrs() != null && param.getAttrs().size() > 0) {
            for (String attrs : param.getAttrs()) {
                BoolQueryBuilder nestedboolQuery = QueryBuilders.boolQuery();
                String[] s = attrs.split("_");
                String attrId = s[0];//检索属性的id
                String[] attrValues = s[1].split(":");//属性检索用的值
                nestedboolQuery.must(QueryBuilders.termsQuery("attrs.attrId", attrId));
                nestedboolQuery.must(QueryBuilders.termsQuery("attrs.attrValue", attrValues));
                //每一个都必须生成一个nested查询
                NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", nestedboolQuery, ScoreMode.None);
                queryBuilder.filter(nestedQuery);

            }
        }
        searchSourceBuilder.query(queryBuilder);

        /**
         * 排序，分页，高亮
         *
         */
       if (!StringUtils.isEmpty(param.getSort())){
           String sort = param.getSort();
           // sort=hotScore_asc/desc
           String[] sortFields = sort.split("_");

           SortOrder sortOrder = "asc".equalsIgnoreCase(sortFields[1]) ? SortOrder.ASC : SortOrder.DESC;
           searchSourceBuilder.sort(sortFields[0], sortOrder);
       }
        // 2.2 分页 from = (pageNum - 1) * pageSize
        searchSourceBuilder.from((param.getPageNum() - 1) * EsConstant.PRODUCT_PAGE_SIZE);
        searchSourceBuilder.size(EsConstant.PRODUCT_PAGE_SIZE);

        // 2.3 高亮
        if (!org.springframework.util.StringUtils.isEmpty(param.getKeyword())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuTitle");
            highlightBuilder.preTags("<b style='color:red'>");
            highlightBuilder.postTags("</b>");

            searchSourceBuilder.highlighter(highlightBuilder);
        }
        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, searchSourceBuilder);

        return searchRequest;
    }

    /**
     * 构建结果
     *
     * @param searchResponse
     * @return
     */
    private SearchResult bulidSearchResult(SearchResponse searchResponse) {

        return null;
    }
}
