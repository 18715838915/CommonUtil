package init.luoyu.shell.service.impl;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.StreamGobbler;
import init.luoyu.shell.service.IXShellService;
import init.luoyu.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LuoYu
 * 2021/1/2
 **/
@Service
@Slf4j
public class XShellServiceImpl implements IXShellService {

    private static String ip = "192.168.0.104";
    private static int port  = 22;
    private static String user = "root";
    private static String password = "root";

    private final WebSocketServer webSocketServer;


    /**
     * 用于存放每个客户端的session会话
     */
    private static ConcurrentHashMap<String, Connection> linuxConnectionMap = new ConcurrentHashMap<>();






    public XShellServiceImpl(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }


    @Override
    public void exec(Session session, String cmd) {
     log.info("执行Linux命令 ----> 命令:{}",cmd);
        Connection connection = linuxConnectionMap.get(session.getId());
        if(connection == null){
            connection = getConnection();
            linuxConnectionMap.put(session.getId(), connection);
        }
        try {
            ch.ethz.ssh2.Session linuxSession = connection.openSession();
            linuxSession.execCommand(cmd);
            InputStream stdout = new StreamGobbler(linuxSession.getStdout());
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            while (true){
                String line = reader.readLine();
                if(line == null){
                    break;
                }
                webSocketServer.sendMessage(session, line);
            }
            linuxSession.close();
            reader.close();
            stdout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Connection getConnection() {
        log.info("获取远程连接");
        Connection connection = new Connection(ip, port);
        try {
            connection.connect();
            boolean isLoginSuccess = connection.authenticateWithPassword(user, password);
            if (!isLoginSuccess) {
                log.error("登录失败 ----> 请检查用户名{}的密码是否正确", user);
                return null;
            }
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("连接远程主机：{}失败", ip);
            return null;
        }

    }

    @Override
    public void disConnect(String sessionId) {
        Connection connection = linuxConnectionMap.get(sessionId);
        if(connection != null){
            connection.close();
        }
    }
}
