package com.example.kopringtest.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@ConfigurationPropertiesScan(basePackages = ["com.example.kopringtest"])
@Configuration
class PropertiesConfig {
}