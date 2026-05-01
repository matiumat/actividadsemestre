package co.edu.poli.contexto4.controlador;

import co.edu.poli.contexto4.modelo.*;
import co.edu.poli.contexto4.servicios.ImplementacionOperacionCRUD;

import java.io.IOException;
import java.util.Scanner;

/**
 * Controlador principal del sistema de gestión de protocolos espaciales.
 * Presenta un menú interactivo por consola para realizar operaciones CRUD
 * (Crear, Leer, Modificar, Eliminar) sobre protocolos, además de
 * serialización y deserialización en archivo plano.
 *
 * @author Equipo Contexto 4
 * @version 1.0
 */
public class PrimaryController {

    // ---------------------------------------------------------------
    // Objetos base reutilizados en la creación de protocolos
    // ---------------------------------------------------------------
    private static final Sensor SENSOR_1 =
            new Sensor("SEN-001", "Titanio", "Blanco", 5.5, 0.3);
    private static final Sensor SENSOR_2 =
            new Sensor("SEN-002", "Aluminio", "Gris", 4.0, 0.2);
    private static final Radiacion RAD_ALTA =
            new Radiacion(2.5, "ALTO", "Gamma", "Alta", "Solar");
    private static final Radiacion RAD_BAJA =
            new Radiacion(0.05, "BAJO", "Alpha", "Baja", "Cosmica");
    private static final Mitigacion MIT_1 =
            new Mitigacion("MIT-001", "Blindaje reforzado", 1, "Modulo A", RAD_ALTA);

    // ---------------------------------------------------------------
    // Utilitarios de impresión
    // ---------------------------------------------------------------

    /** Imprime separador simple. */
    private static void separador() {
        System.out.println("-------------------------------------------------------------");
    }

    /** Imprime separador grueso. */
    private static void separadorGrueso() {
        System.out.println("=============================================================");
    }

