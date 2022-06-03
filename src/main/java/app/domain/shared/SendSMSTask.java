package app.domain.shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.TimerTask;

import static java.lang.System.out;

public class SendSMSTask extends TimerTask {

    private String message;
    @Override
    public void run() {
        File file = new File("C:\\Users\\jonas\\IdeaProjects\\lei-22-s2-1da-g03\\bin\\sms.txt");
        PrintWriter out= null;
        try {
            out = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        out.println(message);
        out.close();
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
