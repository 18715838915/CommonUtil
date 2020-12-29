package init.luoyu.controller;


import init.luoyu.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author LuoYu
 * date 2020/12/29
 */
@RestController
public class TestController {


    @PostMapping(value = "/test")
    public String test(@RequestBody @Validated User user){
        return "Success";
    }






}
