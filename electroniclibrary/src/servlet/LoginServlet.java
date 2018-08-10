package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取用户名和密码
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			//根据获取用户名和密码查找用户
			UserService userService=new UserServiceImpl();
			User user=userService.findUserByUsernameAndPwd(username,password);
			
			//1判断是否存在这样的用户
			if(user==null) {
				//1.1用户名或密码错误
				request.setAttribute("ErrorMessages", "用户名或密码错误!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else {
				//1.2判断用户是否为激活状态
				if(user.getState()==0) {
					//1.2.1State为0表示未激活状态
					request.setAttribute("ErrorMessages", "用户尚未激活,请前往注册时填写的邮箱里进行激活!");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else {
					//1.2.2判断用户的类型
					if("普通用户".equals(user.getRole())) {
						//1.2.2.1普通用户(client)
						//设置当前网页自动登陆，将用户状态user对象存入session域
						request.getSession().setAttribute("user", user);
						//发送自动登陆的Cookie
						String autoLogin=request.getParameter("autologin");
						if(autoLogin!=null) {
							Cookie cookie=new Cookie("autologin", username+"-"+password);
							//设置时效
							cookie.setMaxAge(Integer.parseInt(autoLogin));
							cookie.setPath(request.getContextPath());
							response.addCookie(cookie);
						}
						response.sendRedirect(request.getContextPath()+"/index.jsp");
					}else {
						//1.2.2.2超级用户(admin)
						request.getSession().setAttribute("superUser", user);
						response.sendRedirect(request.getContextPath()+"/admin/admin-index.jsp");
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
