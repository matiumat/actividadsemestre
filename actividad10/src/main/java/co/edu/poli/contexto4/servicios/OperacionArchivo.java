package co.edu.poli.contexto4.servicios;

import java.io.IOException;

import co.edu.poli.contexto4.modelo.Protocolo;

/**
 * Interfaz que define las operaciones de persistencia en archivo plano
 * para objetos de tipo {@link Protocolo}.
 * <p>
 * Los métodos declaran {@code throws IOException} o {@code throws Exception}
 * propagando los errores al método que los invoca para que los maneje con {@code try-catch}.
 * </p>
 *
 * @author Equipo Contexto 4
 * @version 2.0
 * @see ImplementacionOperacionCRUD
 */
public interface OperacionArchivo {

    /**
     * Serializa el arreglo de protocolos escribiendo cada objeto no-null
     * en una línea del archivo {@code protocolos.txt}.
     * <p>Formato: {@code TIPO;numero_id;registro;instrucciones;limites}</p>
     *
     * @return Mensaje OK con la cantidad de protocolos guardados.
     * @throws IOException Si ocurre un error al escribir el archivo.
     * @throws Exception   Si el arreglo está completamente vacío.
     */
    String serializar() throws IOException, Exception;

    /**
     * Deserializa el archivo {@code protocolos.txt} y reconstruye un arreglo
     * de objetos {@link Protocolo}.
     *
     * @return Arreglo de {@link Protocolo} reconstruido desde el archivo.
     * @throws IOException Si el archivo no existe o no puede leerse.
     */
    Protocolo[] deserializar() throws IOException;
}