package cn.badguy.dream.exception;

import cn.badguy.dream.common.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    private final Integer errorCode = 50000;
    private final String description = "sorry，we make a mistake. (^o^)Y";

    @Value("${debug}")
    private boolean debug;

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler
    @ResponseBody
    public ServerResponse handle(Exception e) {
        if (e instanceof BaseException) {
            return ServerResponse.createByErrorCodeMessage(((BaseException) e).getErrorCode(), ((BaseException) e).getDescription());
        } else {
            if (debug) {
                return ServerResponse.createByErrorCodeMessage(this.errorCode, e.toString());
            } else {
                logger.error("exception={}", e.getMessage());
                return ServerResponse.createByErrorCodeMessage(this.errorCode, this.description);
            }
        }
    }
}
