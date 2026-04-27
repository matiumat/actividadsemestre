package co.edu.poli.contexto4.modelo;

import co.edu.poli.contexto4.modelo.ProtocoloInsuficiencia;
import co.edu.poli.contexto4.modelo.ProtocoloRadiacion;
import co.edu.poli.contexto4.modelo.Radiacion;
import co.edu.poli.contexto4.modelo.Sensor;

/**
 * Clase abstracta que representa un protocolo de seguridad espacial.
 * Es la <strong>supersuperclase</strong> de la jerarquía de protocolos.
 * No puede instanciarse directamente; deben usarse sus subclases concretas
 * ({@link ProtocoloInsuficiencia}, {@link ProtocoloRadiacion}).
 * <p>
 * Define el contrato que todas las subclases deben cumplir mediante el
 * método abstracto {@link #leer_informacion()}.
 * </p>
 *
 * @author Mateo Paredes
 * @since 06/04/2026
 * @see ProtocoloInsuficiencia
 * @see ProtocoloRadiacion
 */
public abstract class Protocolo {

    /**
     * Código único del protocolo. Se define en el padre para que el CRUD
     * pueda identificar objetos por ID sin importar la subclase.
     */
    private String codigo;

    /** Número o nombre del registro asociado al protocolo. */
    private String registro;

    /** Instrucciones que definen el procedimiento a seguir. */
    private String instrucciones;

    /** Límites de radiación configurados en este protocolo. */
    private String limites;

    /** Plan de mitigación asociado al protocolo. */
    private Mitigacion mitigacion;

    /** Sensor utilizado para las mediciones del protocolo. */
    private Sensor sensor;

    /** Radiación que este protocolo controla o monitorea. */
    private Radiacion radiacion;

    /**
     * Constructor vacío. Crea un protocolo sin inicializar atributos.
     */
    public Protocolo() {}

    /**
     * Constructor con parámetros. Inicializa todos los atributos del protocolo.
     *
     * @param codigo        Código único del protocolo (usado como ID en el CRUD).
     * @param registro      Registro asociado al protocolo.
     * @param instrucciones Instrucciones del procedimiento.
     * @param limites       Límites de radiación configurados.
     * @param mitigacion    Plan de mitigación asociado.
     * @param sensor        Sensor utilizado en el protocolo.
     * @param radiacion     Radiación que controla el protocolo.
     */
    public Protocolo(String codigo, String registro, String instrucciones, String limites,
                     Mitigacion mitigacion, Sensor sensor, Radiacion radiacion) {
        this.codigo = codigo;
        this.registro = registro;
        this.instrucciones = instrucciones;
        this.limites = limites;
        this.mitigacion = mitigacion;
        this.sensor = sensor;
        this.radiacion = radiacion;
    }

    /**
     * Método abstracto que cada subclase debe implementar obligatoriamente.
     * Retorna información descriptiva del protocolo específico.
     *
     * @return Cadena con la información relevante del protocolo concreto.
     */
    public abstract String leer_informacion();

    /**
     * Controla los límites de radiación para la cantidad indicada.
     * Este método puede ser sobreescrito por las subclases para personalizar
     * el comportamiento según el tipo de protocolo.
     *
     * @param cantidad_radiacion Cantidad de radiación a evaluar en sieverts.
     * @return Mensaje con el resultado del control de límites.
     */
    public String controlar_limites(double cantidad_radiacion) {
        System.out.println("[Protocolo - BASE] Controlando limites para "
                + cantidad_radiacion + " sieverts.");
        return "Limite base verificado: " + limites;
    }

    /**
     * Retorna la descripción general del protocolo.
     * Este método es {@code final}: ninguna subclase puede sobreescribirlo,
     * garantizando que la descripción base siempre sea la misma.
     *
     * @return Cadena con el código, instrucciones y límites del protocolo.
     */
    public final String obtener_descripcion_protocolo() {
        return "Protocolo[" + codigo + "] | Instrucciones: "
                + instrucciones + " | Limites: " + limites;
    }

    /**
     * Modifica el protocolo usando las instrucciones actuales.
     *
     * @return Mensaje confirmando la modificación del protocolo.
     */
    public String modificar_protocolo() {
        return "Protocolo modificado: " + instrucciones;
    }

    /**
     * Modifica el protocolo con una vigencia específica.
     * Sobrecarga de {@link #modificar_protocolo()}.
     *
     * @param vigencia Período de vigencia a aplicar al protocolo.
     * @return Mensaje confirmando la modificación con la vigencia indicada.
     */
    public String modificar_protocolo(String vigencia) {
        return "Protocolo modificado con vigencia '" + vigencia + "': " + instrucciones;
    }

    /** @return Código único del protocolo. */
    public String getCodigo() { return codigo; }

    /** @param codigo Nuevo código del protocolo. */
    public void setCodigo(String codigo) { this.codigo = codigo; }

    /** @return Registro asociado. */
    public String getRegistro() { return registro; }

    /** @param registro Nuevo registro. */
    public void setRegistro(String registro) { this.registro = registro; }

    /** @return Instrucciones del protocolo. */
    public String getInstrucciones() { return instrucciones; }

    /** @param instrucciones Nuevas instrucciones. */
    public void setInstrucciones(String instrucciones) { this.instrucciones = instrucciones; }

    /** @return Límites de radiación. */
    public String getLimites() { return limites; }

    /** @param limites Nuevos límites. */
    public void setLimites(String limites) { this.limites = limites; }

    /** @return Plan de mitigación asociado. */
    public Mitigacion getMitigacion() { return mitigacion; }

    /** @param mitigacion Nuevo plan de mitigación. */
    public void setMitigacion(Mitigacion mitigacion) { this.mitigacion = mitigacion; }

    /** @return Sensor asociado al protocolo. */
    public Sensor getSensor() { return sensor; }

    /** @param sensor Nuevo sensor. */
    public void setSensor(Sensor sensor) { this.sensor = sensor; }

    /** @return Radiación que controla el protocolo. */
    public Radiacion getRadiacion() { return radiacion; }

    /** @param radiacion Nueva radiación asociada. */
    public void setRadiacion(Radiacion radiacion) { this.radiacion = radiacion; }
}