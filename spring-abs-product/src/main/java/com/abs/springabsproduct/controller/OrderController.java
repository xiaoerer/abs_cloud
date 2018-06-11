package com.abs.springabsproduct.controller;

import com.abs.springabsproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 11:43 2018/5/31
 **/
@RestController
public class OrderController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getTest(){
        System.out.println("测试");
        return productService.getTemplate();

    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String getTest2(){
        return "product测试";
    }
}
