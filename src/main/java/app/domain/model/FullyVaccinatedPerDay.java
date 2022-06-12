package app.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FullyVaccinatedPerDay implements Serializable {

    private LocalDate day = LocalDate.now();
    private int count = 1;

    public void updateTotalNumberOfFullyVaccinated () {
        count++;
    }

    public int getCount() {
        return count;
    }

    public boolean checkIfSameDay (LocalDate date) {
        return date.isEqual(day);
    }

    public LocalDate getDay() {
        return day;
    }
}
