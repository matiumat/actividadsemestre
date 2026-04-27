package co.edu.poli.contexto4.modelo;

/**
 * Representa un sensor utilizado en el traje o protocolo espacial.
 * Se encarga de medir señales físicas del astronauta, como el ritmo cardíaco.
 *
 * @author Mateo Paredes
 * @since 06/04/2026
 */
public class Sensor {

    /** Número de serie único que identifica al sensor. */
    private String serial;

    /** Material con el que está fabricado el sensor. */
    private String material;

    /** Color del sensor. */
    private String color;

    /** Tamaño del sensor en centímetros. */
    private double tamanio;

    /** Peso del sensor en kilogramos. */
    private double peso;

    /**
     * Constructor vacío. Crea un sensor sin inicializar atributos.
     */
    public Sensor() {}

    /**
     * Constructor con parámetros. Inicializa todos los atributos del sensor.
     *
     * @param serial   Número de serie del sensor.
     * @param material Material de fabricación del sensor.
     * @param color    Color del sensor.
     * @param tamanio  Tamaño del sensor en centímetros.
     * @param peso     Peso del sensor en kilogramos.
     */
    public Sensor(String serial, String material, String color, double tamanio, double peso) {
        this.serial = serial;
        this.material = material;
        this.color = color;
        this.tamanio = tamanio;
        this.peso = peso;
    }

    /**
     * Mide el ritmo cardíaco del astronauta a partir de los latidos base.
     *
     * @param latidos Número de latidos por minuto medidos.
     * @return Valor calculado del ritmo cardíaco procesado.
     */
    public int medir_ritmo_cardiaco(int latidos) {
        System.out.println("[Sensor] Midiendo ritmo cardiaco con " + latidos + " latidos base.");
        return latidos * 2;
    }

    /**
     * Mide el ritmo cardíaco del astronauta según el modo de medición indicado.
     * Sobrecarga del método {@link #medir_ritmo_cardiaco(int)}.
     *
     * @param latidos Número de latidos por minuto medidos.
     * @param modo    Modo de medición: "rapido" aplica un factor mayor.
     * @return Valor calculado del ritmo cardíaco según el modo.
     */
    public int medir_ritmo_cardiaco(int latidos, String modo) {
        System.out.println("[Sensor - SOBRECARGA] Midiendo ritmo cardiaco en modo '"
                + modo + "' con " + latidos + " latidos.");
        if (modo.equalsIgnoreCase("rapido")) {
            return latidos * 3;
        }
        return latidos * 2;
    }

    /**
     * Retorna el número de serie del sensor.
     * @return serial del sensor.
     */
    public String getSerial() { return serial; }

    /**
     * Establece el número de serie del sensor.
     * @param serial Nuevo serial a asignar.
     */
    public void setSerial(String serial) { this.serial = serial; }

    /**
     * Retorna el material del sensor.
     * @return material del sensor.
     */
    public String getMaterial() { return material; }

    /**
     * Establece el material del sensor.
     * @param material Nuevo material a asignar.
     */
    public void setMaterial(String material) { this.material = material; }

    /**
     * Retorna el color del sensor.
     * @return color del sensor.
     */
    public String getColor() { return color; }

    /**
     * Establece el color del sensor.
     * @param color Nuevo color a asignar.
     */
    public void setColor(String color) { this.color = color; }

    /**
     * Retorna el tamaño del sensor.
     * @return tamaño en centímetros.
     */
    public double getTamanio() { return tamanio; }

    /**
     * Establece el tamaño del sensor.
     * @param tamanio Nuevo tamaño en centímetros.
     */
    public void setTamanio(double tamanio) { this.tamanio = tamanio; }

    /**
     * Retorna el peso del sensor.
     * @return peso en kilogramos.
     */
    public double getPeso() { return peso; }

    /**
     * Establece el peso del sensor.
     * @param peso Nuevo peso en kilogramos.
     */
    public void setPeso(double peso) { this.peso = peso; }

    /**
     * Retorna una representación textual del sensor con sus atributos principales.
     *
     * @return Cadena con serial, material y color del sensor.
     */
    @Override
    public String toString() {
        return "Sensor{serial='" + serial + "', material='" + material + "', color='" + color + "'}";
    }
}