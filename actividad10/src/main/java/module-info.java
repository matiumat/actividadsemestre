module co.edu.poli.contexto4.controlador {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens co.edu.poli.contexto4.controlador to javafx.fxml;
    opens co.edu.poli.contexto4.vista to javafx.graphics;
    exports co.edu.poli.contexto4.controlador;
}
