package checkman.utils;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionChecker {
  public static boolean check(Context context, AppCompatActivity activity, String permission) {
    if (ContextCompat.checkSelfPermission(context, permission) != 0) {
      ActivityCompat.requestPermissions(activity, new String[] { permission }, 0);
      return false;
    } 
    return true;
  }
}