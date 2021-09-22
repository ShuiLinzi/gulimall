package com.shui.gulimall.product.vo;

import lombok.Data;

@Data
public class AttrRespVo extends AttrVo {
    private String gruopName;


    private String catelogName;

    private Long[] catelogPath;


}
