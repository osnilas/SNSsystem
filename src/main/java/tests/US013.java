package tests;

import app.domain.shared.Validate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US013 {

    @Test
    void validateMinimumAge() {
        //Arrange
        List<Integer> age = new ArrayList<>();
        age.add(0);

        //Act
        boolean result = Validate.validateMinimumAge(age, 0);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateMaximumAge() {
        //Arrange
        List<Integer> minAge = new ArrayList<>();
        minAge.add(200);
        List<Integer> maxAge = new ArrayList<>();
        maxAge.add(1);

        //Act
        boolean result = Validate.validateMaximumAge(minAge, maxAge, 0);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateDosage() {
        //Arrange
        List<Double> dosage = new ArrayList<>();
        dosage.add(-30.0);

        //Act
        boolean result = Validate.validateDosage(dosage, 0);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateDoses() {
        //Assert
        List<Integer> doses = new ArrayList<>();
        doses.add(0);

        //Act
        boolean result = Validate.validateDoses(doses, 0);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateVaccineInterval() {
        //Assert
        List<Integer> interval = new ArrayList<>();
        interval.add(0);

        //Act
        boolean result = Validate.validateVaccineInterval(interval, 0);

        //Assert
        assertFalse(result);
    }
}