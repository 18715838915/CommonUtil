package init.luoyu.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author LuoYu
 * 2021/1/2
 **/
@Component
@Slf4j
public class SpringContextUtil implements ApplicationContextAware {


    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("本系统已获取到Spring上下文对象.....");
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * <p>通过类型获取Bean</p>
     * @param tClass  类型
     * @param <T>     类型
     * @return       bean
     */
    public static <T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }


    /**
     * <p>通过类型和名称获取Bean</p>
     * @param tClass  类型
     * @param name    名称
     * @param <T>     类型
     * @return        bean
     */
    public static <T> T getBean(Class<T> tClass,String name){
        return applicationContext.getBean(name, tClass);
    }





}
