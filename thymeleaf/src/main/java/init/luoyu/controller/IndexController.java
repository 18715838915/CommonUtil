package init.luoyu.controller;

import init.luoyu.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @author LuoYu
 * 2021/1/1
 **/
@Controller
public class IndexController {

    @GetMapping(value = {"","/","/index"})
    public ModelAndView index(HttpServletRequest request){
         ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setName("张三");
        modelAndView.setViewName("index");
        modelAndView.addObject(user);
        return modelAndView;
    }



}
