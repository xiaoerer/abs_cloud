package com.abs.springabsorder.common;

import org.springframework.util.StringUtils;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 17:51 2018/6/20
 **/
public class Utils {

    private static boolean existSplitStr(String source, String split, String str) {
        if (StringUtils.isEmpty(source))
            split = ",";// 默认英文逗号
        if (!StringUtils.isEmpty(source)) {
            for (String temp : source.split(split)) {
                System.out.println(temp);
                if (temp.equals(str))
                    return true;
            }
        }
        System.out.println(split);
        return false;
    }


    public static void main(String[] args) {
        boolean a=existSplitStr("P000,999999,51B3,5185", ",", "P000");
//        boolean a=existSplitStr("P000,999999,51B3,5185", ",", "");
        System.out.println(a);
    }

}
