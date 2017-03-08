package cn.codergege.demo.config.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {

    @Autowired
    private MessageSource messageSource;

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        final SimpleDateFormat sdf = createDateFormat(locale);
        return sdf.parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        final SimpleDateFormat sdf = createDateFormat(locale);
        return sdf.format(object);
    }

    private SimpleDateFormat createDateFormat(Locale local) {
        // 1) 根据 页面的 local 去 messages_zh_CN.properties 或 messages_en_US.properties 中去找
        // 2) 没找到对应的 key, 就去 messages.properties 中找
        // 3) 还没找到, 就用 yyyy-MM-dd 这个默认值
        String format = messageSource.getMessage("date.format", null, "yyyy-MM-dd", local);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        System.out.println("-----------> formater runs");
        return sdf;
    }
}
