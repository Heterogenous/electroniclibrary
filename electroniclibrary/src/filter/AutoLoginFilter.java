package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import domain.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

public class AutoLoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		// 获得一个名为autologin的cookie
		Cookie[] cookies=request.getCookies();
		String autologin=null;
		for(int i=0;cookies!=null&&i<cookies.length;i++) {
			if("autologin".equals(cookies[i].getName())) {
				autologin=cookies[i].getValue();
				break;
			}
		}
		if(autologin!=null) {
			String[] parts=autologin.split("-");
			String username=parts[0];
			String password=parts[1];
			//检查用户名和密码
			UserService userService=new UserServiceImpl();
			User user=null;
			try {
				user=userService.findUserByUsernameAndPwd(username,password);
				if(user!=null) {
					//将用户状态user存入session域中
					request.getSession().setAttribute("user", user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//放行
		chain.doFilter(request, response);
		
	}
	
	public void desstroy() {
		
	}

}
