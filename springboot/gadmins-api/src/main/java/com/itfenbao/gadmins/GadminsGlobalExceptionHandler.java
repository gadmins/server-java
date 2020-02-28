package com.itfenbao.gadmins;

import com.itfenbao.gadmins.core.exception.NotLoginException;
import com.itfenbao.gadmins.core.exception.TokenFailException;
import com.itfenbao.gadmins.core.web.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GadminsGlobalExceptionHandler {

    /**
     * 异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class) //该注解声明异常处理方法
    @ResponseBody
    public JsonResult exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("Gadmins Exception", e);
        if (e instanceof NotLoginException) {
            return JsonResult.noLogin();
        } else if (e instanceof TokenFailException) {
            return JsonResult.failToken();
        } else if (e instanceof BindException) {
            return JsonResult.paramsErrorMessage("参数绑定异常");
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return JsonResult.http404Message( ((HttpRequestMethodNotSupportedException) e).getMethod());
        } else if (e instanceof MethodArgumentNotValidException) {
            String msg = ((MethodArgumentNotValidException) e)
                    .getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            log.info("Global MethodArgumentNotValidException：{}", msg);
            return JsonResult.paramsErrorMessage(msg);
        } else {
            return JsonResult.fail();
        }
    }
}
