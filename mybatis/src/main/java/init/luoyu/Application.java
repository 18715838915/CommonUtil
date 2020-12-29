package init.luoyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LuoYu
 * date 2020/12/29
 */
@SpringBootApplication
@MapperScan("init.luoyu.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
