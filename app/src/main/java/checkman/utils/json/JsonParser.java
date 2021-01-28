package checkman.utils.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
    private final JSONParser parser = new JSONParser();

    public JsonObject parse(String paramString) {
        try {
            return new JsonObject((JSONObject)this.parser.parse(paramString));
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            return null;
        }
    }
}
