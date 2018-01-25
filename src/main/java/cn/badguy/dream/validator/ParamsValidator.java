package cn.badguy.dream.validator;

import cn.badguy.dream.pojo.Girl;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class ParamsValidator extends WebMvcConfigurerAdapter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String name = httpServletRequest.getParameter("name");
        String sex = httpServletRequest.getParameter("sex");
        Girl girl = new Girl();
        girl.setName(name);
        girl.setName(sex);
        if (girl.getName() != null && girl.getName() != null) {
            System.out.println("ok");
            return true;
        } else {
            System.out.println("no");
            return false;
        }
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new ParamsValidator()).addPathPatterns("/test/**");
        super.addInterceptors(registry);
    }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
