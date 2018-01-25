package cn.badguy.dream.validator;

import cn.badguy.dream.exception.ParamException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ValidatorPass {

    @Before("execution(public * cn.badguy.dreamer.controller.*.*(..)) && args(..,bindingResult)")
    public void pass(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> msgs = bindingResult.getFieldErrors();
            Map<String, String> map = new HashMap<>();
            for (FieldError msg : msgs) {
                map.put(msg.getField(), msg.getDefaultMessage());
            }
            throw new ParamException(100001, map.toString());
        }
    }
}
