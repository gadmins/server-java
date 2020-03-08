package com.itfenbao.gadmins;

import com.itfenbao.gadmins.core.exception.NotLoginException;
import com.itfenbao.gadmins.core.exception.TokenFailException;
import com.itfenbao.gadmins.core.web.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GadminsGlobalExceptionHandler implements ResponseBodyAdvice {

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
            return JsonResult.http404Message(((HttpRequestMethodNotSupportedException) e).getMethod());
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
            return JsonResult.failMessage(e.getMessage());
        }
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof JsonResult) {
            Integer code = ((JsonResult) o).getCode();
            if (code != null && !(code.equals(HttpStatus.OK.value()))) {
                serverHttpResponse.setStatusCode(HttpStatus.valueOf(code));
            }
        }
        return o;
    }
}
