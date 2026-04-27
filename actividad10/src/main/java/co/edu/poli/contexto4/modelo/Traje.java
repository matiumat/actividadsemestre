package co.edu.poli.contexto4.modelo;

/**
 * Representa el traje espacial utilizado por un astronauta.
 * Contiene información sobre sus características físicas y el sensor asociado.
 * El atributo {@code color} es estático, por lo que es compartido
 * por todas las instancias de la clase.
 *
 * @author Mateo Paredes
 * @since 06/04/2026
 */
public class Traje {

    /** Número de serie único del traje. */
    private String serial;

    /** Nivel de calidad del traje (Alta, Media, Baja). */
    private String calidad;

    /** Material de blindaje del traje. */
    private String blindaje;

    /** Tipo de traje (Extravehicular, Intravehicular, etc.). */
    private String tipo;

    /**
     * Color del traje. Atributo estático: es compartido por todos los objetos Traje.
     * Al modificarlo, todos los objetos reflejan el nuevo valor.
     */
    public static String color;

    /** Función principal del traje dentro de la misión. */
    private String funcion;

    /** Sensor integrado en el traje para monitoreo del astronauta. */
    private Sensor sensor;

    /**
     * Constructor vacío. Crea un traje sin inicializar atributos.
     */
    public Traje() {}

    /**
     * Constructor con parámetros. Inicializa todos los atributos del traje.
     *
     * @param serial   Número de serie del traje.
     * @param calidad  Nivel de calidad del traje.
     * @param blindaje Material de blindaje.
     * @param tipo     Tipo de traje.
     * @param funcion  Función principal del traje.
     * @param sensor   Sensor integrado al traje.
     */
    public Traje(String serial, String calidad, String blindaje, String tipo,
                 String funcion, Sensor sensor) {
        this.serial = serial;
        this.calidad = calidad;
        this.blindaje = blindaje;
        this.tipo = tipo;
        this.funcion = funcion;
        this.sensor = sensor;
    }

    /** @return Serial del traje. */
    public String getSerial() { return serial; }

    /** @param serial Nuevo serial del traje. */
    public void setSerial(String serial) { this.serial = serial; }

    /** @return Calidad del traje. */
    public String getCalidad() { return calidad; }

    /** @param calidad Nueva calidad del traje. */
    public void setCalidad(String calidad) { this.calidad = calidad; }

    /** @return Blindaje del traje. */
    public String getBlindaje() { return blindaje; }

    /** @param blindaje Nuevo blindaje del traje. */
    public void setBlindaje(String blindaje) { this.blindaje = blindaje; }

    /** @return Tipo del traje. */
    public String getTipo() { return tipo; }

    /** @param tipo Nuevo tipo del traje. */
    public void setTipo(String tipo) { this.tipo = tipo; }

    /**
     * Retorna el color estático compartido por todos los objetos Traje.
     * @return Color actual de todos los trajes.
     */
    public static String getColor() { return color; }

    /**
     * Modifica el color estático. Al ser estático, el cambio afecta
     * a todos los objetos Traje existentes.
     * @param nuevoColor Nuevo color a asignar.
     */
    public static void setColor(String nuevoColor) { color = nuevoColor; }

    /** @return Función del traje. */
    public String getFuncion() { return funcion; }

    /** @param funcion Nueva función del traje. */
    public void setFuncion(String funcion) { this.funcion = funcion; }

    /** @return Sensor integrado al traje. */
    public Sensor getSensor() { return sensor; }

    /** @param sensor Nuevo sensor a integrar al traje. */
    public void setSensor(Sensor sensor) { this.sensor = sensor; }

    /**
     * Retorna una representación textual del traje con sus atributos principales.
     *
     * @return Cadena con serial, calidad, color estático y tipo del traje.
     */
    @Override
    public String toString() {
        return "Traje{serial='" + serial + "', calidad='" + calidad
                + "', color(static)='" + color + "', tipo='" + tipo + "'}";
    }
}