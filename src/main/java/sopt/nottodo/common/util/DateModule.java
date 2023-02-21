package sopt.nottodo.common.util;

import sopt.nottodo.common.util.exception.CustomException;
import sopt.nottodo.common.util.response.ResponseCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateModule {

    private static final Integer SUNDAY = 0;
    private static final String DATE_DASH_FORMAT = "yyyy-MM-dd";

    public static Date getToday(String today) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_DASH_FORMAT);
            return format.parse(today);
        } catch (ParseException e) {
            throw new CustomException(ResponseCode.INVALID_DATE_FORMAT);
        }
    }

    public static void validateSunday(Date day) {
        LocalDate localDate = dateToLocalDate(day);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();
        if (dayOfWeekNumber != SUNDAY) {
            throw new CustomException(ResponseCode.NOT_SUNDAY);
        }
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date getWeekAfter(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth(), 0, 0, 0);
        calendar.add(Calendar.DATE, 7);
        return calendar.getTime();
    }
}
