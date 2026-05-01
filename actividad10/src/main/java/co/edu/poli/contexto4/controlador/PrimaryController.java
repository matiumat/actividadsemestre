package co.edu.poli.contexto4.controlador;

import co.edu.poli.contexto4.modelo.*;
import co.edu.poli.contexto4.servicios.ImplementacionOperacionCRUD;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    private static final String TIPO_INS = "ProtocoloInsuficiencia";
    private static final String TIPO_RAD = "ProtocoloRadiacion";

    private static final Sensor    SENSOR_1 = new Sensor("SEN-001", "Titanio", "Blanco", 5.5, 0.3);
    private static final Radiacion RAD_ALTA = new Radiacion(2.5,  "ALTO", "Gamma", "Alta",  "Solar");
    private static final Radiacion RAD_BAJA = new Radiacion(0.05, "BAJO", "Alpha", "Baja",  "Cosmica");
    private static final Mitigacion MIT_1   = new Mitigacion("MIT-001", "Blindaje reforzado", 1, "Modulo A", RAD_ALTA);

    @FXML private ComboBox<String> cmbTipo;
    @FXML private TextField        txtId;
    @FXML private TextField        txtRegistro;
    @FXML private TextArea         txtInstrucciones;
    @FXML private TextField        txtLimites;
    @FXML private TextField        txtBuscarId;

    @FXML private TableView<ProtocoloFila>               tablaProtocolos;
    @FXML private TableColumn<ProtocoloFila, String>     colIndice;
    @FXML private TableColumn<ProtocoloFila, String>     colTipo;
    @FXML private TableColumn<ProtocoloFila, String>     colId;
    @FXML private TableColumn<ProtocoloFila, String>     colRegistro;
    @FXML private TableColumn<ProtocoloFila, String>     colInstrucciones;
    @FXML private TableColumn<ProtocoloFila, String>     colLimites;

    @FXML private Label lblEstado;

    private final ImplementacionOperacionCRUD crud = new ImplementacionOperacionCRUD();
    private final ObservableList<ProtocoloFila> filas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTipo.setItems(FXCollections.observableArrayList(TIPO_INS, TIPO_RAD));
        cmbTipo.setValue(TIPO_INS);

        colIndice.setCellValueFactory(d -> d.getValue().indice);
        colTipo.setCellValueFactory(d -> d.getValue().tipo);
        colId.setCellValueFactory(d -> d.getValue().codigo);
        colRegistro.setCellValueFactory(d -> d.getValue().registro);
        colInstrucciones.setCellValueFactory(d -> d.getValue().instrucciones);
        colLimites.setCellValueFactory(d -> d.getValue().limites);

        tablaProtocolos.setItems(filas);

        tablaProtocolos.getSelectionModel().selectedItemProperty().addListener(
            (obs, prev, sel) -> {
                if (sel != null) {
                    cmbTipo.setValue(sel.tipo.get());
                    txtId.setText(sel.codigo.get());
                    txtRegistro.setText(sel.registro.get());
                    txtInstrucciones.setText(sel.instrucciones.get());
                    txtLimites.setText(sel.limites.get());
                }
            });

        actualizarEstado();
    }

    @FXML
    private void onCreate() {
        int numero_id;
        try {
            numero_id = Integer.parseInt(txtId.getText().trim());
        } catch (NumberFormatException e) {
            alerta("Error", "El Numero ID debe ser un numero entero.");
            return;
        }
        try {
            info(crud.crear(construir(numero_id)));
            actualizarTabla();
            actualizarEstado();
            onLimpiar();
        } catch (Exception e) {
            alerta("Error al crear", e.getMessage());
        }
    }

    @FXML
    private void onModificar() {
        ProtocoloFila sel = tablaProtocolos.getSelectionModel().getSelectedItem();
        if (sel == null) { alerta("Modificar", "Selecciona un protocolo en la tabla."); return; }

        int numero_id;
        try {
            numero_id = Integer.parseInt(txtId.getText().trim());
        } catch (NumberFormatException e) {
            alerta("Error", "El Numero ID debe ser un numero entero.");
            return;
        }
        try {
            info(crud.modificar(Integer.parseInt(sel.indice.get()), construir(numero_id)));
            actualizarTabla();
            actualizarEstado();
            onLimpiar();
        } catch (Exception e) {
            alerta("Error al modificar", e.getMessage());
        }
    }

    @FXML
    private void onEliminar() {
        ProtocoloFila sel = tablaProtocolos.getSelectionModel().getSelectedItem();
        if (sel == null) { alerta("Eliminar", "Selecciona un protocolo en la tabla."); return; }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
            "¿Eliminar protocolo ID=" + sel.codigo.get() + "?", ButtonType.OK, ButtonType.CANCEL);
        confirm.setTitle("Confirmar eliminacion");
        confirm.setHeaderText(null);
        Optional<ButtonType> res = confirm.showAndWait();

        if (res.isPresent() && res.get() == ButtonType.OK) {
            try {
                info(crud.eliminar(Integer.parseInt(sel.indice.get())));
                actualizarTabla();
                actualizarEstado();
                onLimpiar();
            } catch (Exception e) {
                alerta("Error al eliminar", e.getMessage());
            }
        }
    }

    @FXML
    private void onBuscar() {
        String entrada = txtBuscarId.getText().trim();
        int idx = crud.buscarIndicePorCodigo(entrada);
        if (idx >= 0) {
            for (ProtocoloFila f : filas) {
                if (f.indice.get().equals(String.valueOf(idx))) {
                    tablaProtocolos.getSelectionModel().select(f);
                    tablaProtocolos.scrollTo(f);
                    return;
                }
            }
        } else {
            alerta("Buscar", "No se encontro protocolo con ID: " + entrada);
        }
    }

    @FXML
    private void onSerializar() {
        try {
            info(crud.serializar());
        } catch (IOException e) {
            alerta("Error de archivo", e.getMessage());
        } catch (Exception e) {
            alerta("Error al serializar", e.getMessage());
        }
    }

    @FXML
    private void onDeserializar() {
        try {
            Protocolo[] cargados = crud.deserializar();
            for (Protocolo p : cargados) {
                if (p != null && crud.buscarIndicePorCodigo(p.getCodigo()) < 0) {
                    try { crud.crear(p); } catch (Exception ignored) {}
                }
            }
            actualizarTabla();
            actualizarEstado();
            info("Deserializado correctamente.");
        } catch (IOException e) {
            alerta("Error de archivo", "No se pudo leer protocolos.txt.\n" + e.getMessage());
        }
    }

    @FXML
    private void onLimpiar() {
        txtId.clear();
        txtRegistro.clear();
        txtInstrucciones.clear();
        txtLimites.clear();
        txtBuscarId.clear();
        cmbTipo.setValue(TIPO_INS);
        tablaProtocolos.getSelectionModel().clearSelection();
    }

    // ── Helpers ──

    private Protocolo construir(int numero_id) {
        String registro      = txtRegistro.getText().trim();
        String instrucciones = txtInstrucciones.getText().trim();
        String limites       = txtLimites.getText().trim();
        if (TIPO_INS.equals(cmbTipo.getValue()))
            return new ProtocoloInsuficiencia(numero_id, registro, instrucciones, limites, MIT_1, SENSOR_1, RAD_BAJA);
        else
            return new ProtocoloRadiacion(numero_id, registro, instrucciones, limites, MIT_1, SENSOR_1, RAD_ALTA);
    }

    private void actualizarTabla() {
        filas.clear();
        Protocolo[] arr = crud.getArreglo_protocolos();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null)
                filas.add(new ProtocoloFila(
                    String.valueOf(i),
                    arr[i].getClass().getSimpleName(),
                    arr[i].getCodigo(),
                    nvl(arr[i].getRegistro()),
                    nvl(arr[i].getInstrucciones()),
                    nvl(arr[i].getLimites())
                ));
        }
    }

    private void actualizarEstado() {
        long ocupados = java.util.Arrays.stream(crud.getArreglo_protocolos())
                .filter(p -> p != null).count();
        lblEstado.setText("Tamaño arreglo: " + crud.getTamano() + "  |  Ocupados: " + ocupados);
    }

    private void alerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    private void info(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Info");
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    private String nvl(String s) { return s != null ? s : ""; }

    // ── Modelo de fila ──

    public static class ProtocoloFila {
        final SimpleStringProperty indice, tipo, codigo, registro, instrucciones, limites;
        ProtocoloFila(String indice, String tipo, String codigo,
                      String registro, String instrucciones, String limites) {
            this.indice        = new SimpleStringProperty(indice);
            this.tipo          = new SimpleStringProperty(tipo);
            this.codigo        = new SimpleStringProperty(codigo);
            this.registro      = new SimpleStringProperty(registro);
            this.instrucciones = new SimpleStringProperty(instrucciones);
            this.limites       = new SimpleStringProperty(limites);
        }
    }
}