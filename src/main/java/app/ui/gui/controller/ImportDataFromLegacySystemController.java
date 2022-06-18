package app.ui.gui.controller;

import algorithms.sort.BubleSort;
import algorithms.sort.MergeSort;
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

    /**
     * @author Filipe Magalhães
     * @Description This method reads the data from the legacy system file give the file path
     * @param filePath
     */

   public void readData(String filePath) throws Exception {
           this.read = new ReadDataFromLegacySystem(filePath, company.getVaccineList());
           this.fileVaccinations= read.copyDataFromLegacySystem();
   }



   public void save(){
       company.addLegacySystemDataToStore(fileVaccinations);
   }



    /**
     * @author Filipe Magalhães
     * @Description This method gets all the info from each user in the file and fills a list of strings with it.
     * @return List<String> with the info from each user in the file
     */



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



    /**
     * @author Filipe Magalhães
     * @Description This method sorts the data by center arrival time through, either a bubble sort algorithm or a merge sort algorithm
     */




   private void sortByArrival() {
       String sortAlgorithm = Utils.ReadProppeties("Import.Arrival.Sort");
       switch (sortAlgorithm) {
           case "Bubble":
               BubleSort.sortByArrivalTime(fileVaccinations);
               break;
           case "Merge":
              MergeSort.sort(fileVaccinations, 0, fileVaccinations.size()-1,1);
               break;
           default:
       }
   }



    /**
     * @author Filipe Magalhães
     * @Description This method sorts the data by center leaving time through, either a bubble sort algorithm or a merge sort algorithm
     */



   private void sortByLeaving() {
       String sortAlgorithm = Utils.ReadProppeties("Import.Leaving.Sort");
       switch (sortAlgorithm) {
           case "Bubble":
               BubleSort.sortByLeavingTime(fileVaccinations);
               break;
           case "Merge":
               MergeSort.sort(fileVaccinations, 0, fileVaccinations.size()-1,2);
               break;
           default:
       }
   }
}
