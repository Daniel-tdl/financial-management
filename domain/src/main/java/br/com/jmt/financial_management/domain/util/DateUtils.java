package br.com.jmt.financial_management.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.*;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.NONE)
public class DateUtils {

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static LocalDate convertToLocalDateTime(String stringDate)  {
        return LocalDate.from(
                DateTimeFormatter
                        .ofPattern(DATE_FORMAT)
                        .parse(stringDate));
    }
}
