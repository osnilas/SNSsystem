package tests;

import app.domain.shared.Validate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class US09 {

    @Test
    void validateEmailNull(){
        String NULL = null;
        boolean result = Validate.validateEmail(NULL);
        assertFalse(result);
    }

    @Test
    void validateEmailBlank() {
        String blank = "  ";
        boolean result= Validate.validateEmail(blank);
        assertFalse(result);
    }

    @Test
    void validateEmailFormatInvalid() {
        String email = "email";
        boolean result= Validate.validateEmail(email);
        assertFalse(result);
    }

    @Test
    void validateEmailFormat() {
        String email = "admin@lei.sem2.pt";
        boolean result= Validate.validateEmail(email);
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
    void validateWebsiteAdressNull() {
        String NULL = null;
        boolean result= Validate.validateWebsiteAdress(NULL);
        assertFalse(result);
    }

    @Test
    void validateWebsiteAdressBlank() {
        String blank = "  ";
        boolean result= Validate.validateWebsiteAdress(blank);
        assertFalse(result);
    }

    @Test
    void validateWebsiteAdressFormatInvalid() {
        String url = "url";
        boolean result= Validate.validateWebsiteAdress(url);
        assertFalse(result);
    }

    @Test
    void validateWebsiteAdressFormat() {
        String url = "www.isep.pt";
        boolean result= Validate.validateEmail(url);
        assertTrue(result);
    }

}
