package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class PersonDataValidator {
    public Validator phoneValidator() {
        return string -> {
            try {
                Long.parseLong(string);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        };
    }

    public Validator sexValidator() {
        return string -> {
            char sex = string.charAt(0);
            return (sex == 'f' || sex == 'm') && string.length() == 1;
        };
    }

    public Validator dateValidator() {
        return string -> {
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.uuuu")
                        .withResolverStyle(ResolverStyle.STRICT);
                if (LocalDate.from(dateFormatter.parse(string)).compareTo(LocalDate.now()) > 0) {
                    throw new IllegalArgumentException("Неверно введенная дата!");
                }
            } catch (DateTimeParseException e) {
                return false;
            }
            return true;
        };
    }
}