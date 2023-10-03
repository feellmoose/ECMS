package com.example.demo.common.handler;

import com.example.demo.common.enums.ErrorEnum;
import com.example.demo.common.exception.GlobalRunTimeException;
import com.example.demo.common.model.response.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalRunTimeException.class)
    public <T> GlobalResponse<T> globalRunTimeException(GlobalRunTimeException e) {
        GlobalResponse<T> globalResponse = GlobalResponse.failure(e);
        log.error("service exception handled: {}", globalResponse.getMsg());
        return globalResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> GlobalResponse<T> methodArgumentResolutionException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return GlobalResponse.failure(ErrorEnum.PARAM_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public <T> GlobalResponse<T> exception(Exception e) {
        StringBuilder errorMsg = new StringBuilder();
        StackTraceElement[] elements = e.getStackTrace();
        int max = Math.min(elements.length, 5);
        if (e.getMessage() != null) {
            errorMsg.append(e.getMessage());
        }
        for (int i = 0; i < max; i++) {
            errorMsg.append("\n");
            errorMsg.append(elements[i].getClassName());
            errorMsg.append(" | method: ");
            errorMsg.append(elements[i].getMethodName());
            errorMsg.append(" | at line: ");
            errorMsg.append(elements[i].getLineNumber());
        }
        log.error("\nInternal server error, error：{},stack print: {}", e.getMessage(), errorMsg);
        return GlobalResponse.failure(ErrorEnum.INTERNAL_SERVER_ERROR);
    }

}
