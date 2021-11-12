package com.bingo.common;


public enum MessageType {

    OP_OPEN("打开APP", "1"),
    OP_BT_LIKE("点击" + ConstantConfig.BTN_NAME_LIKE + "按钮", "2"),
    OP_BT_UNLIKE("点击" + ConstantConfig.BTN_NAME_NLIKE + "按钮", "3"),
    OP_BT_CLOSE("尝试点击关闭按钮", "4"),
    OP_CLOSE_FAIL("APP关闭失败", "5"),
    OP_CLOSE("正常关闭APP", "0");

    private String key;

    private String value;

    MessageType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
