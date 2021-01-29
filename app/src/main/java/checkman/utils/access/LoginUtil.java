package checkman.utils.access;

import android.content.Context;
import android.content.Intent;
import com.android.volley.VolleyError;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import checkman.activity.LoginActivity;
import checkman.constants.Keys;
import checkman.constants.Links;
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
    hashMap.put(Keys.UID, paramString1);
    hashMap.put(Keys.HASH, hash);
    this.connector.sendMsg(this.context, Links.LOGIN, new JSONObject(hashMap), new AnswerListener() {
      public void onError(VolleyError param1VolleyError) {
        param1VolleyError.printStackTrace();
      }
      public void onSuccess(JSONObject jsonObject) {
        System.out.println(jsonObject);
        try {
          if (jsonObject.getString(Keys.STATUS).equals(Keys.SUCCESS)) {
            userAccessUtil.saveUserData(jsonObject.getString(Keys.UID), hash);
            onSuccess.handle(null);
          } else {
            onError.handle(jsonObject.getString("msd"));
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