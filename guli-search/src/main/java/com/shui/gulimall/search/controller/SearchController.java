package com.shui.gulimall.search.controller;

import com.shui.gulimall.search.service.MallSearchService;
import com.shui.gulimall.search.vo.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {
    @Autowired
    MallSearchService mallSearchService;

    @GetMapping("/list.html")
    public String listPage(SearchParam param, Model model) {
        Object result = mallSearchService.search(param);
        model.addAttribute("result", result);
        return "list";
    }
}
