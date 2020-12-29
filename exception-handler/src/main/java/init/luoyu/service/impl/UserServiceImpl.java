package init.luoyu.service.impl;

import init.luoyu.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author LuoYu
 * date 2020/12/29
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void test() {
        throw new RuntimeException("Service层测试异常");
    }
}
