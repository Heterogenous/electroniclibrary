package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

@WebServlet("/ActiveServlet")
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户激活的Servlet
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		//获取激活码
		String activeCode=request.getParameter("activeCode");
		//根据激活码查找用户，找到返回User对象，否则返回null;
		UserService userService=new UserServiceImpl();
		User user=userService.findUserByActiveCode(activeCode);
		//定义注册成败信息
		String Rmessages="";
		//修改用户的注册状态
		if(user!=null) {
			//修改注册状态
			user.setState(1);
			//清空注册码
		    user.setActiveCode(null);
		    //更新User数据
			userService.updateUser(user);
			Rmessages="<h1 style='color:red'>恭喜您成功激活账号！</h1><p><a href='http://localhost:8080"+request.getContextPath()+"/login.jsp'>登陆账号</a></p>";
		}else {
			Rmessages="<h1 style='color:red'>激活码错误或已激活!</h1><h3><p>有疑问请发email给管理员</p><p><a href='mailto:895246485@qq.com'>895246485@qq.com</a></p><p><a href='http://localhost:8080"+request.getContextPath()+"/login.jsp'>登陆账号</a></p></h3>";
		}
		request.setAttribute("Rmessages", Rmessages);
		request.getRequestDispatcher("/WEB-INF/registerState.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
