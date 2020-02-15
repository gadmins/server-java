package com.itfenbao.gadmins;

import com.itfenbao.gadmins.common.exception.NotLoginException;
import com.itfenbao.gadmins.common.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@ControllerAdvice
public class GadminsGlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GadminsGlobalExceptionHandler.class);

    /**
     * 异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class) //该注解声明异常处理方法
    @ResponseBody
    public JsonResult exceptionHandler(HttpServletRequest request, Exception e) {
        logger.error("Gadmins Exception", e);
        if (e instanceof NotLoginException) {
            return JsonResult.noLogin();
        } else if (e instanceof MethodArgumentNotValidException) {
            String msg = ((MethodArgumentNotValidException) e)
                    .getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            logger.info("Global MethodArgumentNotValidException：{}", msg);
            return JsonResult.paramsErrorMessage(msg);
        } else {
            return JsonResult.fail();
        }
    }
}
