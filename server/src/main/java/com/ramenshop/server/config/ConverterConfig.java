package com.ramenshop.server.config;

import com.ramenshop.server.converter.MenuItemConverter;
import com.ramenshop.server.converter.RamenConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(new RamenConverter());
        registry.addConverter(new MenuItemConverter());
    }
}
