package web.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.guofudata.mooc.kop.service.CoursePostService;
import com.guofudata.mooc.user.SecurityUtils;
import com.kec.smartx.param.dto.CoursePost;

/**
 * 拦截play.htm,lessonstatistics.htm,lessondiscuss.htm请求 
 * 判断host是否变化更新cookie信息
 * 
 * @author gz
 */
public class ShareCoursePostInterceptor implements HandlerInterceptor {

	@Resource
	private CoursePostService coursePostService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uid = SecurityUtils.getCurrentUid();
		String postId = request.getParameter("postId");
		List<CoursePost> list = coursePostService.findCoursePost(postId, "");
		
		if(list != null){
			if(list.size()>0){
				if(list.get(0).getCourse().getSpeakerid().equals(uid)||list.get(0).getCourse().getUid().equals(uid)||list.get(0).getJoinUidList().contains(uid)){
					return true;
				}else{
					return false;
				}
			}else{
				response.sendRedirect(request.getContextPath() + "/house/lessonpresent.htm?postId="+postId); 
				return false;
			}
		}else{
			response.sendRedirect(request.getContextPath() + "/house/lessonpresent.htm?postId="+postId); 
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
