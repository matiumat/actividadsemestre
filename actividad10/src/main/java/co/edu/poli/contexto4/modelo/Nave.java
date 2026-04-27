package co.edu.poli.contexto4.modelo;

import co.edu.poli.contexto4.modelo.Registro;

/**
 * Representa la nave espacial utilizada en una misión.
 * Contiene información sobre sus características físicas, la tripulación
 * de astronautas a bordo y el registro asociado a la nave.
 *
 * @author Mateo Paredes
 * @since 06/04/2026
 */
public class Nave {

    /** Número de serie único de la nave. */
    private String serial;

    /** Material de fabricación del casco de la nave. */
    private String material;

    /** Arreglo de astronautas que conforman la tripulación de la nave. */
    private Astronauta[] astronauta;

    /** Tamaño de la nave en metros. */
    private double tamanio;

    /** Peso de la nave en toneladas. */
    private double peso;

    /** Capacidad máxima de pasajeros de la nave. */
    private double capacidad;

    /** Registro de actividades asociado a la nave. */
    private Registro registro;

    /**
     * Constructor vacío. Crea una nave sin inicializar atributos.
     */
    public Nave() {}

    /**
     * Constructor con parámetros. Inicializa todos los atributos de la nave.
     *
     * @param serial      Número de serie de la nave.
     * @param material    Material de fabricación.
     * @param astronauta  Arreglo de astronautas a bordo.
     * @param tamanio     Tamaño en metros.
     * @param peso        Peso en toneladas.
     * @param capacidad   Capacidad máxima de pasajeros.
     * @param registro    Registro de actividades de la nave.
     */
    public Nave(String serial, String material, Astronauta[] astronauta,
                double tamanio, double peso, double capacidad, Registro registro) {
        this.serial = serial;
        this.material = material;
        this.astronauta = astronauta;
        this.tamanio = tamanio;
        this.peso = peso;
        this.capacidad = capacidad;
        this.registro = registro;
    }

    /** @return Serial de la nave. */
    public String getSerial() { return serial; }

    /** @param serial Nuevo serial de la nave. */
    public void setSerial(String serial) { this.serial = serial; }

    /** @return Material de la nave. */
    public String getMaterial() { return material; }

    /** @param material Nuevo material. */
    public void setMaterial(String material) { this.material = material; }

    /** @return Arreglo de astronautas a bordo. */
    public Astronauta[] getAstronauta() { return astronauta; }

    /** @param astronauta Nuevo arreglo de astronautas. */
    public void setAstronauta(Astronauta[] astronauta) { this.astronauta = astronauta; }

    /** @return Tamaño de la nave en metros. */
    public double getTamanio() { return tamanio; }

    /** @param tamanio Nuevo tamaño en metros. */
    public void setTamanio(double tamanio) { this.tamanio = tamanio; }

    /** @return Peso de la nave en toneladas. */
    public double getPeso() { return peso; }

    /** @param peso Nuevo peso en toneladas. */
    public void setPeso(double peso) { this.peso = peso; }

    /** @return Capacidad máxima de pasajeros. */
    public double getCapacidad() { return capacidad; }

    /** @param capacidad Nueva capacidad máxima. */
    public void setCapacidad(double capacidad) { this.capacidad = capacidad; }

    /** @return Registro asociado a la nave. */
    public Registro getRegistro() { return registro; }

    /** @param registro Nuevo registro a asociar. */
    public void setRegistro(Registro registro) { this.registro = registro; }

    /**
     * Retorna una representación textual de la nave con sus atributos principales.
     *
     * @return Cadena con serial, material y capacidad.
     */
    @Override
    public String toString() {
        return "Nave{serial='" + serial + "', material='" + material
                + "', capacidad=" + capacidad + "}";
    }
}