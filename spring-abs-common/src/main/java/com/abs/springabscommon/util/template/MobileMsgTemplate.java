package com.abs.springabscommon.util.template;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 17:26 2018/7/6
 **/
@Data
@AllArgsConstructor //全参数构造器 与下面注释的构造器意义一样
public class MobileMsgTemplate implements Serializable {

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 文本
     */
    private String text;
    /**
     * 类型（通道）
     */
    private String type;

    /*public MobileMsgTemplate(String mobile, String text, String type) {
        this.mobile = mobile;
        this.text = text;
        this.type = type;
    }*/
}
