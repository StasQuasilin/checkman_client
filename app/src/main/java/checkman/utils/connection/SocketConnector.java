package checkman.utils.connection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public final class SocketConnector {
    private static final SocketConnector instance = new SocketConnector();

    private WebSocket socket;

    public static SocketConnector getInstance() {
        return instance;
    }

    public void connect() {
        connect(ConnectionStatus.external);
    }

    public void connect(String paramString, ConnectionStatus paramConnectionStatus) {
        OkHttpClient okHttpClient = new OkHttpClient();
        this.socket = okHttpClient.newWebSocket((new Request.Builder()).url(paramString).build(), new SocketListener(paramConnectionStatus));
        okHttpClient.dispatcher().executorService().shutdown();
    }

    public void connect(ConnectionStatus paramConnectionStatus) {
        if (paramConnectionStatus == ConnectionStatus.external) {
            connect("ws://91.222.16.30:3322/checkman/api/subscriber", ConnectionStatus.internal);
            return;
        }
        if (paramConnectionStatus == ConnectionStatus.internal)
            connect("ws://10.10.10.201:3322/checkman/api/subscriber", ConnectionStatus.external);
    }

    public void send(String paramString) {
        this.socket.send(paramString);
    }
}
