package task_scheduler;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
	
	static LocalTime amStart = LocalTime.of(9, 0);
	static LocalTime pmStart = LocalTime.of(13, 30);
	static Duration workPeriodLength = Duration.ofHours(3).plusMinutes(30);

	public static List<WorkPeriod> generateWorkPeriods(LocalDate date, int dayCount) {
		List<LocalDate> workingDays = generateWorkingDays(date, dayCount);
		return generateWorkPeriods(workingDays, amStart, workPeriodLength, pmStart, workPeriodLength);
	}
	
	public static List<WorkPeriod> generateWorkPeriods(List<LocalDate> days, LocalTime amStart, Duration amDuration, LocalTime pmStart, Duration pmDuration) {
		 List<WorkPeriod> periods = new ArrayList<>();
		 for (LocalDate day : days) {
			 LocalDateTime thisAmStart = LocalDateTime.of(day, amStart);
			 periods.add(new WorkPeriod(thisAmStart, thisAmStart.plus(amDuration)));
			 LocalDateTime thisPmStart = LocalDateTime.of(day, pmStart);
			 periods.add(new WorkPeriod(thisPmStart, thisAmStart.plus(pmDuration)));
		 }
		return periods;
	}
	
	
	private static List<LocalDate> generateWorkingDays(LocalDate startDate, int dayCount) {
		return Stream.iterate(startDate, d -> d.plusDays(1))
				.filter(Utils::isWorkingDay)
				.limit(dayCount)
				.collect(Collectors.toList());
	}

	private static boolean isWorkingDay(LocalDate date) {
		DayOfWeek day = date.getDayOfWeek();
		return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
	}

}
