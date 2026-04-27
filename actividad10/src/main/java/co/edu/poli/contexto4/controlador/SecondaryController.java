package co.edu.poli.contexto4.controlador;

import java.io.IOException;

import co.edu.poli.contexto4.vista.App;
import javafx.fxml.FXML;
public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}