package task_scheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

public class WorkPeriod {
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	
	public WorkPeriod(LocalDateTime startTime, LocalDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}


	public WorkPeriod(LocalDateTime startTime, Duration duration) {
		this.startTime = startTime;
		this.endTime = startTime.plus(duration);
	}
	
	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	public Optional<WorkPeriod> split(LocalDateTime splitTime) {
		if (startTime.isBefore(splitTime) && splitTime.isBefore(endTime)) {
			WorkPeriod newPeriod = new WorkPeriod(startTime, Duration.between(startTime, splitTime));
			startTime = splitTime;
			return Optional.of(newPeriod);
		} else {
			return Optional.empty();
		}
	}


	public Optional<WorkPeriod> split() {
		LocalDateTime midnight = startTime.toLocalDate().plusDays(1).atStartOfDay();
		return split(midnight);
	}

}
