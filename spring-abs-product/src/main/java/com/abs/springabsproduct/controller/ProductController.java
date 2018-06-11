package com.abs.springabsproduct.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 10:45 2018/5/31
 **/
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @RequestMapping(value = "/getProduct",method = RequestMethod.GET)
    public String getProduct(){
        return "product1";
    }

}
