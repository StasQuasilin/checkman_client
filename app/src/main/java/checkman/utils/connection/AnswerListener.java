package checkman.utils.connection;

import com.android.volley.VolleyError;
import org.json.JSONObject;

public interface AnswerListener {
  void onError(VolleyError paramVolleyError);
  
  void onSuccess(JSONObject paramJSONObject);
}