package com.abs.springabshystrix.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 16:01 2018/6/6
 **/
public class TestController {

    private static final String A="222";

    List<String> list=new ArrayList(12);

    public List test(){
        synchronized (list){
            list.add(A);
            return list;
        }
    }

}
