package com.logibeat.cloud.common.enums;

public enum PayChannel {
    ALIPAY('1'),
    WECHAT('2'),
    BANK('3');

    private final char code;

    PayChannel(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }
}
