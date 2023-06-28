import Controlador.*;
import JSON.JsonUtiles;
import Modelo.Usuario;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ControladorDeUsuarios sistema = new ControladorDeUsuarios();
        sistema.initFromJSON(JsonUtiles.leer("datos"));
        System.out.println(sistema.listar());

        System.out.println("cantidad de guests: " + sistema.contarPorRol("guest"));
        System.out.println("esta Adriana Hines? " + sistema.buscarPorNombre("Adriana Hines"));

        sistema.grabarJSON("nuevosDatos");
        sistema.grabarArchivoBinario(sistema.filtrarPorRol("guest"), "guests");
        ArrayList<Usuario> usuarios = sistema.leerArchivoBinario("guests");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario + "\n");
        }
    }
}
