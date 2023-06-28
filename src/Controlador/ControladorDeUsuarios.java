package Controlador;

import Controlador.Generico.ControladorDeMapas;
import Excepciones.UsernameEnUsoException;
import JSON.JsonUtiles;
import Modelo.Usuario;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Collection;

public class ControladorDeUsuarios extends ControladorDeMapas<String, Usuario> {
    public int contarPorRol(String rol) {
        int contador = 0;
        Collection<Usuario> usuarios = super.valores();
        for (Usuario usuario : usuarios) {
            if (usuario.buscarRol(rol)) {
                contador++;
            }
        }
        return contador;
    }

    public boolean buscarPorNombre(String nombre) {
        boolean respuesta = false;
        Collection<Usuario> usuarios = super.valores();
        for (Usuario usuario : usuarios) {
            if (usuario.getProfile().getName().equals(nombre)) {
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }

    public boolean agregar(Usuario usuario) throws UsernameEnUsoException {
        if (buscar(usuario.getUsername())) {
            throw new UsernameEnUsoException("nombre de usuario en uso");
        }
        super.agregar(usuario.getUsername(), usuario);
        return true;
    }

    public void initFromJSON(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    Usuario nuevo = new Usuario();
                    nuevo.fromJSON(jsonArray.getJSONObject(i));
                    agregar(nuevo);
                } catch (UsernameEnUsoException | JSONException e) {
                    // ok, pase el que siga
                    System.out.println(e.getMessage());
                }
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    public void grabarJSON(String archivo) {
        JSONArray jsonArray = new JSONArray();
        Collection<Usuario> usuarios = super.valores();
        for (Usuario usuario : usuarios) {
            try {
                jsonArray.put(usuario.toJSON());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JsonUtiles.grabar(jsonArray, archivo);
    }

    public String listar() {
        StringBuilder stringBuilder = new StringBuilder();
        Collection<Usuario> usuarios = super.valores();
        for (Usuario usuario : usuarios) {
            stringBuilder.append(usuario.toString() + "\n");
        }
        return stringBuilder.toString();
    }

    // TODO agregar funcionalidad para grabar en un archivo de Usuarios todos los usuarios que cumplan con un requisito, por ejemplo un determinador rol
}
