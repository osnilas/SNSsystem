package Timer;

import app.domain.model.WriteToFile;
import app.domain.shared.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.TimerTask;

import static java.lang.System.out;

public class SendSMSTask extends TimerTask {

    private String message;

    @Override
    public void run() {
        String path = Constants.TestDocs_Directory+"/SMS.txt";
        File file = new File(path);
        System.out.println("path of SMS: "+file.getAbsolutePath());;
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        WriteToFile.write(path,sb);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
