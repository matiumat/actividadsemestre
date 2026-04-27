package co.edu.poli.contexto4.modelo;

import co.edu.poli.contexto4.modelo.Radiacion;

/**
 * Representa el plan de mitigación ante un evento de radiación espacial.
 * Define la estrategia, límite de tolerancia y localización del protocolo
 * de respuesta, así como la radiación asociada que se debe controlar.
 *
* @author Mateo Paredes
 * @since 06/04/2026
 */
public class Mitigacion {

    /** Código único que identifica el plan de mitigación. */
    private String codigo;

    /** Estrategia de mitigación a aplicar (ej: Blindaje reforzado). */
    private String estrategia;

    /** Límite de tolerancia de radiación en sieverts (Sv). */
    private int limite;

    /** Localización del módulo donde se aplica la mitigación. */
    private String localizacion;

    /** Objeto de radiación asociado a esta mitigación. */
    private Radiacion radiacion;

    /**
     * Constructor vacío. Crea una mitigación sin inicializar atributos.
     */
    public Mitigacion() {}

    /**
     * Constructor con parámetros. Inicializa todos los atributos de la mitigación.
     *
     * @param codigo       Código único del plan de mitigación.
     * @param estrategia   Estrategia a aplicar.
     * @param limite       Límite de radiación tolerable en sieverts.
     * @param localizacion Módulo o zona donde se aplica.
     * @param radiacion    Objeto de radiación asociado.
     */
    public Mitigacion(String codigo, String estrategia, int limite,
                      String localizacion, Radiacion radiacion) {
        this.codigo = codigo;
        this.estrategia = estrategia;
        this.limite = limite;
        this.localizacion = localizacion;
        this.radiacion = radiacion;
    }

    /**
     * Evalúa la cantidad de radiación recibida y aplica la estrategia de mitigación.
     * Si la cantidad supera el límite, emite una alerta.
     *
     * @param cantidad_radiacion Cantidad de radiación detectada en sieverts.
     * @return Mensaje con el resultado de la mitigación aplicada.
     */
    public String mitigar_radiacion(double cantidad_radiacion) {
        System.out.println("[Mitigacion] Mitigando radiacion de "
                + cantidad_radiacion + " sieverts con estrategia: " + estrategia);
        if (cantidad_radiacion > limite) {
            return "ALERTA: Radiacion " + cantidad_radiacion
                    + " supera limite " + limite + ". Aplicando: " + estrategia;
        }
        return "Radiacion " + cantidad_radiacion
                + " dentro del limite. Estrategia: " + estrategia;
    }

    /** @return Código del plan de mitigación. */
    public String getCodigo() { return codigo; }

    /** @param codigo Nuevo código del plan. */
    public void setCodigo(String codigo) { this.codigo = codigo; }

    /** @return Estrategia de mitigación. */
    public String getEstrategia() { return estrategia; }

    /** @param estrategia Nueva estrategia. */
    public void setEstrategia(String estrategia) { this.estrategia = estrategia; }

    /** @return Límite de radiación tolerable. */
    public int getLimite() { return limite; }

    /** @param limite Nuevo límite de tolerancia. */
    public void setLimite(int limite) { this.limite = limite; }

    /** @return Localización donde se aplica la mitigación. */
    public String getLocalizacion() { return localizacion; }

    /** @param localizacion Nueva localización. */
    public void setLocalizacion(String localizacion) { this.localizacion = localizacion; }

    /** @return Radiación asociada a esta mitigación. */
    public Radiacion getRadiacion() { return radiacion; }

    /** @param radiacion Nueva radiación asociada. */
    public void setRadiacion(Radiacion radiacion) { this.radiacion = radiacion; }

    /**
     * Retorna una representación textual de la mitigación.
     *
     * @return Cadena con código, estrategia y límite.
     */
    @Override
    public String toString() {
        return "Mitigacion{codigo='" + codigo + "', estrategia='"
                + estrategia + "', limite=" + limite + "}";
    }
}