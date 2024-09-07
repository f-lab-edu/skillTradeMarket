package com.flab.skilltrademarket.config;

import com.flab.skilltrademarket.global.security.resolver.AuthenticationUserResolver;
import com.siot.IamportRestClient.IamportClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationUserResolver authenticationUserResolver;
    private final IamportConfigurationProperties properties;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticationUserResolver);
    }

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(properties.apiKey(), properties.apiSecret());
    }
}
