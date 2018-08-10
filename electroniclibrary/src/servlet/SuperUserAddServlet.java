package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.UserDaoImpl;
import domain.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class NormalUserAddServlet
 */
@WebServlet("/SuperUserAddServlet")
public class SuperUserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String sex=request.getParameter("sex");
		String mail=request.getParameter("mail");
		int state=Integer.parseInt(request.getParameter("state"));
		//判断用户名和邮箱是否和数据库中的雷同
		UserService userService=new UserServiceImpl();
		try {
			if(userService.findM(mail)||userService.findUN(username)) {
				//其中有一个或两个为真
				if(userService.findM(mail)) {
					request.setAttribute("mailAlready", "该邮箱已被用来注册!");
				}
				if(userService.findUN(username)) {
					request.setAttribute("usernameAlready", "该账号已被注册!");
				}
				//把已经全部数据返回
				request.setAttribute("userName", username);
				request.setAttribute("passWord", password);
				request.setAttribute("seX", sex);
				request.setAttribute("maiL", mail);
				request.setAttribute("statE", state);
				request.getRequestDispatcher("/admin/superuser-add.jsp").forward(request, response);
			}else {
				//添加用户数据
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setMail(mail);
				user.setSex(sex);
				user.setRole("超级用户");
				user.setState(state);
				user.setRegisterTime(new Date());
				//两个都不存在雷同,则调用DAO层添加用户
				boolean b=new UserDaoImpl().adminInsertUser(user);
				if(b) {
					request.getSession().setAttribute("adminAddsuper", "添加用户成功!");
				}else {
					request.getSession().setAttribute("adminAddsuper", "添加用户失败!");
				}
				response.sendRedirect(request.getContextPath()+"/admin/admin-superuser.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
