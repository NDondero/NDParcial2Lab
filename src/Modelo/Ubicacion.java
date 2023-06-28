package Modelo;

import JSON.IFromJSON;
import org.json.JSONException;
import org.json.JSONObject;

public class Ubicacion implements IFromJSON {
    private double latitud;
    private double longitud;

    public Ubicacion() {
        this.latitud = 0f;
        this.longitud = 0f;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }

    @Override
    public void fromJSON(JSONObject jsonObject) throws JSONException {
        setLatitud(jsonObject.getDouble("lat"));
        setLongitud(jsonObject.getDouble("long"));
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        return new JSONObject().put("lat", latitud).put("long", longitud);
    }
}
