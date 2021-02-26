package lt.asprogramuoju.service.customerbill.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

/**
 * Date util class
 * <br/>Class support {@link Calendar}, {@link Date}, {@link OffsetDateTime} class conversions and vice-versa conversions
 * <br/>Class consist required methods for any usages
 *
 * @author Pavel Vrublevskij
 * @version 1.0
 * @since 2021-03-01
 */
public final class DateUtil {

    private DateUtil() {
    }

    public static Calendar getFuture(int countOfDay) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, countOfDay);
        return cal;
    }

    public static int getMonthCountFromDates(Calendar startDate, Calendar endDate) {
        long millis = Math.abs(endDate.getTimeInMillis() - startDate.getTimeInMillis());
        long oneMonth = 30L * 24 * 60 * 60 * 1000;
        return (int) Math.ceil(millis * 1.00 / oneMonth * 1.00);
    }

    /**
     * Convert by {@link DateFormat} and will be changed by #monthCount parameter to the future from #date.
     *
     * @param dateFormatter
     * @param dateStr          i.e 20200115
     * @return Calendar
     */
    public static Calendar toCalendar(DateFormat dateFormatter, String dateStr) {
        try {
            Date date = dateFormatter.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert by {@link DateFormat} and will be changed by #monthCount parameter to the future from #date.
     *
     * @param dateFormatter
     * @param date          i.e 20200115
     * @param monthCount
     * @return Calendar
     */
    public static Calendar toCalendar(DateFormat dateFormatter, String date, int monthCount) {
        Calendar cal = DateUtil.toCalendar(dateFormatter, date);
        cal.add(Calendar.MONTH, monthCount);

        return cal;
    }

    /**
     * Convert by {@link DateFormat} and will be changed date to last month date
     *
     * @param dateFormatter
     * @param date          i.e 20200115
     * @return Calendar date by last month date
     */
    public static Calendar toCalendarLastMonthDate(DateFormat dateFormatter, String date) {
        return DateUtil.toCalendarLastMonthDate(DateUtil.toCalendar(dateFormatter, date));
    }

    /**
     * Convert current date to first month date, ie. 2020-08-24 -> 2020-08-31
     * @param cal
     * @return
     */
    public static Calendar toCalendarLastMonthDate(Calendar cal) {
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -1);

        return cal;
    }

    /**
     * Convert current date to first month date, ie. 2020-08-24 -> 2020-08-01
     * @param cal
     * @return
     */
    public static Calendar toCalendarFirstMonthDate(Calendar cal) {
        cal.set(Calendar.DATE, 1);

        return cal;
    }

    public static OffsetDateTime toOffsetDateTime(Calendar calendar) {
        return OffsetDateTime.of(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH),
                0, 0, 0, 0,
                ZoneOffset.UTC
        );
    }

    /**
     * @param date
     * @return Converted Date to OffsetDateTime by UTC Offset Zone
     */
    public static OffsetDateTime toOffsetDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.UTC);
    }

    /**
     * Convert by {@link DateFormat} to String value
     *
     * @param dateFormatter
     * @param cal
     * @return String value by format
     */
    public static String calendarToString(DateFormat dateFormatter, Calendar cal) {
        return dateFormatter.format(cal.getTime());
    }

    /**
     * Convert Date to Calendar
     *
     * @param date
     * @return
     */
    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    /**
     * Convert Calendar to Date
     *
     * @param calendar
     * @return
     */
    public static Date toDate(Calendar calendar) {
        return calendar.getTime();
    }
}
