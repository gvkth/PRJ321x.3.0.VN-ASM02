package vn.funix.ccdn.utilities;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utility {
	public static String getCurrentTimeString() {
		LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
        return date;
	}
	
	public static String getNextMonthDateString() {
		LocalDate currentDate = LocalDate.now();
		LocalDate newDate = currentDate.plusMonths(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = newDate.format(formatter);
        return formattedDate;
	}
	
	public static String ensureUTF8(String data) {
		return new String(data.getBytes(),StandardCharsets.UTF_8);
	}
}
