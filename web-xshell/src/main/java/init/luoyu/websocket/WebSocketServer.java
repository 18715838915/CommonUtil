package init.luoyu.websocket;

import init.luoyu.common.utils.SpringContextUtil;
import init.luoyu.shell.service.IXShellService;
import init.luoyu.shell.service.impl.XShellServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author LuoYu
 * 2021/1/2
 **/
@ServerEndpoint("/webSocket/{sid}")
@Component
public class WebSocketServer {

    /**
     * 用于统计在线人数
     */
    private static AtomicInteger onlineNum = new AtomicInteger();

    /**
     * 用于存放每个客户端的session会话
     */
    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();





    /**
     * <p>发送消息</p>
     * @param session  会话实例
     * @param msg      发送的消息
     * @throws IOException
     */
    public void sendMessage(Session session,String msg) throws IOException {
        if(session != null){
            synchronized (session){
                session.getBasicRemote().sendText(msg);
            }
        }
    }


    /**
     * <p>给指定用户发送消息</p>
     * @param userName  用户名称
     * @param msg       消息
     */
    public void sendToUser(String userName,String msg){
        Session session = sessionMap.get(userName);
        try {
            sendMessage(session, msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * <p>当用户连接时调用该方法</p>
     * @param session           会话
     * @param userName           用户名
     */
    @OnOpen
    public void onConnection(Session session,@PathParam("sid") String userName){
        sessionMap.put(userName, session);
        addOnlineNum();
    }

    @OnMessage
    public void onMessage(Session session,String msg){
        XShellServiceImpl shellService = SpringContextUtil.getBean(XShellServiceImpl.class);
        shellService.exec(session, msg);
    }



    /**
     * <p>用户断开连接时调用</p>
     * @param userName  用户名称
     */
    @OnClose
    public void onClose(@PathParam("sid") String userName){
        sessionMap.remove(userName);
        subOnlineNum();
    }


    /**
     * <p>发生错误时调用</p>
     * @param session       会话
     * @param throwable     异常实例
     */
    @OnError
    public void onError(Session session,Throwable throwable){
        throwable.printStackTrace();
    }


    public static void addOnlineNum(){
        onlineNum.incrementAndGet();
    }

    public static void subOnlineNum(){
        onlineNum.decrementAndGet();
    }














































}
