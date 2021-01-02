package init.luoyu.controller.view;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;

/**
 * @author LuoYu
 * 2021/1/2
 **/
@Controller
public class IndexController {



    @RequestMapping(value = {"/","","/index"})
    public ModelAndView index(HttpSession session){
        Object user = session.getAttribute("user");
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        if(user == null){
            view.setViewName("login");
            return view;
        }
        view.addObject(user);
        return view;
    }

    @RequestMapping(value = "/websocket")
    public ModelAndView websocketView(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/page/webSocket");
        return view;
    }




}
