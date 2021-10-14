package com.shui.gulimall.search.service;

import com.shui.gulimall.search.vo.SearchParam;
import com.shui.gulimall.search.vo.SearchResult;

public interface MallSearchService {
    /**
     *
     * @param param 检索的所有参数
     * @return 最终返回检索的结果
     */
    SearchResult search(SearchParam param);

}
