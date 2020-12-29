package init.luoyu.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LuoYu
 * date 2020/12/29
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler
    @ResponseBody
    public String errorHandler(Exception e){
        log.error("出现错误 ----> E:{}",e.getMessage());
        return "Error";
    }







}
