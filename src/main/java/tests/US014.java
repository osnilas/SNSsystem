package tests;

import app.controller.AddSNSfromCSVController;
import app.controller.App;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.AddUserFromCSVUI;
import mappers.dto.dtoSNSuser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class US014 {


    @Test
    void validateUserInvalidEmail(){
        dtoSNSuser dto=new dtoSNSuser("J","Male",Constants.BIRTH_TESTER,"Rua","joao@gmail.com",912422195,111111,111111111);
        AddSNSfromCSVController ctlr=new AddSNSfromCSVController();
        boolean test= ctlr.saveSNSuser(dto);
        assertFalse(test);
    }

    @Test
    void validateUserValidEmail(){
        dtoSNSuser dto=new dtoSNSuser("J","Male",Constants.BIRTH_TESTER,"Rua","jo@gmail.com",912422195,111111,111111111);
        AddSNSfromCSVController ctlr=new AddSNSfromCSVController();
        boolean test= ctlr.saveSNSuser(dto);
        assertFalse(test);
    }

    @Test
    void validateUserInvalidSNSnumber(){
        dtoSNSuser dto=new dtoSNSuser("J","Male",Constants.BIRTH_TESTER,"Rua","joo@gmail.com",912422195,22207750,111111111);
        AddSNSfromCSVController ctlr=new AddSNSfromCSVController();
        boolean test= ctlr.saveSNSuser(dto);
        assertFalse(test);
    }

    @Test
    void validateUserValidSNSnumber(){
        dtoSNSuser dto=new dtoSNSuser("J","Male",Constants.BIRTH_TESTER,"Rua","jo@gmail.com",912422195,111111,111111111);
        AddSNSfromCSVController ctlr=new AddSNSfromCSVController();
        boolean test= ctlr.saveSNSuser(dto);
        assertFalse(test);
    }

    @Test
    void validateUserInvalidCCnumber(){
        dtoSNSuser dto=new dtoSNSuser("J","Male",Constants.BIRTH_TESTER,"Rua","jo@gmail.com",912422195,111111,15467765);
        AddSNSfromCSVController ctlr=new AddSNSfromCSVController();
        boolean test= ctlr.saveSNSuser(dto);
        assertFalse(test);
    }

    @Test
    void validateUserValidCCnumber(){
        dtoSNSuser dto=new dtoSNSuser("J","Male",Constants.BIRTH_TESTER,"Rua","jo@gmail.com",912422195,111111,12345678);
        AddSNSfromCSVController ctlr=new AddSNSfromCSVController();
        boolean test= ctlr.saveSNSuser(dto);
        assertFalse(test);
    }

    @Test
    void validateDateValid() {
        // Arrange
        String date="11/09/2022";
        // Act
        boolean test=Validate.validateDate(date);
        //Assert
        assertTrue(test);
    }

    @Test
    void validateDateInvalid() {
        // Arrange
        String date="11 9";
        // Act
        boolean test=Validate.validateDate(date);
        //Assert
        assertFalse(test);
    }

    @Test
    void validateDateNull() {
        // Arrange
        String date=null;
        // Act
        boolean test=Validate.validateDate(date);
        //Assert
        assertFalse(test);
    }

    @Test
    void validateSexValid() {
        // Arrange
        String sex=Constants.SexList[1];
        // Act
        boolean test=Validate.validateSex(sex);
        //Assert
        assertTrue(test);
    }

    @Test
    void validateSexInvalid() {
        // Arrange
        String sex="Teste java";
        // Act
        boolean test=Validate.validateSex(sex);
        //Assert
        assertFalse(test);
    }

    @Test
    void validateSexNull() {
        // Arrange
        String sex=null;
        // Act
        boolean test=Validate.validateSex(sex);
        //Assert
        assertFalse(test);
    }
}