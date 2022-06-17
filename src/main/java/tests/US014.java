package tests;
import app.controller.AddSNSfromCSVController;
import app.domain.model.SNSuser;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import mappers.dto.MapperSNSuser;
import mappers.dto.dtoSNSuser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class US014 {


    @Test
    void validateUserInvalidEmail(){
        dtoSNSuser dto=new dtoSNSuser("J","Male",Constants.BIRTH_TESTER,"Rua","joao@gmail.com",912422195,111111,111111111,"11");
        AddSNSfromCSVController ctlr=new AddSNSfromCSVController();
        boolean test= ctlr.saveSNSuser(dto);
        assertFalse(test);
    }

    @Test
    void validateUserInvalidSNSnumber(){
        dtoSNSuser dto=new dtoSNSuser("J","Male",Constants.BIRTH_TESTER,"Rua","joo@gmail.com",912422195,22207750,111111111,"11");
        AddSNSfromCSVController ctlr=new AddSNSfromCSVController();
        boolean test= ctlr.saveSNSuser(dto);
        assertFalse(test);
    }

    @Test
    void validateUserInvalidCCnumber(){
        dtoSNSuser dto=new dtoSNSuser("J","Male",Constants.BIRTH_TESTER,"Rua","jo@gmail.com",912422195,111111,15467765,"22");
        AddSNSfromCSVController ctlr=new AddSNSfromCSVController();
        boolean test= ctlr.saveSNSuser(dto);
        assertFalse(test);
    }

    @Test
    void SNSuserCopy(){
        MapperSNSuser mapper=new MapperSNSuser();
        dtoSNSuser dto=mapper.toDTO(Constants.SNS_USER_TESTER_EMPTY);
        AddSNSfromCSVController ctlr=new AddSNSfromCSVController();
        boolean test= ctlr.saveSNSuser(dto);
        assertFalse(test);
    }

    @Test
    void SNSuserValid(){
        MapperSNSuser mapper=new MapperSNSuser();
        SNSuser user=new SNSuser("Carlos",Constants.SexListFull[0],Constants.BIRTH_TESTER,"Rua","carlos.tester@gmail.com",91508799,22206756,15489996,"password");
        dtoSNSuser dto=mapper.toDTO(user);
        AddSNSfromCSVController ctlr=new AddSNSfromCSVController();
        boolean test= ctlr.saveSNSuser(dto);
        assertTrue(test);
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
        String sex=Constants.SexListFull[1];
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