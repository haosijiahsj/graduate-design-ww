package com.zzz.exceptions;

/**
 * @author 胡胜钧
 * @date 12/30 0030.
 */
public class ZzzException extends RuntimeException {

    public ZzzException() {
        super();
    }

    public ZzzException(String message) {
        super(message);
    }

    public ZzzException(Throwable cause) {
        super(cause);
    }

    public ZzzException(String message, Throwable cause) {
        super(message, cause);
    }

}
