package co.edu.poli.contexto4.controlador;

import java.io.IOException;

import co.edu.poli.contexto4.vista.App;
import javafx.fxml.FXML;


public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
