package app.ui.console.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileData implements ReadFile{


    public ReadFileData() {
    }

    @Override
    public List<String> readFile(String file) throws IOException {
        List<String> fileData=new ArrayList<>();
        Scanner in = new Scanner(new File(file));
        while (in.hasNextLine()){
            fileData.add(in.nextLine());
        }
        return fileData;
    }
}
