package com.shui.gulimall.product.web;

import com.shui.gulimall.product.entity.CategoryEntity;
import com.shui.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    CategoryService categoryService;
    @GetMapping(value = {"/", "index.html"})
    private String indexPage(Model model) {
        //1、查出所有的一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categories();
        model.addAttribute("categories", categoryEntities);
        return "index";
    }
}
