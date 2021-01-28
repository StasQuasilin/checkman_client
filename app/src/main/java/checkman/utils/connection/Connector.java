package checkman.utils.connection;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class Connector {

  private static Connector connector = new Connector();
  
  private RequestQueue requestQueue;
  
  public static Connector getConnector() {
    return connector;
  }
  
  private void sendMsg(final Context context, String paramString1, final String url, final JSONObject data, final AnswerListener answerListener, final ConnectionStatus status) {
    addRequest(context, (Request<?>)new JsonObjectRequest(1, paramString1 + url, data, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject param1JSONObject) {
              System.out.println(data);
              answerListener.onSuccess(param1JSONObject);
            }
          },  new Response.ErrorListener() {
            public void onErrorResponse(VolleyError param1VolleyError) {
              if (param1VolleyError instanceof com.android.volley.NoConnectionError) {
                if (status == ConnectionStatus.external) {
                  Connector.this.sendMsg(context, url, data, answerListener, ConnectionStatus.internal);
                  return;
                } 
                answerListener.onError(param1VolleyError);
              } 
            }
          }));
  }
  
  public <T> void addRequest(Context paramContext, Request<T> paramRequest) {
    getRequest(paramContext).add(paramRequest);
  }
  
  public RequestQueue getRequest(Context paramContext) {
    if (this.requestQueue == null)
      this.requestQueue = Volley.newRequestQueue(paramContext); 
    return this.requestQueue;
  }
  
  public void sendMsg(Context paramContext, String paramString, JSONObject paramJSONObject, AnswerListener paramAnswerListener) {
    sendMsg(paramContext, paramString, paramJSONObject, paramAnswerListener, ConnectionStatus.external);
  }
  
  public void sendMsg(Context context, String api, JSONObject paramJSONObject, AnswerListener paramAnswerListener, ConnectionStatus paramConnectionStatus) {
    String str;
    if (paramConnectionStatus == ConnectionStatus.external) {
      str = "http://91.222.16.30:3322/checkman";
    } else {
      str = "http://10.10.10.201:3322/checkman";
    } 
    sendMsg(context, str, api, paramJSONObject, paramAnswerListener, paramConnectionStatus);
  }
}