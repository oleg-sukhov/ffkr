package ua.vn.ffkr.article.util;

import java.time.LocalDateTime;

import static ua.vn.ffkr.article.crawler.parser.MonthCyrillicDictionary.getMonthByCyrillicCode;

public final class DateUtil {

    //TODO: This method needs to reviewed

    public static LocalDateTime fromPattern(String pattern) {
        String[] dateTimeParts = pattern.replace(',', ' ').split(" ");
        int day = Integer.parseInt(dateTimeParts[0]);
        int month = getMonthByCyrillicCode(dateTimeParts[1]).getValue();
        int year = Integer.parseInt(dateTimeParts[2]);
        String[] timeParts = dateTimeParts[4].split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        return LocalDateTime.of(year, month, day, hour, minute);
    }
}
