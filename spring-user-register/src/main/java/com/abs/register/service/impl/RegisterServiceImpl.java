package com.abs.register.service.impl;

import com.abs.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 14:59 2018/5/31
 **/
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getProductById() {
        ResponseEntity<String> responseEntity= restTemplate.getForEntity("http://spring-abs-product/product/getProduct",String.class);
        return responseEntity.getBody();
    }
}
