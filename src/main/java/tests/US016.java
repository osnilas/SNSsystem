package tests;

import app.domain.shared.Validate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class US016 {

    @Test

    void validateDateIsValid() {
        // Arrange
        String date="14/03/2022";
        // Act
        boolean test= Validate.validateDateInPast(date);
        //Assert
        assertTrue(test);
    }

    @Test

    void validateDateFormatInvalid() {
        // Arrange
        String date="2022/03/14";
        // Act
        boolean test= Validate.validateDateInPast(date);
        //Assert
        assertFalse(test);
    }

    @Test

    void validateDateNotFromPast() {
        // Arrange
        String date="23/05/2024";
        // Act
        boolean test= Validate.validateDateInPast(date);
        //Assert
        assertFalse(test);
    }


    }
