package app.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

public class FullyVaccinatedPerDay implements Serializable {

    private LocalDate day = LocalDate.now();
    private int count = 1;

    public FullyVaccinatedPerDay(LocalDate day, int count) {
        this.day = day;
        this.count = count;
    }

    public FullyVaccinatedPerDay () {
    }

    public void updateTotalNumberOfFullyVaccinated () {
        int temp = Integer.parseInt(String.valueOf(count));
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

    @Override
    public String toString() {
        return String.format("%s  %d", day, count);
    }
}
