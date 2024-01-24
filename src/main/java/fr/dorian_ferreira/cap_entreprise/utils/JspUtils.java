package fr.dorian_ferreira.cap_entreprise.utils;

import org.springframework.stereotype.Service;

@Service
public class JspUtils {

    public String excerpt(String text, int size) {
        if (text.length() <= size) return text;
        return text.substring(0, size) + "...";
    }

    public String getCssClas(float rating) {
        if (rating <= 5) return "rating-5";
        if (rating <= 10) return "rating-10";
        if (rating <= 15) return "rating-15";
        if (rating < 20) return "rating-19";
        return "rating-20";
    }

}
