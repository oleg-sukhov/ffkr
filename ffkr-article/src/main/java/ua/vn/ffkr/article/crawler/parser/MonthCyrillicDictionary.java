package ua.vn.ffkr.article.crawler.parser;

import java.time.Month;

public enum MonthCyrillicDictionary {
    JANUARY(Month.JANUARY, "\u044F\u043D\u0432\u0430\u0440\u044F"),
    FEBRUARY(Month.FEBRUARY, "\u0444\u0435\u0432\u0440\u0435\u043B\u044F"),
    MARCH(Month.MARCH, "\u043C\u0430\u0440\u0442\u0430"),
    APRIL(Month.APRIL, "\u0430\u043F\u0440\u0435\u043B\u044F"),
    MAY(Month.MAY, "\u043C\u0430\u044F"),
    JUNE(Month.JUNE, "\u0438\u044E\u043D\u044F"),
    JULY(Month.JULY, "\u0438\u044E\u043B\u044F"),
    AUGUST(Month.AUGUST, "\u0430\u0432\u0433\u0443\u0441\u0442\u0430"),
    SEPTEMBER(Month.SEPTEMBER, "\u0441\u0435\u043D\u0442\u044F\u0431\u0440\u044F"),
    OCTOBER(Month.OCTOBER, "\u043E\u043A\u0442\u044F\u0431\u0440\u044F"),
    NOVEMBER(Month.NOVEMBER, "\u043D\u043E\u044F\u0431\u0440\u044F"),
    DECEMBER(Month.DECEMBER, "\u0434\u0435\u043A\u0430\u0431\u0440\u044F");

    MonthCyrillicDictionary(Month month, String cyrillicCodeBytes) {
        this.month = month;
        this.cyrillicCodeBytes = cyrillicCodeBytes;
    }

    private Month month;
    private String cyrillicCodeBytes;

    public Month getMonth() {
        return month;
    }

    public String getCyrillicCodeBytes() {
        return cyrillicCodeBytes;
    }

    public static Month getMonthByCyrillicCode(final String cyrillicCode) {
        for (MonthCyrillicDictionary monthCyrillicDictionaryItem : values()) {
            if (monthCyrillicDictionaryItem.getCyrillicCodeBytes().equalsIgnoreCase(cyrillicCode)) {
                return monthCyrillicDictionaryItem.getMonth();
            }
        }

        throw new IllegalArgumentException("Cannot find appropriate month for cyrillic code: " + cyrillicCode);
    }
}
