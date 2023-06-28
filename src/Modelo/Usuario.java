package Modelo;

import Contenedor.ContenedorSet;
import Excepciones.AnioInvalidoException;
import Excepciones.RolRepetidoException;
import JSON.IFromJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class Usuario implements IFromJSON {
    private String id;
    private String email;
    private ContenedorSet<String> roles;
    private String apiKey;
    private Perfil profile;
    private String username;
    private String createdAt;
    private String updatedAt;

    public Usuario() {
        this.id = "";
        this.email = "";
        this.apiKey = "";
        this.roles = new ContenedorSet<>();
        this.profile = null;
        this.username = "";
        this.createdAt = "";
        this.updatedAt = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Perfil getProfile() {
        return profile;
    }

    public void setProfile(Perfil profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public boolean agregarRol(String rol) throws RolRepetidoException {
        if (roles.buscar(rol)) {
            throw new RolRepetidoException("Rol repetido, solo se puede tener uno de cada");
        }
        return roles.agregar(rol);
    }

    public boolean buscarRol(String rol) {
        return roles.buscar(rol);
    }

    @Override
    public boolean equals(Object obj) {
        boolean rta = false;
        if(obj instanceof Usuario usuario) {
            if(this.username.equals(usuario.getUsername())) {
                rta = true;
            }
        }
        return rta;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", apiKey='" + apiKey + '\'' +
                ", profile=" + profile +
                ", username='" + username + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    @Override
    public void fromJSON(JSONObject jsonObject) throws JSONException {
        try {
            Perfil perfil = new Perfil(jsonObject.getJSONObject("profile").getString("dob"));
            perfil.fromJSON(jsonObject.getJSONObject("profile"));
            setProfile(perfil);
            setId(jsonObject.getString("id"));
            setEmail(jsonObject.getString("email"));
            JSONArray roles = jsonObject.getJSONArray("roles");

            for (int i = 0; i < roles.length(); i++) {
                try {
                    agregarRol(roles.getString(i));
                } catch (RolRepetidoException e) {
                    // ok, ese no, el que sigue...
                    //System.out.println(e.getMessage());
                }
            }
            setApiKey(jsonObject.getString("apiKey"));
            setUsername(jsonObject.getString("username"));
            setCreatedAt(jsonObject.getString("createdAt"));
            setUpdatedAt(jsonObject.getString("updatedAt"));
        } catch (AnioInvalidoException e) {
            throw new JSONException(e);
        }
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONArray roles = new JSONArray();
        Iterator<String> it = this.roles.elementos();
        while (it.hasNext()) {
            roles.put(it.next());
        }
        return new JSONObject().put("id", id).
                put("email", email).
                put("roles", roles).
                put("apiKey", apiKey).
                put("profile", profile.toJSON()).
                put("username", username).
                put("createdAt", createdAt).put("updatedAt", updatedAt);
    }
}
