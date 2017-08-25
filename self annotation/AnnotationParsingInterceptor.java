package web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.guofudata.mooc.client.Member;
import com.guofudata.mooc.user.SecurityUtils;

import web.annotation.NeedLogin;

//解析注解
public class AnnotationParsingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (handler instanceof HandlerMethod) {
			HandlerMethod myHandlerMethod = (HandlerMethod) handler;
			Method method = myHandlerMethod.getMethod();

			// 判断用户请求的method上面是否存在注解
			boolean isAnnotation = method.isAnnotationPresent(NeedLogin.class);

			if (isAnnotation) {
				NeedLogin needLogin = method.getAnnotation(NeedLogin.class);// 类上有该标记
				if (needLogin.needLogin()) {
					Member user = SecurityUtils.currUser();
					if (user != null) {
						return true;
					}else {// 未登录
						response.sendRedirect("/login.htm");
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
