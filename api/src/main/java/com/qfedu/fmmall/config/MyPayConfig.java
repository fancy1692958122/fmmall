package com.qfedu.fmmall.config;

import com.github.wxpay.sdk.WXPayConfig;

import  java.io.InputStream;

/**
 * @author fancy
 * @create 2023-02-21 1:00
 */
public class MyPayConfig implements WXPayConfig {

    @Override
    public String getAppID() {
        return "wx632c8f211f8122c6";
    }

    @Override
    public String getMchID() {
        return "1497984412";
    }

    @Override
    public String getKey() {
        return "sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC";
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }


}
