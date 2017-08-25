package web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.guofudata.mooc.client.Member;
import com.guofudata.mooc.kop.service.HouseService;
import com.guofudata.mooc.user.SecurityUtils;
import com.kec.smartx.param.dto.House;

/**
 * 拦截所有*.htm请求 
 * 根据请求host获取企业sid,logo存放到cookie中 
 * 判断host是否变化更新cookie信息
 * 
 * @author yxm
 */
public class NoIdentityInterceptor implements HandlerInterceptor {

	@Resource
	private HouseService houseService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	
		House house = houseService.findHouseByHost(request.getHeader("Host"));
		if(house != null){
			if("1".equals(house.getFlowExhausted())){
				//流量已用完
				response.sendRedirect(request.getContextPath() + "/notfound.html"); 
				return false;
			}
		}

		
		Member user = SecurityUtils.currUser();
		
		if(user != null){
			
			String cid = user.getCid();
			String requestUrl = request.getRequestURI(); 
			
			if(requestUrl.contains("login.htm")||requestUrl.contains("hasnoidentity.htm")||requestUrl.contains("optimizeInviteCode.htm")){
				return true;
			}else{
				if(StringUtils.isBlank(cid)){
					SecurityUtils.logout();
					response.sendRedirect(request.getContextPath() + "/index.htm"); 
					return false;
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
