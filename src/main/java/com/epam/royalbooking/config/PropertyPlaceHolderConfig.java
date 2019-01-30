package com.epam.royalbooking.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:userregex.properties")
public class PropertyPlaceHolderConfig {
}
