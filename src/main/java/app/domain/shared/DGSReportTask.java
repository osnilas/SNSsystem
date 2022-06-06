package app.domain.shared;

import app.controller.App;
import app.domain.model.Company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import static java.lang.System.out;

public class DGSReportTask extends TimerTask {
    private List<String> report = new ArrayList<>();
    private App app;
    private Company company;

    @Override
    public void run() {
        setMessage();
        File file = new File("C:\\Users\\jonas\\IdeaProjects\\lei-22-s2-1da-g03\\testDocs\\ReportVaccination.csv");
        PrintWriter out= null;
        try {
            FileWriter fileWriter = new FileWriter("testDocs/Report.csv", true);
            out = new PrintWriter(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(file.exists() && !file.isDirectory()) {
            out.write("Date;Vaccination Facility;Total of vaccinated users");
        }
        for(int i=0;i<report.size();i++) {
            out.println(report.get(i));
        }
        out.close();
    }

    public void setMessage() {
        this.app = App.getInstance();
        this.company = App.getInstance().getCompany();
        report = new ArrayList<>(company.generateDGSreportContent());
    }
}
