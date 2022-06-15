package Timer;

import app.controller.App;
import app.domain.model.Company;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import static java.lang.System.out;

public class DGSReportTask extends TimerTask {

    private Company company;
    @Override
    public void run() {
        company.generateDGSreportContent();
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
