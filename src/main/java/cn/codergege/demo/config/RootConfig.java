package cn.codergege.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan( value = { "cn.codergege.demo" },
    excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@Import({DataConfig.class})
public class RootConfig {
}

			