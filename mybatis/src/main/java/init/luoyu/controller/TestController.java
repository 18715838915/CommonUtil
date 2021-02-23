package init.luoyu.controller;

import init.luoyu.dao.AccountMapper;
import init.luoyu.pojo.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LuoYu
 * date 2020/12/29
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private AccountMapper accountMapper;


    @GetMapping(value = "/get")
    public Account get(@RequestParam("id") Long id){
        return check(id);
    }


    private Account check(long id){

        if(id > 0){
            return new Account();
        }

        return null;
    }




}
