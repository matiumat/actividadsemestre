package co.edu.poli.contexto4.servicios;

import co.edu.poli.contexto4.modelo.Mitigacion;

/**
 * Clase del paquete de servicios que representa un protocolo de emergencia.
 * Se activa ante situaciones críticas de radiación y aplica las medidas
 * de emergencia necesarias según el nivel de peligro configurado.
 *
* @author Mateo Paredes
 * @since 06/04/2026
 */
public class ProtocoloEmergencia {

    /**
     * Nivel de peligro del protocolo de emergencia.
     * Determina la severidad de la respuesta aplicada.
     */
    private int nivel_peligro;

    /**
     * Constructor vacío. Crea un protocolo de emergencia sin inicializar atributos.
     */
    public ProtocoloEmergencia() {}

    /**
     * Constructor con parámetros. Inicializa el nivel de peligro del protocolo.
     *
     * @param nivel_peligro Nivel de peligro de la emergencia (escala numérica).
     */
    public ProtocoloEmergencia(int nivel_peligro) {
        this.nivel_peligro = nivel_peligro;
    }

    /**
     * Aplica el protocolo de emergencia evaluando la cantidad de radiación recibida,
     * el plan de mitigación disponible y la dosis a administrar.
     *
     * @param cantidad_radiacion Cantidad de radiación detectada en sieverts.
     * @param mitigacion         Plan de mitigación a aplicar durante la emergencia.
     * @param dosis              Dosis de respuesta médica o de mitigación a aplicar.
     * @return Cadena describiendo el resultado de la aplicación del protocolo de emergencia.
     */
    public String aplicar_protocolo(double cantidad_radiacion, Mitigacion mitigacion, String dosis) {
        System.out.println("[ProtocoloEmergencia] Aplicando protocolo de emergencia. Nivel peligro: "
                + nivel_peligro);
        return "Protocolo Emergencia aplicado | Radiacion: " + cantidad_radiacion
                + " | Mitigacion: " + (mitigacion != null ? mitigacion.getEstrategia() : "N/A")
                + " | Dosis: " + dosis;
    }

    /** @return Nivel de peligro del protocolo de emergencia. */
    public int getNivel_peligro() { return nivel_peligro; }

    /** @param nivel_peligro Nuevo nivel de peligro. */
    public void setNivel_peligro(int nivel_peligro) { this.nivel_peligro = nivel_peligro; }

    /**
     * Retorna una representación textual del protocolo de emergencia.
     *
     * @return Cadena con el nivel de peligro configurado.
     */
    @Override
    public String toString() {
        return "ProtocoloEmergencia{nivel_peligro=" + nivel_peligro + "}";
    }
}