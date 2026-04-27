package co.edu.poli.contexto4.modelo;

import co.edu.poli.contexto4.modelo.Registro;

/**
 * Representa la bitácora de una misión espacial.
 * Almacena el código, fecha, datos generales y el registro
 * de actividades asociado a la bitácora.
 *
* @author Mateo Paredes
 * @since 06/04/2026
 */
public class Bitacora {

    /** Código único que identifica la bitácora. */
    private String codigo;

    /** Fecha de la bitácora en formato numérico (yyyyMMdd). */
    private int fecha;

    /** Datos o descripción general de la bitácora. */
    private String datos;

    /** Registro de actividades asociado a esta bitácora. */
    private Registro registro;

    /**
     * Constructor vacío. Crea una bitácora sin inicializar atributos.
     */
    public Bitacora() {}

    /**
     * Constructor con parámetros. Inicializa todos los atributos de la bitácora.
     *
     * @param codigo   Código único de la bitácora.
     * @param fecha    Fecha en formato numérico (yyyyMMdd).
     * @param datos    Descripción general de la bitácora.
     * @param registro Registro de actividades asociado.
     */
    public Bitacora(String codigo, int fecha, String datos, Registro registro) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.datos = datos;
        this.registro = registro;
    }

    /**
     * Registra y retorna la información almacenada en la bitácora.
     * Imprime por consola el código de la bitácora que se está procesando.
     *
     * @return Cadena con código, fecha y datos de la bitácora.
     */
    public String registrar_informacion_bitacora() {
        System.out.println("[Bitacora] Registrando informacion en bitacora codigo: " + codigo);
        return "Bitacora{codigo='" + codigo + "', fecha=" + fecha + ", datos='" + datos + "'}";
    }

    /** @return Código de la bitácora. */
    public String getCodigo() { return codigo; }

    /** @param codigo Nuevo código de la bitácora. */
    public void setCodigo(String codigo) { this.codigo = codigo; }

    /** @return Fecha de la bitácora. */
    public int getFecha() { return fecha; }

    /** @param fecha Nueva fecha de la bitácora. */
    public void setFecha(int fecha) { this.fecha = fecha; }

    /** @return Datos de la bitácora. */
    public String getDatos() { return datos; }

    /** @param datos Nuevos datos de la bitácora. */
    public void setDatos(String datos) { this.datos = datos; }

    /** @return Registro asociado a la bitácora. */
    public Registro getRegistro() { return registro; }

    /** @param registro Nuevo registro a asociar. */
    public void setRegistro(Registro registro) { this.registro = registro; }

    /**
     * Retorna una representación textual de la bitácora.
     *
     * @return Cadena con código, fecha y datos.
     */
    @Override
    public String toString() {
        return "Bitacora{codigo='" + codigo + "', fecha=" + fecha + ", datos='" + datos + "'}";
    }
}