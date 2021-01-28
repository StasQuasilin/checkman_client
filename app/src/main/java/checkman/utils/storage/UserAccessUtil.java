package checkman.utils.storage;

import android.content.Context;
import org.json.simple.JSONObject;

import checkman.constants.Keys;
import checkman.entity.UserAccess;
import checkman.utils.StorageUtil;
import checkman.utils.json.JsonObject;
import checkman.utils.json.JsonParser;

public class UserAccessUtil {

  private static final String FILE_NAME = ".UserAcc";
  
  private final JsonParser parser = new JsonParser();
  
  private final StorageUtil storageUtil;
  
  public UserAccessUtil(Context paramContext) {
    this.storageUtil = new StorageUtil(paramContext);
  }
  
  public UserAccess getUserAccessData() {
    String data = this.storageUtil.readFile(FILE_NAME);
    UserAccess access = null;
    if (data != null) {
      JsonObject jsonObject = this.parser.parse(data);
      if (jsonObject != null) {
        access = new UserAccess();
        access.setUid(jsonObject.getString(Keys.UID));
        access.setHash(jsonObject.getString(Keys.HASH));
      } 
    } 
    return access;
  }
  
  public void saveUserData(String paramString1, String paramString2) {
    JSONObject jSONObject = new JSONObject();
    jSONObject.put(Keys.UID, paramString1);
    jSONObject.put(Keys.HASH, paramString2);
    this.storageUtil.saveData(FILE_NAME, jSONObject.toJSONString());
  }
}