package work.huangxin.display.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/websocket/sendMessage")
@Component
public class WebSocketServer {


    //每个客户端都会有相应的session,服务端可以发送相关消息
    private Session session;

    //J.U.C包下线程安全的类，主要用来存放每个客户端对应的webSocket连接
    private static CopyOnWriteArraySet<WebSocketServer> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

    /**
     * 打开链接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        copyOnWriteArraySet.add(this);
        System.out.println("websocket有新的连接, 总数:" + copyOnWriteArraySet.size());

    }

    /**
     * 关闭链接
     */
    @OnClose
    public void onClose() {
        copyOnWriteArraySet.remove(this);
        System.out.println("websocket连接断开, 总数:" + copyOnWriteArraySet.size());
    }

    /**
     * 接收
     *
     * @param message
     * @throws Exception
     */
    @OnMessage
    public void onMessage(String message) throws Exception {
        System.out.println("发来的消息:" + message);
        if ("1538933906".equals(message)) {
            return;
        }
        sendMessage(message);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误：" + error.getMessage() + "; sessionId:" + session.getId());
        error.printStackTrace();
    }

    /**
     * 群发
     *
     * @param message
     */
    public static void sendMessage(String message) {
        //遍历客户端
        for (WebSocketServer webSocket : copyOnWriteArraySet) {
            System.out.println("websocket广播消息：" + message);
            try {
                //服务器主动推送
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
