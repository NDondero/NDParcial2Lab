package Controlador.Generico;

import java.io.*;
import java.util.ArrayList;

public class ControladorArchivoObjetos<T extends Serializable> {
    public void grabar(ArrayList<T> elementos, String archivo) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(archivo + ".bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (T elemento : elementos) {
                objectOutputStream.writeObject(elemento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<T> leer(String archivo) {
        ArrayList<T> elementos = new ArrayList<>();
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(archivo + ".bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                elementos.add((T)objectInputStream.readObject());
            }
        } catch (EOFException e) {
            // all good
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return elementos;
    }
}
