package lt.asprogramuoju.service.customerbill.api.converter;

import lt.asprogramuoju.gen.customerbill.model.Money;
import lt.asprogramuoju.gen.customerbill.model.TimePeriod;
import lt.asprogramuoju.service.customerbill.core.util.DateUtil;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.Calendar;

public class ApiConverter {

    private static final String DEFAULT_CURRENCY_UNIT = "EUR";

    public static Money toMoney(String value) {
        return Money.builder()
                .unit(DEFAULT_CURRENCY_UNIT)
                .value(Float.parseFloat(value))
                .build();
    }

    /**
     * Converts BigDecimal to Money DTO
     *
     * @param value
     * @param unit  if null then default currency will be added
     * @return {@link Money} dto
     */
    public static Money toMoney(BigDecimal value, @Nullable String unit) {
        return Money.builder()
                .unit(unit == null ? DEFAULT_CURRENCY_UNIT : unit)
                .value(value.floatValue())
                .build();
    }

    /**
     * @param date
     * @return Converted date to {@link TimePeriod} format
     */
    public static TimePeriod toTimePeriod(Calendar date) {
        return TimePeriod.builder()
                .startDateTime(DateUtil.toOffsetDateTime(DateUtil.toCalendarFirstMonthDate(date)))
                .endDateTime(DateUtil.toOffsetDateTime(DateUtil.toCalendarLastMonthDate(date)))
                .build();
    }

}
