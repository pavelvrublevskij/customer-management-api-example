package lt.asprogramuoju.service.customerbill.core.util;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilTest {

    @Test
    void getMonthCountFromDates() {
        assertEquals(12,
                DateUtil.getMonthCountFromDates(
                        new GregorianCalendar(2020, Calendar.DECEMBER, 1),
                        new GregorianCalendar(2020, Calendar.JANUARY, 1)
                ));

        assertEquals(13,
                DateUtil.getMonthCountFromDates(
                        new GregorianCalendar(2021, Calendar.JANUARY, 1),
                        new GregorianCalendar(2020, Calendar.JANUARY, 1)
                ));

        assertEquals(2,
                DateUtil.getMonthCountFromDates(
                        new GregorianCalendar(2020, Calendar.FEBRUARY, 1),
                        new GregorianCalendar(2020, Calendar.JANUARY, 1)
                ));

        assertEquals(2,
                DateUtil.getMonthCountFromDates(
                        new GregorianCalendar(2020, Calendar.JANUARY, 1),
                        new GregorianCalendar(2020, Calendar.FEBRUARY, 2)
                ));

        assertEquals(0,
                DateUtil.getMonthCountFromDates(
                        new GregorianCalendar(2020, Calendar.JANUARY, 1),
                        new GregorianCalendar(2020, Calendar.JANUARY, 1)
                ));

        assertEquals(1,
                DateUtil.getMonthCountFromDates(
                        new GregorianCalendar(2020, Calendar.JANUARY, 1),
                        new GregorianCalendar(2020, Calendar.JANUARY, 2)
                ));
    }

    @Test
    void getFuture() {
        Calendar expected = new GregorianCalendar();
        expected.add(Calendar.DAY_OF_MONTH, 1);

        Calendar result = DateUtil.getFuture(1);

        assertEquals(expected.get(Calendar.YEAR), result.get(Calendar.YEAR));
        assertEquals(expected.get(Calendar.MONTH), result.get(Calendar.MONTH));
        assertEquals(expected.get(Calendar.DAY_OF_MONTH), result.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void toCalendar() {
        Calendar expected = new GregorianCalendar();
        expected.set(2020, Calendar.DECEMBER, 24);

        Calendar result = DateUtil.toCalendar(new SimpleDateFormat("yyyyMMdd"), "20201224");

        assertEquals(expected.get(Calendar.YEAR), result.get(Calendar.YEAR));
        assertEquals(expected.get(Calendar.MONTH), result.get(Calendar.MONTH));
        assertEquals(expected.get(Calendar.DAY_OF_MONTH), result.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void toCalendarLastMonthDate() {
        //given
        Calendar expected = new GregorianCalendar();
        expected.set(2020, Calendar.DECEMBER, 31);

        Calendar expected1 = new GregorianCalendar();
        expected1.set(2021, Calendar.JANUARY, 31);

        //when
        Calendar result1 = DateUtil.toCalendarLastMonthDate(new SimpleDateFormat("yyyyMMdd"), "20201224");
        Calendar result2 = DateUtil.toCalendarLastMonthDate(new SimpleDateFormat("yyyyMMdd"), "20201231");
        Calendar result3 = DateUtil.toCalendarLastMonthDate(new SimpleDateFormat("yyyyMMdd"), "20210131");

        //then
        assertEquals(expected.get(Calendar.YEAR), result1.get(Calendar.YEAR));
        assertEquals(expected.get(Calendar.MONTH), result1.get(Calendar.MONTH));
        assertEquals(expected.get(Calendar.DAY_OF_MONTH), result1.get(Calendar.DAY_OF_MONTH));

        assertEquals(expected.get(Calendar.YEAR), result2.get(Calendar.YEAR));
        assertEquals(expected.get(Calendar.MONTH), result2.get(Calendar.MONTH));
        assertEquals(expected.get(Calendar.DAY_OF_MONTH), result2.get(Calendar.DAY_OF_MONTH));

        assertEquals(expected1.get(Calendar.YEAR), result3.get(Calendar.YEAR));
        assertEquals(expected1.get(Calendar.MONTH), result3.get(Calendar.MONTH));
        assertEquals(expected1.get(Calendar.DAY_OF_MONTH), result3.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void toOffsetDateTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(2020, Calendar.DECEMBER, 11);

        OffsetDateTime expected = OffsetDateTime.of(
                2020,
                12,
                11,
                0, 0, 0, 0,
                ZoneOffset.UTC);

        assertEquals(expected, DateUtil.toOffsetDateTime(calendar));
    }
}
