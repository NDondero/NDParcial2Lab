package JSON;

import org.json.JSONException;
import org.json.JSONObject;

public interface IFromJSON {
    public void fromJSON(JSONObject jsonObject) throws JSONException;
}
