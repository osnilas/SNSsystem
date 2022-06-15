package app.controller;

import algorithms.sort.BubleSort;
import algorithms.sort.QuickSort;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ImportDataFromLegacySystemController {


    private Company company;
    private App app;
    private ReadDataFromLegacySystem read;
    private List<LegacySystemData> fileVaccinations;

    private VaccinationFacility facility;



    public ImportDataFromLegacySystemController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }



    ArrayList<String> fileData = new ArrayList<>();

   int n = fileData .size();

   public void readData(String filePath) throws Exception {
           this.read = new ReadDataFromLegacySystem(filePath, company.getVaccineList());
           this.fileVaccinations= read.copyDataFromLegacySystem();
   }

   public void save(){
       company.addLegacySystemDataToStore(fileVaccinations);
       //saveAdminstrationSNSuser();
       //company.saveSNSusersListFile();
   }

    private void saveAdminstrationSNSuser() {
        List<SNSuser> snsUsers = company.getSNSuserList();
        VaccineCard vaccineCard;
        boolean found = false;
        for(int i=0;i<fileVaccinations.size();i++){
            found=false;
            for (int j=0;j<snsUsers.size();j++){
                if(snsUsers.get(j).getSNSnumber()==fileVaccinations.get(i).getSNSuserNumber()){
                    found=true;
                    vaccineCard=new VaccineCard(fileVaccinations.get(i).getVaccine(),fileVaccinations.get(i).getNurseAdministrationDateTime(),fileVaccinations.get(i).getDose());
                    snsUsers.get(j).getVaccineCards().add(vaccineCard);
                }
            }
            if(!found){
                Utils.Warning("SNSuser not found","nul","nul").showAndWait();
            }
        }
    }

    public List<String> getInfo(){
       List<String> info = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<fileVaccinations.size();i++){
            sb.setLength(0);
            SNSuser snsUser = company.SNSuserExistsNumber(fileVaccinations.get(i).getSNSuserNumber());
            sb.append(fileVaccinations.get(i).getSNSuserNumber()+"; "
                    +snsUser.getName()+"; "
                    +fileVaccinations.get(i).getVaccine().getName()+"; "
                    +fileVaccinations.get(i).getVaccine().getTypeVaccine().getDescription()+"; "
                    +fileVaccinations.get(i).getDose()+"; "
                    +fileVaccinations.get(i).getLotNumber()+"; "
                    +fileVaccinations.get(i).getScheduledDateTime().format(Constants.DATE_TIME_FORMATTER_ALT)+"; "
                    +fileVaccinations.get(i).getArrivalTime().format(Constants.DATE_TIME_FORMATTER_ALT)+"; "
                    +fileVaccinations.get(i).getNurseAdministrationDateTime().format(Constants.DATE_TIME_FORMATTER_ALT)+"; "
                    +fileVaccinations.get(i).getLeavingDateTime().format(Constants.DATE_TIME_FORMATTER_ALT));
            info.add(sb.toString());
        }
    return info;
   }

   public void sort(){
       String sortBy = Utils.ReadProppeties("Import.View");
       switch (sortBy) {
           case "Arrival":
               sortByArrival();
               break;
           case "Leaving":
               sortByLeaving();
               break;
           default:
       }
   }


   private void sortByArrival() {
       String sortAlgorithm = Utils.ReadProppeties("Import.Arrival.Sort");
       switch (sortAlgorithm) {
           case "Bubble":
               BubleSort.sortByArrivalTime(fileVaccinations);
               break;
           case "Quick":
               QuickSort.sortByArrivalTime(fileVaccinations, 0, fileVaccinations.size());
               break;
           default:
       }
   }

   private void sortByLeaving() {
       String sortAlgorithm = Utils.ReadProppeties("Import.Leaving.Sort");
       switch (sortAlgorithm) {
           case "Bubble":
               BubleSort.sortByLeavingTime(fileVaccinations);
               break;
           case "Quick":
               QuickSort.sortByLeavingTime(fileVaccinations, 0, fileVaccinations.size());
               break;
           default:
       }
   }
}
