package co.edu.poli.contexto4.modelo;

import co.edu.poli.contexto4.modelo.Radiacion;
import co.edu.poli.contexto4.modelo.Sensor;

/**
 * Subclase concreta de {@link Protocolo} que representa el protocolo
 * de manejo de insuficiencia de radiación.
 * Se activa cuando los niveles de radiación están por debajo del mínimo
 * aceptable para la misión.
 *
 * @author Equipo Contexto 4
 * @version 2.0
 */
public class ProtocoloInsuficiencia extends Protocolo {

    /** Identificador numérico único del protocolo de insuficiencia. */
    private int numero_id;

    /**
     * Constructor vacío.
     */
    public ProtocoloInsuficiencia() {}

    /**
     * Constructor con parámetros.
     *
     * @param numero_id     Identificador numérico único (int).
     * @param registro      Registro asociado al protocolo.
     * @param instrucciones Instrucciones del procedimiento.
     * @param limites       Límites mínimos de radiación.
     * @param mitigacion    Plan de mitigación asociado.
     * @param sensor        Sensor utilizado.
     * @param radiacion     Radiación monitoreada.
     */
    public ProtocoloInsuficiencia(int numero_id, String registro, String instrucciones,
                                   String limites, Mitigacion mitigacion,
                                   Sensor sensor, Radiacion radiacion) {
        // El codigo en el padre se guarda como String del numero_id
        super(String.valueOf(numero_id), registro, instrucciones, limites,
              mitigacion, sensor, radiacion);
        this.numero_id = numero_id;
    }

    /**
     * Implementación del método abstracto de {@link Protocolo}.
     *
     * @return Información del protocolo de insuficiencia.
     */
    @Override
    public String leer_informacion() {
        return "ProtocoloInsuficiencia | numero_id: " + numero_id
                + " | Instrucciones: " + getInstrucciones()
                + " | Limites: " + getLimites();
    }

    /**
     * Sobreescritura de controlar_limites para insuficiencia.
     *
     * @param cantidad_radiacion Radiación detectada en sieverts.
     * @return Resultado del control.
     */
    @Override
    public String controlar_limites(double cantidad_radiacion) {
        System.out.println("[ProtocoloInsuficiencia - SOBREESCRITURA] Controlando limites para "
                + cantidad_radiacion + " sieverts.");
        if (cantidad_radiacion < 0.1) {
            return "INSUFICIENCIA DETECTADA: Radiacion " + cantidad_radiacion
                    + " muy baja. numero_id: " + numero_id;
        }
        return "Radiacion " + cantidad_radiacion
                + " aceptable para protocolo insuficiencia. numero_id: " + numero_id;
    }

    /** @return numero_id del protocolo. */
    public int getNumero_id() { return numero_id; }

    /** @param numero_id Nuevo numero_id. */
    public void setNumero_id(int numero_id) {
        this.numero_id = numero_id;
        setCodigo(String.valueOf(numero_id));
    }

    /**
     * Representación textual del protocolo.
     *
     * @return Cadena con numero_id e instrucciones.
     */
    @Override
    public String toString() {
        return "ProtocoloInsuficiencia{numero_id=" + numero_id
                + ", instrucciones='" + getInstrucciones() + "'}";
    }
}