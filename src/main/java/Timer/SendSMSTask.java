package Timer;

import app.domain.shared.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.TimerTask;

import static java.lang.System.out;

public class SendSMSTask extends TimerTask {

    private String message;

    private String path;
    @Override
    public void run() {
        File file = new File(path);
        PrintWriter out= null;
        try {
            out = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        out.println(message);
        out.close();
    }

    public void setPath() {
        this.path = Constants.TestDocs_Directory+"/SMS.txt";
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
