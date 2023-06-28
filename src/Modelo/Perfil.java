package Modelo;

import Excepciones.AnioInvalidoException;
import JSON.IFromJSON;
import org.json.JSONException;
import org.json.JSONObject;

public class Perfil implements IFromJSON {
    private final String dob;
    private String name;
    private String about;
    private String address;
    private String company;
    private Ubicacion location;

    public Perfil(String dob) throws AnioInvalidoException {

        if (!validarAnio(dob)) {
            throw new AnioInvalidoException("anio de nacimiento menor a 1990");
        }
        this.dob = dob;
        this.name = "";
        this.about = "";
        this.address = "";
        this.company = "";
        this.location = new Ubicacion();
    }

    public String getDob() {
        return dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Ubicacion getLocation() {
        return location;
    }

    public void setLocation(Ubicacion location) {
        this.location = location;
    }

    private boolean validarAnio(String dob) {
        boolean respuesta = true;
        String [] superdob  = dob.split("-");
        int anio = Integer.parseInt(superdob[0]);
        if (anio < 1990) {
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "dob='" + dob + '\'' +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", location=" + location +
                '}';
    }

    @Override
    public void fromJSON(JSONObject jsonObject) throws JSONException {
        setAbout(jsonObject.getString("about"));
        setAddress(jsonObject.getString("address"));
        setCompany(jsonObject.getString("company"));
        setName(jsonObject.getString("name"));
        location.fromJSON(jsonObject.getJSONObject("location"));
    }
}
