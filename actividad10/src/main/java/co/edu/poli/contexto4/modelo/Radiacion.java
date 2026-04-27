package co.edu.poli.contexto4.modelo;

/**
 * Representa la radiación espacial detectada en el entorno de una misión.
 * Contiene información sobre la cantidad, nivel de peligro, tipo,
 * intensidad y origen de la radiación.
 *
* @author Mateo Paredes
 * @since 06/04/2026
 */
public class Radiacion {

    /** Cantidad de radiación medida en sieverts (Sv). */
    private double cantidad_sievert;

    /** Nivel de peligro de la radiación (BAJO, MEDIO, ALTO). */
    private String nivel_de_peligro;

    /** Tipo de radiación (Alpha, Beta, Gamma, etc.). */
    private String tipo;

    /** Intensidad de la radiación (Baja, Media, Alta). */
    private String intensidad;

    /** Origen de la radiación (Solar, Cósmica, etc.). */
    private String origen;

    /**
     * Constructor vacío. Crea un objeto Radiacion sin inicializar atributos.
     */
    public Radiacion() {}

    /**
     * Constructor con parámetros. Inicializa todos los atributos de la radiación.
     *
     * @param cantidad_sievert Cantidad de radiación en sieverts.
     * @param nivel_de_peligro Nivel de peligro asociado.
     * @param tipo             Tipo de radiación.
     * @param intensidad       Intensidad de la radiación.
     * @param origen           Origen de la radiación.
     */
    public Radiacion(double cantidad_sievert, String nivel_de_peligro, String tipo,
                     String intensidad, String origen) {
        this.cantidad_sievert = cantidad_sievert;
        this.nivel_de_peligro = nivel_de_peligro;
        this.tipo = tipo;
        this.intensidad = intensidad;
        this.origen = origen;
    }

    /**
     * Mide y retorna la cantidad de radiación actual en sieverts.
     *
     * @return Cantidad de radiación en sieverts.
     */
    public double medir_radiacion() {
        System.out.println("[Radiacion] Midiendo radiacion: " + cantidad_sievert + " sieverts.");
        return cantidad_sievert;
    }

    /** @return Cantidad de radiación en sieverts. */
    public double getCantidad_sievert() { return cantidad_sievert; }

    /** @param cantidad_sievert Nueva cantidad de radiación en sieverts. */
    public void setCantidad_sievert(double cantidad_sievert) { this.cantidad_sievert = cantidad_sievert; }

    /** @return Nivel de peligro de la radiación. */
    public String getNivel_de_peligro() { return nivel_de_peligro; }

    /** @param nivel_de_peligro Nuevo nivel de peligro. */
    public void setNivel_de_peligro(String nivel_de_peligro) { this.nivel_de_peligro = nivel_de_peligro; }

    /** @return Tipo de radiación. */
    public String getTipo() { return tipo; }

    /** @param tipo Nuevo tipo de radiación. */
    public void setTipo(String tipo) { this.tipo = tipo; }

    /** @return Intensidad de la radiación. */
    public String getIntensidad() { return intensidad; }

    /** @param intensidad Nueva intensidad de la radiación. */
    public void setIntensidad(String intensidad) { this.intensidad = intensidad; }

    /** @return Origen de la radiación. */
    public String getOrigen() { return origen; }

    /** @param origen Nuevo origen de la radiación. */
    public void setOrigen(String origen) { this.origen = origen; }

    /**
     * Retorna una representación textual de la radiación con sus atributos principales.
     *
     * @return Cadena con cantidad, nivel de peligro, tipo y origen.
     */
    @Override
    public String toString() {
        return "Radiacion{cantidad_sievert=" + cantidad_sievert
                + ", nivel_de_peligro='" + nivel_de_peligro
                + "', tipo='" + tipo + "', origen='" + origen + "'}";
    }
}