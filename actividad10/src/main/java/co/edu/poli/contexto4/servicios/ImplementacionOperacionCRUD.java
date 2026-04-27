package co.edu.poli.contexto4.servicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import co.edu.poli.contexto4.modelo.Protocolo;
import co.edu.poli.contexto4.modelo.ProtocoloInsuficiencia;
import co.edu.poli.contexto4.modelo.ProtocoloRadiacion;

/**
 * Implementación concreta de las interfaces {@link OperacionCRUD} y {@link OperacionArchivo}.
 * <p>
 * Gestiona un arreglo dinámico de objetos de tipo {@link Protocolo} (supersuperclase).
 * El arreglo inicia con tamaño 2 y se duplica automáticamente cuando se llena.
 * </p>
 * <p>
 * Todos los métodos usan {@code throws} para propagar los errores al método
 * invocador en lugar de retornarlos como Strings. Los errores de validación
 * lanzan {@link Exception} y los errores de archivo lanzan {@link IOException}.
 * </p>
 *
 * @author Equipo Contexto 4
 * @version 2.0
 * @see OperacionCRUD
 * @see OperacionArchivo
 */
public class ImplementacionOperacionCRUD implements OperacionCRUD, OperacionArchivo {

    /** Nombre del archivo donde se persisten los protocolos. */
    private static final String NOMBRE_ARCHIVO = "protocolos.txt";

    /** Separador de campos dentro de cada línea del archivo. */
    private static final String SEP = ";";

    /**
     * Arreglo de tipo supersuperclase {@link Protocolo}.
     * Inicia con tamaño 2. Se duplica automáticamente al llenarse.
     */
    private Protocolo[] arreglo_protocolos;

    /**
     * Constructor por defecto. Inicializa el arreglo con tamaño 2.
     */
    public ImplementacionOperacionCRUD() {
        arreglo_protocolos = new Protocolo[2];
    }

    // =======================================================================
    // OPERACIONES CRUD
    // =======================================================================

