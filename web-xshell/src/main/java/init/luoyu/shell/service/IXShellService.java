package init.luoyu.shell.service;

import ch.ethz.ssh2.Connection;

import javax.websocket.Session;

/**
 * @author LuoYu
 * 2021/1/2
 **/
public interface IXShellService {


    void exec(Session session,String cmd);


    Connection getConnection();


    void disConnect(String sessionId);


}
