package com.abs.springabsproduct.service.impl;

import com.abs.springabsproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 10:27 2018/5/31
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getTemplate() {
        ResponseEntity<String> responseEntity= restTemplate.getForEntity("http://eureka-client-register/user/register",String.class);
        return responseEntity.getBody();
    }
}
