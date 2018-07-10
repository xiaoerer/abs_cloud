package com.abs.springabscommon.constant.enums;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 15:35 2018/7/3
 * 短信通道枚举
 **/
public enum EnumSmsChannel {

    ALIYUN("ALIYUN_SNS","阿里大鱼短信通道");

    /**
     * 通道名称
     */
    private String name;
    /**
     * 通道描述
     */
    private String description;

    EnumSmsChannel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
