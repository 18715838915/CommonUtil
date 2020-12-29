package init.luoyu.controller;

import init.luoyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LuoYu
 * date 2020/12/29
 */
@Controller
public class TestController {


    @Autowired
    private UserService userService;

    @GetMapping(value = "/get")
    @ResponseBody
    public String get(){
       userService.test();
       return "Success";
    }


}
