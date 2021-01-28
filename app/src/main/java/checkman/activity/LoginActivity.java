package checkman.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import checkman.client.R;
import checkman.constants.Keys;
import checkman.utils.MD5;
import checkman.utils.PermissionChecker;
import checkman.utils.access.LoginUtil;
import checkman.utils.access.OnLogin;

public class LoginActivity extends AppCompatActivity {
    private TextView errMsg;

    private LoginUtil loginUtil;

    private final MD5 md5 = new MD5();

    private EditText passwordField;

    private EditText uidField;

    private void checkCameraPermissions(Context context) {
        if (PermissionChecker.check(context, this, Manifest.permission.CAMERA)) {
            runQR();
        } else {

        }
    }

    private void login() {
        this.errMsg.setText(Keys.EMPTY);
        String str1 = this.passwordField.getEditableText().toString();
        str1 = this.md5.checkSum(str1);
        String str2 = this.uidField.getEditableText().toString();
        this.loginUtil.login(str2, str1, new OnLogin() {
            public void handle(String param1String) {
                Context context = LoginActivity.this.getApplicationContext();
                Intent intent = new Intent(context, ParentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        },new OnLogin() {
            public void handle(String param1String) {
                LoginActivity.this.errMsg.setText(param1String);
            }
        });
    }

    private void runQR() {
        System.out.println("Run QR activity");
        Context context = getApplicationContext();
        Intent intent = new Intent(context, QRScannerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.login_activity);
        final Context context = getApplicationContext();
        this.loginUtil = new LoginUtil(context);
        String uid = getIntent().getStringExtra("uid");
        uidField = (EditText)findViewById(R.id.uid);

        if (uid != null) {
            uidField.setText(uid);
        }

        ((ImageView)findViewById(R.id.qrButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                System.out.println("Click on QR");
                checkCameraPermissions(context);
            }
        });
        this.passwordField = (EditText)findViewById(R.id.password);

        this.errMsg = (TextView)findViewById(R.id.err);
        ((Button)findViewById(R.id.loginButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                LoginActivity.this.login();
            }
        });
    }

    public void onRequestPermissionsResult(int paramInt, @NotNull String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == 0)
            runQR();
    }
}