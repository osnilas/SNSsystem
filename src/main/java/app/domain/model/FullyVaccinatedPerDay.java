package app.domain.model;

import java.io.*;
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

    public boolean save (String filePath, StringBuilder sb) {
        WriteToFile.write(filePath, sb);
        return true;
        /*try {
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
        }*/
    }

    @Override
    public String toString() {
        return String.format("%s               %d", day, count);
    }
}
