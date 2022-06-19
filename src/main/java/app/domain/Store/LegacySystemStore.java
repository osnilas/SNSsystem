package app.domain.Store;

import app.domain.model.LegacySystemData;
import app.domain.model.Vaccine;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;
import pt.isep.lei.esoft.auth.domain.model.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;

public class LegacySystemStore {

    private List<LegacySystemData> legacySystemDataList=new ArrayList<>();;

    public LegacySystemStore() {
    }

    public LegacySystemData create(int SNSuserNumber,Vaccine vaccine,int dose,String lotNumber,LocalDateTime arrivalTime,LocalDateTime ScheduledDateTime,LocalDateTime NurseAdministrationDateTime,LocalDateTime LeavingDateTime) {
        return new LegacySystemData(SNSuserNumber, vaccine, dose, lotNumber, arrivalTime, ScheduledDateTime, NurseAdministrationDateTime, LeavingDateTime);
    }
    public void add(List<LegacySystemData> data) {
        if(legacySystemDataList.size()==0) {
            legacySystemDataList.addAll(data);
        }else {
            for (int i = 0; i < legacySystemDataList.size(); i++) {
                for (int j = 0; j < data.size(); j++) {
                    if (legacySystemDataList.get(i).equals(data.get(j))) {
                        legacySystemDataList.add(data.get(j));
                    }
                }
            }
        }
    }


    public boolean checkIfEmpty () {
        return legacySystemDataList.isEmpty() || legacySystemDataList == null;
    }

    public List<LegacySystemData> getAll() {
        return legacySystemDataList;
    }
}
