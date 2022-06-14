package app.controller;

import app.domain.model.Company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportDataFromLegacySystemController {


    private Company company;
    private App app;



    public ImportDataFromLegacySystemController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    ArrayList<String> fileData = new ArrayList<>();

   int n = fileData .size();

   public void readData(String filePath) throws IOException {
           read = new ReadDataFromLegacySystem(filePath);
   }






}
