package cn.codergege.demo.config;

import cn.codergege.demo.config.conversion.Date2StringConverter;
import cn.codergege.demo.config.conversion.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;

@Configuration
@EnableWebMvc
@ComponentScan("cn.codergege.demo")
@Import(value = {ThymeleafConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

    // 处理静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*
     *  Message externalization/internationalization
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    // Conversion Service
    // 注册 Converter, Formatter
    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        // 使用 date2StringConverter 与 dateFormatter 都可以完成对日期的格式化显示, 随便选一个就行
        // 如果同时使用两个, 后注册的起作用:
        //      thymeleaf ${{date}} 需要从 java.util.Date 转成 String, dateFormatter 与 date2StringConverter
        //      都可以完成这个任务, 那么后注册的起作用.
        // todo form 中 String 日期转后台的 java 对象时, 还需要一个 converter, String to Date
        // todo dateFormatter 已经可以 String to Date 了.
        registry.addConverter(date2StringConverter());
        // 后注册, 这个 dateFormatter 起作用
        registry.addFormatter(dateFormatter());
    }

    @Bean
    public Converter<Date, String> date2StringConverter() {
        Converter<Date, String> date2StringConverter = new Date2StringConverter();
        return date2StringConverter;
    }

    @Bean
    public Formatter<Date> dateFormatter() {
        return new DateFormatter();
    }

}
			