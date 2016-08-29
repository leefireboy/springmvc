package com.libing.common;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by libing on 2016/8/29.
 */
public class MyDateFormatter implements Formatter<Date> {

    public Date parse(String text, Locale locale) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(text);
    }

    public String print(Date object, Locale locale) {
        return null;
    }

}
