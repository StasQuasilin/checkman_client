package checkman.utils.json;

import org.json.simple.JSONObject;

public class JsonObject {
    private final JSONObject json;

    public JsonObject(JSONObject paramJSONObject) {
        this.json = paramJSONObject;
    }

    public String getString(String paramString) {
        if (this.json.containsKey(paramString)) {
            Object object = this.json.get(paramString);
            if (object != null)
                return String.valueOf(object);
        }
        return null;
    }
}
