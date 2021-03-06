package br.com.grauzely.endereco.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataUtil {
	
	// O padr�o usado para convers�o. Mude como quiser. 
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    
    // O formatador de data.
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    public static boolean validDate(String dateString) {
        // Tenta converter o String.
        return DataUtil.parse(dateString) != null;
    }
    
}
