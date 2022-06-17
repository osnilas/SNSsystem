package app.ui.gui.controller;

import algorithms.sort.BubleSort;
import algorithms.sort.Merge;
import algorithms.sort.QuickSort;
import app.controller.App;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ImportDataFromLegacySystemController {


    private Company company;
    ArrayList<String> fileData = new ArrayList<>();
    private App app;
    private ReadDataFromLegacySystem read;
    private List<LegacySystemData> fileVaccinations;

    private long startTime;
    private long endTime;




    public ImportDataFromLegacySystemController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

   public void readData(String filePath) throws Exception {
           this.read = new ReadDataFromLegacySystem(filePath, company.getVaccineList());
           this.fileVaccinations= read.copyDataFromLegacySystem();
   }

   public void save(){
       company.addLegacySystemDataToStore(fileVaccinations);
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
               startTime= System.nanoTime();
               BubleSort.sortByArrivalTime(fileVaccinations);
                endTime= System.nanoTime();
               System.out.println("BubbleSort arrival: "+(endTime-startTime)+" nanoseconds");
               break;
           case "Quick":
                startTime= System.nanoTime();
                Merge.sort(fileVaccinations,0,fileVaccinations.size()-1);
                endTime= System.nanoTime();
                System.out.println("QuickSort arrival: "+(endTime-startTime)+" nanoseconds");
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
               Merge.sort(fileVaccinations, 0, fileVaccinations.size()-1);
               break;
           default:
       }
   }
}
