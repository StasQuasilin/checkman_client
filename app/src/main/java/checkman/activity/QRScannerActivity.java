package checkman.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import org.jetbrains.annotations.NotNull;

import checkman.client.R;

public class QRScannerActivity extends AppCompatActivity {
    private CodeScanner scanner;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.qr_scanner);
        final Context context = getApplicationContext();
        CodeScannerView codeScannerView = (CodeScannerView)findViewById(R.id.scanner_view);
        final Handler handler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message param1Message) {
                String str = param1Message.getData().getString("result");
                Intent intent = new Intent(context, LoginActivity.class);
                intent.putExtra("uid", str);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        };
        CodeScanner codeScanner = new CodeScanner((Context)this, codeScannerView);
        this.scanner = codeScanner;
        codeScanner.setDecodeCallback(new DecodeCallback() {
            public void onDecoded(@NotNull Result param1Result) {
                String str = param1Result.getText();
                Bundle bundle = new Bundle();
                bundle.putString("result", str);
                Message message = new Message();
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
        codeScannerView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                QRScannerActivity.this.scanner.startPreview();
            }
        });
    }

    protected void onPause() {
        this.scanner.releaseResources();
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.scanner.startPreview();
    }
}
