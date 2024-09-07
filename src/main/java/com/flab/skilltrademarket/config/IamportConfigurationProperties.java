package com.flab.skilltrademarket.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "stm.iamport")
public record IamportConfigurationProperties(String apiKey, String apiSecret) {
}
