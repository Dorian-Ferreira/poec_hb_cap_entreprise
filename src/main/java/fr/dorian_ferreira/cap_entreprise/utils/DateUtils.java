package fr.dorian_ferreira.cap_entreprise.utils;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class DateUtils {

    public String getDateFormat(LocalDate localDate, String format) {
        return getDateFormat(localDate.atTime(0, 0), format);
    }

    public String getDateFormat(LocalDateTime localDate, String format) {
        Date date = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
        return new SimpleDateFormat(format).format(date);
    }
}
