package com.java.websocket;

import okhttp3.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.ByteString;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class LittleWebSocket {
    private static MockWebServer mockWebServer;
    private static WebSocket webSocket;
    private static Timer timer;
    private static int msgCount;

    public static void main(String[] args) {
        initMockWebServer();
        String wsUrl = "ws://" + mockWebServer.getHostName() + ":" + mockWebServer.getPort() + "/";
        initWebSocketClient(wsUrl);
    }

    private static void initWebSocketClient(String wsUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .pingInterval(1, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(wsUrl)
                .build();

        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket ws, Response response) {
                super.onOpen(ws, response);
                webSocket = ws;
                System.out.println("client onOpen");
                System.out.println("client request header " + response.request().headers());
                System.out.println("client response header " + response.headers());
                System.out.println("client response " + response);
                // 开始发送消息
                sendMessage();
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                System.out.println("client onMessage, receive message=" + text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
                System.out.println("client onClosing, code=" + code + ", reason=" + reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                System.out.println("client onClosed, code=" + code + ", reason=" + reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
                System.out.println("client onFailure throwable " + t + ", response " + response);
                t.printStackTrace();
            }
        });
    }

    /**
     * 每秒发送一条消息
     */
    private static void sendMessage() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (webSocket == null)
                    return;
                msgCount++;
                System.out.println("sendMessage---msgCount=" + msgCount);
                webSocket.send("消息内容: 我是第" + msgCount + "条消息, 发送时间=" + LocalDateTime.now());
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    private static void initMockWebServer() {
        mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().withWebSocketUpgrade(new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                System.out.println("server onOpen");
                System.out.println("server request header " + response.request().headers());
                System.out.println("server response header " + response.headers());
                System.out.println("server response " + response);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                System.out.println("server onMessage, receive message=" + text);
                if (msgCount == 5) {
                    timer.cancel();
                    webSocket.close(1000, "close by server!");
                    return;
                }
                webSocket.send("response---" + text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
                System.out.println("server onClosing, code=" + code + ", reason=" + reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                System.out.println("server onClosed, code=" + code + ", reason=" + reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
                System.out.println("server onFailure throwable " + t + ", response " + response);
            }
        }));
    }
}
