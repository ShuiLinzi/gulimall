package com.shui.gulimall.product.feign;


import com.shui.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("guli-ware")


public interface WareFeignService {
    @PostMapping("ware/waresku/hasstock")
     R getSkusHasStock(@RequestBody List<Long> skuIds);
}
