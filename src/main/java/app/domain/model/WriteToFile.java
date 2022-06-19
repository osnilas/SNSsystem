package app.domain.model;

import app.domain.shared.Constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WriteToFile {

    public static void write(String filePath, StringBuilder sb){
        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(sb.toString());
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
