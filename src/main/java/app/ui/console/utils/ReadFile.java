package app.ui.console.utils;

import java.io.FileNotFoundException;
import java.util.List;

public interface ReadFile {

    public List<String> readFile(String file) throws FileNotFoundException;
}
