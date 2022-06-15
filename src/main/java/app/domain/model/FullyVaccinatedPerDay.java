package app.domain.model;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

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

    public boolean save (File file, List<FullyVaccinatedPerDay> fullyVaccinatedPerDayList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(fullyVaccinatedPerDayList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s               %d", day, count);
    }
}
