package checkman.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import checkman.client.R;
import checkman.utils.access.LoginUtil;
import checkman.utils.access.OnLogin;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.wait);
        final Context context = getApplicationContext();
        (new LoginUtil(context)).checkAccess(new OnLogin() {
            public void handle(String param1String) {
                Intent intent = new Intent(context, ParentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
    }
}
