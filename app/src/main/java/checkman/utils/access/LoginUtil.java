package checkman.utils.access;

import android.content.Context;
import android.content.Intent;
import com.android.volley.VolleyError;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import checkman.activity.LoginActivity;
import checkman.entity.UserAccess;
import checkman.utils.connection.AnswerListener;
import checkman.utils.connection.Connector;
import checkman.utils.storage.UserAccessUtil;

public class LoginUtil {
  private final Connector connector = Connector.getConnector();
  
  private final Context context;
  
  private final UserAccessUtil userAccessUtil;
  
  public LoginUtil(Context paramContext) {
    this.userAccessUtil = new UserAccessUtil(paramContext);
    this.context = paramContext;
  }
  
  public void checkAccess(OnLogin paramOnLogin) {
    UserAccess userAccess = this.userAccessUtil.getUserAccessData();
    OnLogin onLogin = new OnLogin() {
        public void handle(String param1String) {
          Intent intent = new Intent(LoginUtil.this.context, LoginActivity.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
          LoginUtil.this.context.startActivity(intent);
        }
      };
    if (userAccess != null) {
      login(userAccess.getUid(), userAccess.getHash(), paramOnLogin, onLogin);
      return;
    } 
    onLogin.handle(null);
  }
  
  public void login(String paramString1, final String hash, final OnLogin onSuccess, final OnLogin onError) {
    HashMap<Object, Object> hashMap = new HashMap<>();
    hashMap.put("uid", paramString1);
    hashMap.put("hash", hash);
    this.connector.sendMsg(this.context, "/a/sign/in", new JSONObject(hashMap), new AnswerListener() {
      public void onError(VolleyError param1VolleyError) {
        param1VolleyError.printStackTrace();
      }
      public void onSuccess(JSONObject param1JSONObject) {
        System.out.println(param1JSONObject);
        try {
          String str;
          if (param1JSONObject.getString("status").equals("success")) {
            str = param1JSONObject.getString("uid");
            LoginUtil.this.userAccessUtil.saveUserData(str, hash);
            onSuccess.handle(null);
          } else {
            str = param1JSONObject.getString("msd");
            onError.handle(str);
          }
        } catch (JSONException jSONException) {
          jSONException.printStackTrace();
        }
      }
    });
  }
}


/* Location:              /home/szpt-user045/szpt/base.jar!/ua/s/vasilina/checkman/utils/access/LoginUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */