package app.ui.gui.ui;

import javafx.stage.FileChooser;

public class FileChooserLegacySystem {
    private FileChooser fileChooser;

    private FileChooserLegacySystem() {
        fileChooser = new FileChooser();
        addFilter("Ficheiros Lista Telefónica", "*.csv");
    }

    public static FileChooser criarFileChooserListaTelefonica() {
        FileChooserLegacySystem fileChooserLegacySystem = new FileChooserLegacySystem();
        return fileChooserLegacySystem.fileChooser;
    }

    private void addFilter(String descricao, String extensao) {
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter(descricao, extensao);
        fileChooser.getExtensionFilters().add(filtro);
    }

}
