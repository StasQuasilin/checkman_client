package checkman.utils.connection;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class SocketListener extends WebSocketListener {
    public static final String TAG = "Socket Listener";

    final SocketConnector connector;

    private final ConnectionStatus status;

    public SocketListener(ConnectionStatus paramConnectionStatus) {
        this.status = paramConnectionStatus;
        this.connector = new SocketConnector();
    }

    public void onClosed(@NotNull WebSocket paramWebSocket, int paramInt, @NotNull String paramString) {
        super.onClosed(paramWebSocket, paramInt, paramString);
    }

    public void onFailure(@NotNull WebSocket paramWebSocket, @NotNull Throwable paramThrowable, Response paramResponse) {
        super.onFailure(paramWebSocket, paramThrowable, paramResponse);
        if (paramThrowable instanceof java.net.SocketException) {
            Log.w("Socket Listener", this.status.toString() + " connection error");
            this.connector.connect(this.status);
            return;
        }
        paramThrowable.printStackTrace();
    }

    public void onMessage(@NotNull WebSocket paramWebSocket, @NotNull String paramString) {
        super.onMessage(paramWebSocket, paramString);
        System.out.println(paramString);
    }

    public void onOpen(@NotNull WebSocket paramWebSocket, @NotNull Response paramResponse) {
        super.onOpen(paramWebSocket, paramResponse);
        Log.i("Socket Listener", this.status.toString() + " connection open");
    }
}
