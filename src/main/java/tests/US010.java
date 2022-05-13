package tests;

import app.domain.shared.Validate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class US010 {

    @Test
    void validateEmailNull() {
        // Arrange
        String NULL = null;
        // Act
        boolean result= Validate.validateEmail(NULL);
        //Assert
        assertFalse(result);
    }

    @Test
    void validateEmailBlank() {
        // Arrange
        String blank = "  ";
        // Act
        boolean result= Validate.validateEmail(blank);
        //Assert
        assertFalse(result);
    }

    @Test
    void validateEmailFormatInvalid() {
        // Arrange
        String email = "email";
        // Act
        boolean result= Validate.validateEmail(email);
        //Assert
        assertFalse(result);
    }

    @Test
    void validateEmailFormat() {
        // Arrange
        String email = "admin@lei.sem2.pt";
        // Act
        boolean result= Validate.validateEmail(email);
        //Assert
        assertTrue(result);
    }

    @Test
    void validatePhoneFormatInvalid() {
        // Arrange
        int phone = 1231;
        // Act
        boolean result= Validate.validatePhone(phone);
        //Assert
        assertFalse(result);
    }

    @Test
    void validatePhoneFormat() {
        // Arrange
        int phone = 808919191;
        // Act
        boolean result= Validate.validatePhone(phone);
        //Assert
        assertTrue(result);
    }

    @Test
    void validateCCFormatInvalid() {
        // Arrange
        int cc = 1231;
        // Act
        boolean result= Validate.validateCC(cc);
        //Assert
        assertFalse(result);
    }

    @Test
    void validateCCFormat() {
        // Arrange
        int cc = 15454679;
        // Act
        boolean result= Validate.validateCC(cc);
        //Assert
        assertTrue(result);
    }



}