package app.ui.console;

import app.controller.App;
import app.controller.VaccinationScheduleController;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.Validate;
import app.ui.console.utils.Utils;
import mappers.dto.dtoScheduleVaccine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleVaccinationUI implements Runnable {

    private VaccinationScheduleController ctlr;
    private App app;

    public ScheduleVaccinationUI() {
        ctlr = new VaccinationScheduleController();
    }

    @Override
    public void run() {
        try {
            boolean sucess = Schedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean Schedule() throws Exception {
        boolean sucess = false;
        int snsNumber = 0;
        TypeVaccine typeVaccine = null;
        LocalDateTime appoimmentDate = null;
        VaccinationFacility vaccinationFacility = null;

        if (ctlr.checkIfVaccinationFaciltyListIsEmpty()) {
            snsNumber = getSNSnumber();
            vaccinationFacility = selectVaccinationFacility();
            typeVaccine = getTypeVaccineFromVaccinationFacility(vaccinationFacility);
            appoimmentDate = getDateAppoiment(vaccinationFacility);
        }
        if (snsNumber != 0 && typeVaccine != null && appoimmentDate != null) {

            dtoScheduleVaccine dto = new dtoScheduleVaccine(snsNumber, appoimmentDate, typeVaccine);
            ctlr.createSchedule(dto);
            Utils.printText(ctlr.printSchedule(vaccinationFacility));
            if (Utils.confirm("Is this correct?")) {
                if (ctlr.validateScheduleVaccine(dto.getTypeVaccine())) {
                    sucess = ctlr.saveSchedule(vaccinationFacility);
                }
            }

        }
        if (sucess) {
            Utils.printText("-----Appoiment added sucessfully-----");
        } else {
            Utils.printText("----Appoiment creation failed----");
        }


        return sucess;
    }

    private int getSNSnumber() throws Exception {
        int snsNubmer;
        do {
            snsNubmer = Utils.readIntegerFromConsole("Enter SNS number");
            if (!Validate.validateCC(snsNubmer)) {
                Utils.printText("Input a valid SNS number, it has 8 digits");
            }
        } while (!Validate.validateCC(snsNubmer));

        if (!ctlr.checkIfSNSuserExists(snsNubmer)) {
            throw new Exception("SNS user not registered on system");
        }
        return snsNubmer;
    }

    private boolean ValidateAppoimentTime(LocalDateTime date, VaccinationFacility center) {
        boolean flag = false;
        int count = 0;
        List<VaccinationAppointment> scheduleList = center.getVaccinationScheduleList();
        for (int i = 0; i < scheduleList.size(); i++) {
            if (scheduleList.get(i).isAppointmentSameTime(date)) {
                count++;
                if (count == center.getMaximumNumberOfVaccinesPerSlot()) {
                    flag = false;
                    break;
                }
            }
            flag = true;
        }
        return flag;
    }

    public LocalDateTime getDateAppoiment(VaccinationFacility facility) {
        LocalDate date = null;
        LocalDateTime dateTime = null;
        boolean flag = true;
        do {
            try {
                date = getDate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (date == null);
        do {
            try {
                dateTime = getTime(facility, date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (dateTime == null);
        return dateTime;
    }

    private LocalDateTime getTime(VaccinationFacility facility, LocalDate date) throws Exception {
        Boolean flag;
        LocalDateTime opening = LocalDateTime.of(date, facility.getOpeningHours());
        LocalDateTime closing = LocalDateTime.of(date, facility.getClosingHours());

        List<LocalDateTime> timeSlots = new ArrayList<>();
        LocalDateTime temp = opening;

        int index = 0;
        flag = true;
        do {
            timeSlots.add(temp);
            temp = temp.plusMinutes(facility.getSlotDuration());
        } while (closing.isAfter(temp));

        do {
            index = Utils.showAndSelectIndex(timeSlots, "Select a time");
            if (index == -1) {
                throw new Exception("Time not chosen");
            }
            if (!ValidateAppoimentTime(timeSlots.get(index), facility)) {
                Utils.printText("Slot:\n" + timeSlots.get(index).format(Constants.DATE_TIME_FORMATTER) + " already full");
                flag = !ValidateAppoimentTime(timeSlots.get(index), facility);
            }
            flag = ValidateAppoimentTime(timeSlots.get(index), facility);
        } while (!flag);
        return timeSlots.get(index);
    }

    private LocalDate getDate() throws Exception {
        int count = 0;
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate inicial = LocalDate.now();
        LocalDate temp = inicial;
        LocalDate end = inicial.plusMonths(1);
        do {
            dateList.add(temp);
            temp = temp.plusDays(1);
        } while (temp.isBefore(end));
        Utils.showDate(dateList, "Select a date");
        int index = Utils.selectsIndex(dateList);
        if (index == -1) {
            throw new Exception("No date chosen");
        }
        return dateList.get(index);
    }

    public VaccinationFacility selectVaccinationFacility() throws Exception {
        List<VaccinationFacility> list = ctlr.getVaccinationFacilityList();
        Utils.showVaccinationFacility(list, "Select vaccination facility");
        int index = Utils.selectsIndex(list);
        if (index == -1) {
            throw new Exception("No vaccination facility chosen");
        }
        return list.get(index);
    }

    public TypeVaccine getTypeVaccineFromVaccinationFacility(VaccinationFacility facility) throws Exception {
        if (facility instanceof MassVaccinationCenter) {
            return getTypeVaccineFromVaccinationCenter((MassVaccinationCenter) facility);
        } else {
            return getTypeVaccineFromHealthCareCenter((HealthCareCenter) facility);
        }
    }

    private TypeVaccine getTypeVaccineFromVaccinationCenter(MassVaccinationCenter center) throws Exception {
        String typeVaccine = center.getTypeOfVaccine().getName();
        Utils.printText("Vaccine of this vaccination center:");
        Utils.printText(typeVaccine);
        Utils.printText("The DGS recommends:" + Constants.TYPE_VACCINE_RECOMMENDED.getName());
        if (Utils.confirm("Confirms type of Vaccine?")) {
            return center.getTypeOfVaccine();
        }
        throw new Exception("Vaccine type not chosen");
    }

    private TypeVaccine getTypeVaccineFromHealthCareCenter(HealthCareCenter center) {
        List<TypeVaccine> typeVaccineNameList = new ArrayList<>();
        for (int i = 0; i < center.getTypeVaccineList().size(); i++) {
            typeVaccineNameList.addAll(center.getTypeVaccineList());
        }
        Utils.showTypeVaccinne(typeVaccineNameList, "Select vaccine");
        Utils.printText("The DGS recommends:" + Constants.TYPE_VACCINE_RECOMMENDED.getName());
        int index = Utils.selectsIndex(typeVaccineNameList);
        return center.getTypeVaccineList().get(index);
    }

    private boolean checkIfSNSuser() {
        return ctlr.checkIfSNSuser();
    }
}
