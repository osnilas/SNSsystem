package app.ui.gui.ui;

import javafx.stage.FileChooser;

public class FileChooserVaccinationStatisticsUI {
    private FileChooser fileChooser;

    private FileChooserVaccinationStatisticsUI() {
        fileChooser = new FileChooser();
        associarFiltro("Comma-separated values", "*.csv");
    }

    public static FileChooser createFileChooserVaccinationStatistics() {
        FileChooserVaccinationStatisticsUI fileChooserVaccinationStatistics = new FileChooserVaccinationStatisticsUI();
        return fileChooserVaccinationStatistics.fileChooser;
    }

    private void associarFiltro(String descricao, String extensao) {
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(descricao, extensao);
        fileChooser.getExtensionFilters().add(filter);
    }
}
