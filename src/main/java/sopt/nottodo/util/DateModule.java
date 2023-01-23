package sopt.nottodo.util;

import sopt.nottodo.util.exception.CustomException;
import sopt.nottodo.util.response.ResponseCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateModule {

    private static final Integer MONDAY = 1;
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static Date getToday(String today) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            return format.parse(today);
        } catch (ParseException e) {
            throw new CustomException(ResponseCode.INVALID_DATE_FORMAT);
        }
    }
}
