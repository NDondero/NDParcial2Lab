package Contenedor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

public class ContenedorSet<T> implements Serializable {
    private HashSet<T> coleccion;

    public ContenedorSet() {
        this.coleccion = new HashSet<>();
    }

    public boolean agregar(T elemento) {
        return coleccion.add(elemento);
    }

    public boolean eliminar(T elemento) {
        return coleccion.remove(elemento);
    }

    public boolean buscar(T elemento) {
        return coleccion.contains(elemento);
    }

    public Iterator<T> elementos() {
        return coleccion.iterator();
    }

    public int cantidad() {
        return coleccion.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<T> it = coleccion.iterator();
        while (it.hasNext()) {
            stringBuilder.append(" - " + it.next().toString() + " - ");
        }
        return stringBuilder.toString();
    }
}
