package co.edu.poli.contexto4.modelo;

/**
 * Representa un registro de actividad asociado a un astronauta dentro de una misión.
 * Almacena el número de registro, la fecha, el astronauta involucrado y los datos
 * relevantes del evento registrado.
 *
 * @author Mateo Paredes
 * @since 06/04/2026
 */
public class Registro {

    /** Número único que identifica el registro. */
    private int numero_registro;

    /** Fecha del registro en formato numérico (yyyyMMdd). */
    private int fecha;

    /** Astronauta al que pertenece este registro. */
    private Astronauta astronauta;

    /** Datos o descripción del evento registrado. */
    private String datos;

    /**
     * Constructor vacío. Crea un registro sin inicializar atributos.
     */
    public Registro() {}

    /**
     * Constructor con parámetros. Inicializa todos los atributos del registro.
     *
     * @param numero_registro Número único del registro.
     * @param fecha           Fecha en formato numérico (yyyyMMdd).
     * @param astronauta      Astronauta asociado al registro.
     * @param datos           Descripción del evento registrado.
     */
    public Registro(int numero_registro, int fecha, Astronauta astronauta, String datos) {
        this.numero_registro = numero_registro;
        this.fecha = fecha;
        this.astronauta = astronauta;
        this.datos = datos;
    }

    /**
     * Registra y retorna los datos del astronauta asociado a este registro.
     * Imprime un mensaje por consola indicando el nombre del astronauta.
     *
     * @return Cadena con número de registro, fecha, nombre del astronauta y datos.
     */
    public String registrar_datos_astronauta() {
        System.out.println("[Registro] Registrando datos del astronauta: "
                + (astronauta != null ? astronauta.getNombre() : "Sin astronauta"));
        return "Registro #" + numero_registro
                + " | Fecha: " + fecha
                + " | Astronauta: " + (astronauta != null ? astronauta.getNombre() : "N/A")
                + " | Datos: " + datos;
    }

    /** @return Número de registro. */
    public int getNumero_registro() { return numero_registro; }

    /** @param numero_registro Nuevo número de registro. */
    public void setNumero_registro(int numero_registro) { this.numero_registro = numero_registro; }

    /** @return Fecha del registro. */
    public int getFecha() { return fecha; }

    /** @param fecha Nueva fecha del registro. */
    public void setFecha(int fecha) { this.fecha = fecha; }

    /** @return Astronauta asociado al registro. */
    public Astronauta getAstronauta() { return astronauta; }

    /** @param astronauta Nuevo astronauta a asociar. */
    public void setAstronauta(Astronauta astronauta) { this.astronauta = astronauta; }

    /** @return Datos del evento registrado. */
    public String getDatos() { return datos; }

    /** @param datos Nuevos datos del evento. */
    public void setDatos(String datos) { this.datos = datos; }

    /**
     * Retorna una representación textual del registro con sus atributos principales.
     *
     * @return Cadena con número, fecha, nombre del astronauta y datos.
     */
    @Override
    public String toString() {
        return "Registro{numero_registro=" + numero_registro
                + ", fecha=" + fecha
                + ", astronauta='" + (astronauta != null ? astronauta.getNombre() : "N/A")
                + "', datos='" + datos + "'}";
    }
}