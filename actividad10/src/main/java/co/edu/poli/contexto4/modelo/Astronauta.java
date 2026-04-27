package co.edu.poli.contexto4.modelo;

import co.edu.poli.contexto4.modelo.Traje;

/**
 * Representa a un astronauta que participa en misiones espaciales.
 * Contiene información personal, médica y operativa del astronauta,
 * así como el traje espacial asignado.
 *
 * @author Mateo Paredes
 * @since 06/04/2026
 */
public class Astronauta {

    /** Identificador único del astronauta. */
    private String id;

    /** Nombre completo del astronauta. */
    private String nombre;

    /** Peso del astronauta en kilogramos. */
    private double peso;

    /** Edad del astronauta en años. */
    private int edad;

    /** Nacionalidad del astronauta. */
    private String nacionalidad;

    /** Tipo de sangre del astronauta (ej: O+, A-, B+). */
    private String tipo_sangre;

    /** Años de experiencia del astronauta en misiones espaciales. */
    private int anios_experiencia;

    /** Sexo del astronauta. */
    private String sexo;

    /** Traje espacial asignado al astronauta. */
    private Traje traje;

    /**
     * Constructor vacío. Crea un astronauta sin inicializar atributos.
     */
    public Astronauta() {}

    /**
     * Constructor con parámetros. Inicializa todos los atributos del astronauta.
     *
     * @param id                Identificador único.
     * @param nombre            Nombre completo.
     * @param peso              Peso en kilogramos.
     * @param edad              Edad en años.
     * @param nacionalidad      Nacionalidad.
     * @param tipo_sangre       Tipo de sangre.
     * @param anios_experiencia Años de experiencia en misiones.
     * @param sexo              Sexo del astronauta.
     * @param traje             Traje espacial asignado.
     */
    public Astronauta(String id, String nombre, double peso, int edad, String nacionalidad,
                      String tipo_sangre, int anios_experiencia, String sexo, Traje traje) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.tipo_sangre = tipo_sangre;
        this.anios_experiencia = anios_experiencia;
        this.sexo = sexo;
        this.traje = traje;
    }

    /** @return ID del astronauta. */
    public String getId() { return id; }

    /** @param id Nuevo ID del astronauta. */
    public void setId(String id) { this.id = id; }

    /** @return Nombre del astronauta. */
    public String getNombre() { return nombre; }

    /** @param nombre Nuevo nombre del astronauta. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return Peso del astronauta en kg. */
    public double getPeso() { return peso; }

    /** @param peso Nuevo peso en kg. */
    public void setPeso(double peso) { this.peso = peso; }

    /** @return Edad del astronauta. */
    public int getEdad() { return edad; }

    /** @param edad Nueva edad del astronauta. */
    public void setEdad(int edad) { this.edad = edad; }

    /** @return Nacionalidad del astronauta. */
    public String getNacionalidad() { return nacionalidad; }

    /** @param nacionalidad Nueva nacionalidad. */
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    /** @return Tipo de sangre del astronauta. */
    public String getTipo_sangre() { return tipo_sangre; }

    /** @param tipo_sangre Nuevo tipo de sangre. */
    public void setTipo_sangre(String tipo_sangre) { this.tipo_sangre = tipo_sangre; }

    /** @return Años de experiencia del astronauta. */
    public int getAnios_experiencia() { return anios_experiencia; }

    /** @param anios_experiencia Nuevos años de experiencia. */
    public void setAnios_experiencia(int anios_experiencia) { this.anios_experiencia = anios_experiencia; }

    /** @return Sexo del astronauta. */
    public String getSexo() { return sexo; }

    /** @param sexo Nuevo sexo del astronauta. */
    public void setSexo(String sexo) { this.sexo = sexo; }

    /** @return Traje asignado al astronauta. */
    public Traje getTraje() { return traje; }

    /** @param traje Nuevo traje a asignar. */
    public void setTraje(Traje traje) { this.traje = traje; }

    /**
     * Retorna una representación textual del astronauta con sus datos principales.
     *
     * @return Cadena con id, nombre, edad, nacionalidad y sexo.
     */
    @Override
    public String toString() {
        return "Astronauta{id='" + id + "', nombre='" + nombre + "', edad=" + edad
                + ", nacionalidad='" + nacionalidad + "', sexo='" + sexo + "'}";
    }
}