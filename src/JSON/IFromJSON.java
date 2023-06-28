package JSON;

import org.json.JSONException;
import org.json.JSONObject;

public interface IFromJSON {
    void fromJSON(JSONObject jsonObject) throws JSONException;
    JSONObject toJSON() throws JSONException;
}
