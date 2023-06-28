package Controlador.Generico;

import java.util.*;

public class ControladorDeMapas<K,V> {
    private HashMap<K,V> coleccion;

    public ControladorDeMapas() {
        this.coleccion = new HashMap<>();
    }

    public boolean agregar(K clave, V valor) {
        boolean respuesta = false;
        if(!coleccion.containsKey(clave)) {
            coleccion.put(clave, valor);
            respuesta = true;
        }
        return respuesta;
    }

    public boolean eliminar(K clave) {
        boolean respuesta = false;
        if(coleccion.containsKey(clave)) {
            coleccion.remove(clave);
            respuesta = true;
        }
        return respuesta;
    }

    public boolean buscar(K clave) {
        return coleccion.containsKey(clave);
    }

    public V get(K clave) {
        return coleccion.get(clave);
    }

    public int contar() {
        return coleccion.size();
    }

    public Collection<V> valores() {
        return coleccion.values();
    }

    public Set<K> claves() {
        return coleccion.keySet();
    }

    public Iterator<Map.Entry<K,V>> entradas() {
        return coleccion.entrySet().iterator();
    }
}
