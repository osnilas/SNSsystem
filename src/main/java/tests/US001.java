package tests;

import app.controller.AddSNSfromCSVController;
import app.controller.VaccinationScheduleController;
import app.domain.model.SNSuser;
import app.domain.shared.Constants;
import app.ui.console.ScheduleVaccinationUI;
import mappers.dto.MapperSNSuser;
import mappers.dto.dtoSNSuser;
import mappers.dto.dtoScheduleVaccine;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class US001 {

    /**
     * For this test I created 3 SNS users:
     * Joao Veiga, with SNS number 222077500.He took the covid vaccine .He took first dose on the 1st of April 2022 and the second dose on the 8th of March 2022.
     * Maria Santos, with SNS number 122077500. She didn't take any vaccine yet.
     * Pedro Cardoso, with SNS number 122137500. He took the covid vaccine. She took the first dose on the 1st of April.
     */

    @Test
    void validSchedule(){
        VaccinationScheduleController ctlr=new VaccinationScheduleController();
        // Arrange
        boolean flag=true;
        dtoScheduleVaccine dto=new dtoScheduleVaccine(122137500,LocalDateTime.of(2022,6,1,12,00),Constants.TYPE_VACCINE_TESTER2);
        ctlr.createScheduleFromDTO(dto);
        ctlr.setVaccinationFacility(0);
        // Act
        flag=ctlr.validateScheduleVaccine();
        //Assert
        assertTrue(flag);
    }

    @Test
    void invalidTooSoon(){
        VaccinationScheduleController ctlr=new VaccinationScheduleController();
        // Arrange
        boolean flag=true;
        dtoScheduleVaccine dto=new dtoScheduleVaccine(122137500,LocalDateTime.of(2022,4,30,12,00),Constants.TYPE_VACCINE_TESTER1);
        ctlr.createScheduleFromDTO(dto);
        ctlr.setVaccinationFacility(0);
        // Act
        flag=ctlr.validateScheduleVaccine();
        //Assert
        assertFalse(flag);
    }

    @Test
    void invalidAllreadyTookAll(){
        VaccinationScheduleController ctlr=new VaccinationScheduleController();
        // Arrange
        boolean flag=true;
        dtoScheduleVaccine dto=new dtoScheduleVaccine(222077500,LocalDateTime.of(2022,4,30,12,00),Constants.TYPE_VACCINE_TESTER1);
        ctlr.createScheduleFromDTO(dto);
        ctlr.setVaccinationFacility(0);
        // Act
        flag=ctlr.validateScheduleVaccine();
        //Assert
        assertFalse(flag);
    }

    @Test
    void invalidAlreadyHasAppoiment(){
        VaccinationScheduleController ctlr=new VaccinationScheduleController();
        // Arrange
        boolean flag=true;
        dtoScheduleVaccine dto=new dtoScheduleVaccine(133077500,LocalDateTime.of(2022,4,30,12,00),Constants.TYPE_VACCINE_TESTER1);
        ctlr.createScheduleFromDTO(dto);
        ctlr.setVaccinationFacility(0);
        // Act
        flag=ctlr.validateScheduleVaccine();
        //Assert
        assertFalse(flag);
    }

    @Test
    void invalidScheduleSNSnumber(){
        VaccinationScheduleController ctlr=new VaccinationScheduleController();
        // Arrange
        boolean flag=true;
        dtoScheduleVaccine dto=new dtoScheduleVaccine(0,LocalDateTime.of(2022,4,30,12,00),Constants.TYPE_VACCINE_TESTER1);
        ctlr.createScheduleFromDTO(dto);
        ctlr.setVaccinationFacility(0);
        // Act
        flag=ctlr.validateCreationSchedule();
        //Assert
        assertFalse(flag);
    }

    @Test
    void invalidScheduleDate(){
        VaccinationScheduleController ctlr=new VaccinationScheduleController();
        // Arrange
        boolean flag=true;
        dtoScheduleVaccine dto=new dtoScheduleVaccine(222077500,null,Constants.TYPE_VACCINE_TESTER1);
        ctlr.createScheduleFromDTO(dto);
        ctlr.setVaccinationFacility(0);
        // Act
        flag=ctlr.validateCreationSchedule();
        //Assert
        assertFalse(flag);
    }

    @Test
    void invalidScheduleVaccine(){
        VaccinationScheduleController ctlr=new VaccinationScheduleController();
        // Arrange
        boolean flag=true;
        dtoScheduleVaccine dto=new dtoScheduleVaccine(222077500,LocalDateTime.of(2022,4,30,12,00),null);
        ctlr.createScheduleFromDTO(dto);
        ctlr.setVaccinationFacility(0);
        // Act
        flag=ctlr.validateCreationSchedule();
        //Assert
        assertFalse(flag);
    }

    @Test
    void invalidScheduleFacility(){
        VaccinationScheduleController ctlr=new VaccinationScheduleController();
        // Arrange
        boolean flag=true;
        dtoScheduleVaccine dto=new dtoScheduleVaccine(222077500,LocalDateTime.of(2022,4,30,12,00),Constants.TYPE_VACCINE_TESTER1);
        ctlr.createScheduleFromDTO(dto);
        // Act
        flag=ctlr.validateCreationSchedule();
        //Assert
        assertFalse(flag);
    }
}