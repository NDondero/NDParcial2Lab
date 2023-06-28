import Controlador.*;
import JSON.JsonUtiles;

public class Main {
    public static void main(String[] args) {
        ControladorDeUsuarios sistema = new ControladorDeUsuarios();
        sistema.initFromJSON(JsonUtiles.leer("datos"));
        System.out.println(sistema.listar());

        System.out.println("cantidad de guests: " + sistema.contarPorRol("guest"));
        System.out.println("esta Adriana Hines? " + sistema.buscarPorNombre("Adriana Hines"));

        sistema.grabarJSON("nuevosDatos");
    }
}