    /**
     * {@inheritDoc}
     *
     * @throws Exception Si el protocolo es {@code null}, el numero_id está vacío,
     *                   o ya existe un protocolo con ese numero_id.
     */
    @Override
    public String crear(Protocolo protocolo) throws Exception {
        if (protocolo == null) {
            throw new Exception("No se puede insertar un protocolo null.");
        }
        if (protocolo.getCodigo() == null || protocolo.getCodigo().trim().isEmpty()) {
            throw new Exception("El protocolo debe tener un numero_id valido.");
        }
        for (Protocolo p : arreglo_protocolos) {
            if (p != null && p.getCodigo().equals(protocolo.getCodigo())) {
                throw new Exception("Ya existe un protocolo con numero_id '"
                        + protocolo.getCodigo() + "'.");
            }
        }

        // Buscar primer null de izquierda a derecha
        for (int i = 0; i < arreglo_protocolos.length; i++) {
            if (arreglo_protocolos[i] == null) {
                arreglo_protocolos[i] = protocolo;
                return "OK [CREAR]: Protocolo con numero_id '" + protocolo.getCodigo()
                        + "' insertado en posicion [" + i + "].";
            }
        }

        // Arreglo lleno → duplicar tamaño
        Protocolo[] nuevo = new Protocolo[arreglo_protocolos.length * 2];
        for (int i = 0; i < arreglo_protocolos.length; i++) {
            nuevo[i] = arreglo_protocolos[i];
        }
        arreglo_protocolos = nuevo;

        for (int i = 0; i < arreglo_protocolos.length; i++) {
            if (arreglo_protocolos[i] == null) {
                arreglo_protocolos[i] = protocolo;
                return "OK [CREAR]: Arreglo duplicado a tamano " + arreglo_protocolos.length
                        + ". Protocolo con numero_id '" + protocolo.getCodigo()
                        + "' insertado en posicion [" + i + "].";
            }
        }

        throw new Exception("No se pudo insertar el protocolo. Error inesperado.");
    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception Si el índice está fuera de rango o la posición está vacía.
     */
    @Override
    public Protocolo leer(int indice) throws Exception {
        if (indice < 0 || indice >= arreglo_protocolos.length) {
            throw new Exception("Indice " + indice
                    + " fuera de rango. Tamano actual: " + arreglo_protocolos.length);
        }
        if (arreglo_protocolos[indice] == null) {
            throw new Exception("No hay protocolo en la posicion [" + indice + "].");
        }
        return arreglo_protocolos[indice];
    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception Si el arreglo está completamente vacío.
     */
    @Override
    public Protocolo[] leerTodos() throws Exception {
        for (Protocolo p : arreglo_protocolos) {
            if (p != null) return arreglo_protocolos;
        }
        throw new Exception("El arreglo esta vacio. No hay protocolos registrados.");
    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception Si el índice es inválido, la posición está vacía,
     *                   el protocolo de reemplazo es null, o el numero_id está vacío.
     */
    @Override
    public String modificar(int indice, Protocolo protocolo) throws Exception {
        if (indice < 0 || indice >= arreglo_protocolos.length) {
            throw new Exception("Indice " + indice
                    + " fuera de rango. Tamano actual: " + arreglo_protocolos.length);
        }
        if (arreglo_protocolos[indice] == null) {
            throw new Exception("No existe protocolo en la posicion ["
                    + indice + "]. Use CREAR para insertar.");
        }
        if (protocolo == null) {
            throw new Exception("El protocolo de reemplazo no puede ser null.");
        }
        if (protocolo.getCodigo() == null || protocolo.getCodigo().trim().isEmpty()) {
            throw new Exception("El protocolo de reemplazo debe tener un numero_id valido.");
        }
        String idAnterior = arreglo_protocolos[indice].getCodigo();
        arreglo_protocolos[indice] = protocolo;
        return "OK [MODIFICAR]: Posicion [" + indice + "] actualizada. numero_id '"
                + idAnterior + "' -> '" + protocolo.getCodigo() + "'.";
    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception Si el índice está fuera de rango o la posición ya está vacía.
     */
    @Override
    public String eliminar(int indice) throws Exception {
        if (indice < 0 || indice >= arreglo_protocolos.length) {
            throw new Exception("Indice " + indice
                    + " fuera de rango. Tamano actual: " + arreglo_protocolos.length);
        }
        if (arreglo_protocolos[indice] == null) {
            throw new Exception("No existe protocolo en la posicion ["
                    + indice + "]. Ya esta vacia.");
        }
        String idEliminado = arreglo_protocolos[indice].getCodigo();
        arreglo_protocolos[indice] = null;
        return "OK [ELIMINAR]: Protocolo con numero_id '" + idEliminado
                + "' eliminado de la posicion [" + indice + "].";
    }

    // =======================================================================
    // OPERACIONES DE ARCHIVO
    // =======================================================================

    /**
     * {@inheritDoc}
     *
     * @throws Exception   Si el arreglo está completamente vacío.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    @Override
    public String serializar() throws IOException, Exception {
        boolean hayDatos = false;
        for (Protocolo p : arreglo_protocolos) {
            if (p != null) { hayDatos = true; break; }
        }
        if (!hayDatos) {
            throw new Exception("El arreglo esta vacio. No hay datos para serializar.");
        }

        // IOException se propaga directamente al invocador (sin try-catch interno)
        BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO));
        int escritos = 0;
        for (Protocolo p : arreglo_protocolos) {
            if (p != null) {
                String tipo          = (p instanceof ProtocoloInsuficiencia) ? "INS" : "RAD";
                String numeroId      = p.getCodigo()        != null ? p.getCodigo()        : "";
                String registro      = p.getRegistro()      != null ? p.getRegistro()      : "";
                String instrucciones = p.getInstrucciones() != null ? p.getInstrucciones() : "";
                String limites       = p.getLimites()       != null ? p.getLimites()       : "";

                bw.write(tipo + SEP + numeroId + SEP + registro + SEP + instrucciones + SEP + limites);
                bw.newLine();
                escritos++;
            }
        }
        bw.close();
        return "OK [SERIALIZAR]: " + escritos + " protocolo(s) guardados en '"
                + NOMBRE_ARCHIVO + "'.";
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException Si el archivo no existe o no puede leerse.
     */
    @Override
    public Protocolo[] deserializar() throws IOException {
        // Primera pasada: contar líneas (IOException se propaga al invocador)
        int totalLineas = 0;
        BufferedReader br1 = new BufferedReader(new FileReader(NOMBRE_ARCHIVO));
        while (br1.readLine() != null) totalLineas++;
        br1.close();

        Protocolo[] resultado = new Protocolo[totalLineas];
        int idx = 0;

        // Segunda pasada: reconstruir objetos (IOException se propaga al invocador)
        BufferedReader br2 = new BufferedReader(new FileReader(NOMBRE_ARCHIVO));
        String linea;
        while ((linea = br2.readLine()) != null) {
            String[] partes = linea.split(SEP, -1);

            if (partes.length < 5) {
                System.out.println("AVISO [DESERIALIZAR]: Linea con formato incorrecto, se omite: ["
                        + linea + "]");
                continue;
            }

            String tipo          = partes[0];
            String numeroIdStr   = partes[1];
            String registro      = partes[2];
            String instrucciones = partes[3];
            String limites       = partes[4];

            // Validar que numero_id sea entero; si no, omitir la línea sin try-catch
            boolean esEntero = !numeroIdStr.isEmpty();
            for (int i = 0; i < numeroIdStr.length() && esEntero; i++) {
                if (i == 0 && numeroIdStr.charAt(i) == '-') continue;
                if (!Character.isDigit(numeroIdStr.charAt(i))) esEntero = false;
            }
            if (!esEntero) {
                System.out.println("AVISO [DESERIALIZAR]: numero_id '" + numeroIdStr
                        + "' no es entero, se omite la linea.");
                continue;
            }

            int numero_id = Integer.parseInt(numeroIdStr);

            if (tipo.equals("INS")) {
                resultado[idx] = new ProtocoloInsuficiencia(
                        numero_id, registro, instrucciones, limites, null, null, null);
            } else if (tipo.equals("RAD")) {
                resultado[idx] = new ProtocoloRadiacion(
                        numero_id, registro, instrucciones, limites, null, null, null);
            } else {
                System.out.println("AVISO [DESERIALIZAR]: Tipo '" + tipo + "' desconocido, se omite.");
                continue;
            }
            idx++;
        }
        br2.close();

        System.out.println("OK [DESERIALIZAR]: " + idx + " protocolo(s) cargados desde '"
                + NOMBRE_ARCHIVO + "'.");
        return resultado;
    }

    // =======================================================================
    // MÉTODOS UTILITARIOS
    // =======================================================================

    /**
     * Busca el índice de un protocolo según su numero_id (como String).
     *
     * @param codigo numero_id del protocolo a buscar.
     * @return Índice en el arreglo, o {@code -1} si no se encuentra.
     */
    public int buscarIndicePorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) return -1;
        for (int i = 0; i < arreglo_protocolos.length; i++) {
            if (arreglo_protocolos[i] != null
                    && arreglo_protocolos[i].getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna el arreglo completo de protocolos.
     *
     * @return Arreglo actual de {@link Protocolo}.
     */
    public Protocolo[] getArreglo_protocolos() { return arreglo_protocolos; }

    /**
     * Retorna el tamaño actual del arreglo.
     *
     * @return Tamaño del arreglo incluyendo posiciones vacías.
     */
    public int getTamano() { return arreglo_protocolos.length; }
}