    /**
     * Imprime el estado completo del arreglo del CRUD.
     *
     * @param crud Instancia de ImplementacionOperacionCRUD.
     */
    private static void imprimirArreglo(ImplementacionOperacionCRUD crud) {
        Protocolo[] arr = crud.getArreglo_protocolos();
        System.out.println("  Estado del arreglo [tamaño=" + arr.length + "]:");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.println("    [" + i + "] "
                        + arr[i].getClass().getSimpleName()
                        + " | numero_id='" + arr[i].getCodigo() + "'"
                        + " | instrucciones='" + arr[i].getInstrucciones() + "'");
            } else {
                System.out.println("    [" + i + "] -- vacío (null) --");
            }
        }
    }

    // ---------------------------------------------------------------
    // Menú principal
    // ---------------------------------------------------------------

    /** Muestra el menú principal. */
    private static void mostrarMenu() {
        separadorGrueso();
        System.out.println("   SISTEMA DE GESTIÓN DE PROTOCOLOS ESPACIALES");
        separadorGrueso();
        System.out.println("  1. CREAR   protocolo");
        System.out.println("  2. LEER    protocolo por índice");
        System.out.println("  3. LEER    todos los protocolos");
        System.out.println("  4. BUSCAR  protocolo por numero_id");
        System.out.println("  5. MODIFICAR protocolo por numero_id");
        System.out.println("  6. ELIMINAR  protocolo por numero_id");
        separador();
        System.out.println("  7. SERIALIZAR   (guardar en archivo .txt)");
        System.out.println("  8. DESERIALIZAR (cargar desde archivo .txt)");
        separador();
        System.out.println("  9. VER estado del arreglo");
        System.out.println("  0. SALIR");
        separadorGrueso();
        System.out.print("  Seleccione una opción: ");
    }

    // ---------------------------------------------------------------
    // Submenús CRUD
    // ---------------------------------------------------------------

    /**
     * Solicita datos al usuario y crea un protocolo.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada.
     * @throws Exception 
     */
    private static void menuCrear(ImplementacionOperacionCRUD crud, Scanner scanner) throws Exception {
        separadorGrueso();
        System.out.println("  CREAR PROTOCOLO");
        separador();

        System.out.println("  Tipo de protocolo:");
        System.out.println("    1. ProtocoloInsuficiencia");
        System.out.println("    2. ProtocoloRadiacion");
        System.out.print("  Seleccione tipo: ");
        String opTipo = scanner.nextLine().trim();

        if (!opTipo.equals("1") && !opTipo.equals("2")) {
            System.out.println("  ERROR: Tipo inválido. Debe ser 1 o 2.");
            return;
        }

        System.out.print("  numero_id (entero, ej: 101): ");
        String entradaId = scanner.nextLine().trim();

        int numero_id;
        try {
            numero_id = Integer.parseInt(entradaId);
        } catch (NumberFormatException e) {
            System.out.println("  ERROR: numero_id inválido. '" + entradaId
                    + "' no es un número entero.");
            return;
        }

        System.out.print("  Registro: ");
        String registro = scanner.nextLine().trim();
        System.out.print("  Instrucciones: ");
        String instrucciones = scanner.nextLine().trim();
        System.out.print("  Límites (ej: 1.0 Sv): ");
        String limites = scanner.nextLine().trim();

        Protocolo nuevo;
        if (opTipo.equals("1")) {
            nuevo = new ProtocoloInsuficiencia(numero_id, registro, instrucciones,
                    limites, MIT_1, SENSOR_1, RAD_BAJA);
        } else {
            nuevo = new ProtocoloRadiacion(numero_id, registro, instrucciones,
                    limites, MIT_1, SENSOR_1, RAD_ALTA);
        }

        try {
            String resultado = crud.crear(nuevo);
            separador();
            System.out.println("  " + resultado);
        } catch (IOException e) {
            System.out.println("  ERROR [CREAR]: " + e.getMessage());
        }
    }

    /**
     * Lee un protocolo por su índice.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada.
     * @throws Exception 
     */
    private static void menuLeerPorIndice(ImplementacionOperacionCRUD crud, Scanner scanner) throws Exception {
        separadorGrueso();
        System.out.println("  LEER PROTOCOLO POR ÍNDICE");
        separador();

        System.out.print("  Ingrese el índice: ");
        String entrada = scanner.nextLine().trim();
        int indice;
        try {
            indice = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("  ERROR: Debe ingresar un número entero válido.");
            return;
        }

        try {
            Protocolo p = crud.leer(indice);
            separador();
            System.out.println("  Tipo        : " + p.getClass().getSimpleName());
            System.out.println("  Información : " + p.leer_informacion());
            System.out.println("  Descripción : " + p.obtener_descripcion_protocolo());
        } catch (IOException e) {
            System.out.println("  ERROR [LEER]: " + e.getMessage());
        }
    }

    /**
     * Muestra todos los protocolos almacenados.
     *
     * @param crud Instancia del CRUD.
     * @throws Exception 
     */
    private static void menuLeerTodos(ImplementacionOperacionCRUD crud) throws Exception {
        separadorGrueso();
        System.out.println("  TODOS LOS PROTOCOLOS");
        separador();

        try {
            Protocolo[] todos = crud.leerTodos();
            for (int i = 0; i < todos.length; i++) {
                if (todos[i] != null) {
                    System.out.println("  [" + i + "] " + todos[i].leer_informacion());
                } else {
                    System.out.println("  [" + i + "] -- vacío (null) --");
                }
            }
        } catch (IOException e) {
            System.out.println("  ERROR [LEER TODOS]: " + e.getMessage());
        }
    }

    /**
     * Busca un protocolo por su numero_id.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada.
     * @throws Exception 
     */
    private static void menuBuscarPorCodigo(ImplementacionOperacionCRUD crud, Scanner scanner) throws Exception {
        separadorGrueso();
        System.out.println("  BUSCAR PROTOCOLO POR numero_id");
        separador();

        System.out.print("  Ingrese el numero_id a buscar: ");
        String entrada = scanner.nextLine().trim();

        int idx = crud.buscarIndicePorCodigo(entrada);
        separador();
        if (idx >= 0) {
            try {
                Protocolo p = crud.leer(idx);
                System.out.println("  Encontrado en posición [" + idx + "]");
                System.out.println("  Tipo        : " + p.getClass().getSimpleName());
                System.out.println("  Información : " + p.leer_informacion());
                System.out.println("  Descripción : " + p.obtener_descripcion_protocolo());
            } catch (IOException e) {
                System.out.println("  ERROR [BUSCAR]: " + e.getMessage());
            }
        } else {
            System.out.println("  No se encontró protocolo con numero_id '" + entrada + "'.");
        }
    }

    /**
     * Modifica un protocolo buscado por su numero_id.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada.
     * @throws Exception 
     */
    private static void menuModificar(ImplementacionOperacionCRUD crud, Scanner scanner) throws Exception {
        separadorGrueso();
        System.out.println("  MODIFICAR PROTOCOLO POR numero_id");
        separador();

        System.out.print("  Ingrese el numero_id del protocolo a modificar: ");
        String idBuscar = scanner.nextLine().trim();

        int idx = crud.buscarIndicePorCodigo(idBuscar);
        if (idx < 0) {
            System.out.println("  ERROR: No se encontró protocolo con numero_id '"
                    + idBuscar + "'.");
            return;
        }

        try {
            System.out.println("  Protocolo encontrado: "
                    + crud.leer(idx).leer_informacion());
        } catch (IOException e) {
            System.out.println("  ERROR: " + e.getMessage());
            return;
        }

        separador();
        System.out.println("  Ingrese los nuevos datos:");

        System.out.println("  Tipo:");
        System.out.println("    1. ProtocoloInsuficiencia");
        System.out.println("    2. ProtocoloRadiacion");
        System.out.print("  Seleccione tipo: ");
        String opTipo = scanner.nextLine().trim();
        if (!opTipo.equals("1") && !opTipo.equals("2")) {
            System.out.println("  ERROR: Tipo inválido.");
            return;
        }

        System.out.print("  Nuevo numero_id (entero): ");
        String entradaId = scanner.nextLine().trim();
        int nuevoId;
        try {
            nuevoId = Integer.parseInt(entradaId);
        } catch (NumberFormatException e) {
            System.out.println("  ERROR: numero_id inválido.");
            return;
        }

        System.out.print("  Nuevo registro: ");
        String nuevoRegistro = scanner.nextLine().trim();
        System.out.print("  Nuevas instrucciones: ");
        String nuevasInstrucciones = scanner.nextLine().trim();
        System.out.print("  Nuevos límites: ");
        String nuevosLimites = scanner.nextLine().trim();

        Protocolo nuevoProtocolo;
        if (opTipo.equals("1")) {
            nuevoProtocolo = new ProtocoloInsuficiencia(nuevoId, nuevoRegistro,
                    nuevasInstrucciones, nuevosLimites, MIT_1, SENSOR_1, RAD_BAJA);
        } else {
            nuevoProtocolo = new ProtocoloRadiacion(nuevoId, nuevoRegistro,
                    nuevasInstrucciones, nuevosLimites, MIT_1, SENSOR_1, RAD_ALTA);
        }

        try {
            String resultado = crud.modificar(idx, nuevoProtocolo);
            separador();
            System.out.println("  " + resultado);
        } catch (IOException e) {
            System.out.println("  ERROR [MODIFICAR]: " + e.getMessage());
        }
    }

    /**
     * Elimina un protocolo buscado por su numero_id.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada.
     * @throws Exception 
     */
    private static void menuEliminar(ImplementacionOperacionCRUD crud, Scanner scanner) throws Exception {
        separadorGrueso();
        System.out.println("  ELIMINAR PROTOCOLO POR numero_id");
        separador();

        System.out.print("  Ingrese el numero_id del protocolo a eliminar: ");
        String idBuscar = scanner.nextLine().trim();

        int idx = crud.buscarIndicePorCodigo(idBuscar);
        if (idx < 0) {
            System.out.println("  ERROR: No se encontró protocolo con numero_id '"
                    + idBuscar + "'.");
            return;
        }

        try {
            System.out.println("  Protocolo encontrado: "
                    + crud.leer(idx).leer_informacion());
        } catch (IOException e) {
            System.out.println("  ERROR: " + e.getMessage());
            return;
        }

        System.out.print("  ¿Confirmar eliminación? (s/n): ");
        String confirmacion = scanner.nextLine().trim();
        separador();

        if (confirmacion.equalsIgnoreCase("s")) {
            try {
                System.out.println("  " + crud.eliminar(idx));
            } catch (IOException e) {
                System.out.println("  ERROR [ELIMINAR]: " + e.getMessage());
            }
        } else {
            System.out.println("  Operación cancelada.");
        }
    }

    /**
     * Serializa el arreglo al archivo protocolos.txt.
     *
     * @param crud Instancia del CRUD.
     * @throws Exception 
     */
    private static void menuSerializar(ImplementacionOperacionCRUD crud) throws Exception {
        separadorGrueso();
        System.out.println("  SERIALIZAR — Guardar protocolos en archivo .txt");
        separador();

        try {
            System.out.println("  " + crud.serializar());
        } catch (IOException e) {
            System.out.println("  ERROR [SERIALIZAR]: " + e.getMessage());
        }
    }

    /**
     * Deserializa los protocolos desde protocolos.txt.
     *
     * @param crud Instancia del CRUD.
     */
    private static void menuDeserializar(ImplementacionOperacionCRUD crud) {
        separadorGrueso();
        System.out.println("  DESERIALIZAR — Cargar protocolos desde archivo .txt");
        separador();

        try {
            Protocolo[] cargados = crud.deserializar();
            if (cargados.length == 0) {
                System.out.println("  No se cargaron protocolos.");
                return;
            }
            System.out.println("  Protocolos cargados desde archivo:");
            for (int i = 0; i < cargados.length; i++) {
                if (cargados[i] != null) {
                    System.out.println("    [" + i + "] "
                            + cargados[i].getClass().getSimpleName()
                            + " | " + cargados[i].leer_informacion());
                }
            }
        } catch (IOException e) {
            System.out.println("  ERROR [DESERIALIZAR]: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    // Método principal
    // ---------------------------------------------------------------

    /**
     * Ejecuta el menú interactivo por consola.
     * @throws Exception 
     */
    public static void ejecutarMenu() throws Exception {
        ImplementacionOperacionCRUD crud = new ImplementacionOperacionCRUD();
        Scanner scanner = new Scanner(System.in);
        String opcion;

        separadorGrueso();
        System.out.println("   BIENVENIDO AL SISTEMA DE GESTIÓN DE PROTOCOLOS");
        System.out.println("   Contexto 4 — Protocolos Espaciales");
        separadorGrueso();

        do {
            mostrarMenu();
            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1": menuCrear(crud, scanner);           break;
                case "2": menuLeerPorIndice(crud, scanner);   break;
                case "3": menuLeerTodos(crud);                break;
                case "4": menuBuscarPorCodigo(crud, scanner); break;
                case "5": menuModificar(crud, scanner);       break;
                case "6": menuEliminar(crud, scanner);        break;
                case "7": menuSerializar(crud);               break;
                case "8": menuDeserializar(crud);             break;
                case "9":
                    separadorGrueso();
                    System.out.println("  VER ESTADO DEL ARREGLO");
                    separador();
                    imprimirArreglo(crud);
                    break;
                case "0":
                    separadorGrueso();
                    System.out.println("  Saliendo del sistema. Hasta luego.");
                    separadorGrueso();
                    break;
                default:
                    System.out.println("  ERROR: Opción inválida. Ingrese un número del 0 al 9.");
                    break;
            }

            if (!opcion.equals("0")) {
                System.out.println("\n  Presione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (!opcion.equals("0"));

        scanner.close();
    }
}