package com.shui.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shui.gulimall.product.entity.CategoryEntity;
import com.shui.gulimall.product.service.CategoryService;
import com.shui.common.utils.PageUtils;
import com.shui.common.utils.R;


/**
 * 商品三级分类
 *
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-29 23:17:09
 */
@Slf4j
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表，查出所有分类和子分类
     */
    @RequestMapping("/list/tree")
    //@RequiresPermissions("product:category:list")
    public R list() {
        List<CategoryEntity> entities = categoryService.listWithTree();
        // List<CategoryEntity> entities = categoryService.list(null);

        return R.ok().put("data", entities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    // @RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId) {
        CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category) {
        categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category) {
        categoryService.updateCascade(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("product:category:delete")
    /**
     * @RequestBody是获取请求体，而只有post请求有请求体，所以要发送post请求
     * springmvc会自动把前端传来的json数据转化成对象
     */
    public R delete(@RequestBody Long[] catIds) {
        categoryService.removeByIds(Arrays.asList(catIds));

        //categoryService.removeMenuByIds(Arrays.asList(catIds));
        return R.ok();
    }


}
