package init.luoyu;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author LuoYu
 * 2021/1/1
 **/
@Slf4j
public class SSHApp {

    private static String ip = "192.168.0.104";
    private static int port  = 22;
    private static String user = "root";
    private static String password = "root";


    public static void main(String[] args) {

        try {
            Connection connection = new Connection(ip, port);
            connection.connect();
            boolean isLoginSuccess = connection.authenticateWithPassword(user, password);
            if(!isLoginSuccess){
                log.error("登录失败 ----> 请检查用户名{}的密码是否正确",user);
                return;
            }
            Session session = connection.openSession();
            session.execCommand("ifconfig");
            InputStream stdout = new StreamGobbler(session.getStdout());
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            while (true){
                String line = reader.readLine();
                if(line == null){
                    break;
                }
                System.out.println(line);
            }
            session.close();
            connection.close();
        }catch (IOException e){
            log.error("连接远程主机:{}失败",ip);
        }



    }








}
