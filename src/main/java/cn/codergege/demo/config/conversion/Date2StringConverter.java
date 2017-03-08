package cn.codergege.demo.config.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Date2StringConverter implements Converter<Date, String> {

    @Autowired
    private MessageSource messageSource;

    public Date2StringConverter() {}

    @Override
    public String convert(Date source) {
        // 1) locale 为 null 或 Locale.CHINA, 默认去 messages_zh_CN.properties 中去找
        // locale 为 Locale.US, 就去 messages_en_US.properties 中去找
        // 2) 没找到对应的 key, 就去 messages.properties 中找
        // 3) 还没找到, 就用 yyyy-MM-dd 这个默认值
        String pattern = messageSource.getMessage("date.format", null, "yyyy-MM-dd", Locale.CHINA);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        System.out.println("-----------> converter runs");

        return sdf.format(source);
    }
}
