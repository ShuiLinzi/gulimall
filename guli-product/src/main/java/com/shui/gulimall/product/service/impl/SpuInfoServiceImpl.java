package com.shui.gulimall.product.service.impl;

import com.shui.common.to.es.SkuEsModel;
import com.shui.common.utils.R;
import com.shui.gulimall.product.entity.*;
import com.shui.gulimall.product.feign.WareFeignService;
import com.shui.gulimall.product.service.*;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shui.common.utils.PageUtils;
import com.shui.common.utils.Query;

import com.shui.gulimall.product.dao.SpuInfoDao;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AttrService attrService;
    @Autowired
    ProductAttrValueService attrValueService;
    @Autowired
    WareFeignService wareFeignService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    //商品上架功能
    @Override
    public void up(Long spuId) {
        List<SkuEsModel> upProducts = new ArrayList<>();
        //组装需要的数据
        List<ProductAttrValueEntity> baseAttrs = attrValueService.baseAttrlistforspu(spuId);
        List<Long> attrIds = baseAttrs.stream().map(ProductAttrValueEntity::getAttrId).collect(Collectors.toList());
        List<Long> searchAttrIds = attrService.selectSearchAttrs(attrIds);
        //转换为Set集合
        Set<Long> idSet = new HashSet<>(searchAttrIds);
        // TODO: 2021/9/12 1.发送远程调用，调用库存系统查询是否有库存
        R skusHasStock = wareFeignService.getSkusHasStock(attrIds);

        List<SkuEsModel.Attr> attrsList = baseAttrs.stream()
                .filter(item -> idSet.contains(item.getAttrId()))
                .map(item -> {
                    SkuEsModel.Attr attrs = new SkuEsModel.Attr();
                    BeanUtils.copyProperties(item, attrs);
                    return attrs;
                }).collect(Collectors.toList());

//        List<Long> skuIdList = skuInfoEntities.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());
        List<SkuInfoEntity> skuInfoEntityList = skuInfoService.getSkuBySpuId(spuId);
        //2.封装每个sku信息 成为upProducts
        List<SkuEsModel> collect = skuInfoEntityList.stream().map((sku) -> {
            SkuEsModel model = new SkuEsModel();
            BeanUtils.copyProperties(sku, model);
            model.setSkuPrice(sku.getPrice());
            model.setSkuImg(sku.getSkuDefaultImg());
            model.setAttrs(attrsList);


            // TODO: 2021/9/12 2.热度评分，默认为零
            model.setHotScore(0L);
            //TODO: 3.查询品牌和分类的名字信息
            BrandEntity brandEntity = brandService.getById(model.getBrandId());
            model.setBrandName(brandEntity.getName());
            model.setBrandImg(brandEntity.getLogo());
            CategoryEntity categoryEntity = categoryService.getById(model.getCatalogId());
            model.setCatalogName(categoryEntity.getName());
            //TODO: 4.查询当前sku所有规格能被查询的属性
            return model;
        }).collect(Collectors.toList());
        // TODO：将数据发送给es进行保存
    }

}