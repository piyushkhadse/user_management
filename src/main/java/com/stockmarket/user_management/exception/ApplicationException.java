package com.stockmarket.user_management.exception;

import com.stockmarket.user_management.domain.Error;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

@Getter
@Setter
public class ApplicationException extends RuntimeException{

    private Error error;
    @Transient
    private Integer statusCode;

    public ApplicationException(Error error, Integer statusCode) {
        this.error = error;
        this.statusCode = statusCode;
    }
}
