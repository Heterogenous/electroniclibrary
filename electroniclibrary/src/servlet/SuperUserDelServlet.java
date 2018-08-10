package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import serviceImpl.UserServiceImpl;

/**
 * 处理超级用户被删除操作
 */
@WebServlet("/SuperUserDelServlet")
public class SuperUserDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		int uid=Integer.parseInt(request.getParameter("uid"));
		//调用业务层
		UserService userService=new UserServiceImpl();
		boolean b=userService.deleteUserByUid(uid);
		if(b) {
			request.getSession().setAttribute("dSuc","删除用户成功!");
		}else {
			request.getSession().setAttribute("dSuc","删除用户失败!");
		}
		response.sendRedirect(request.getContextPath()+"/admin/admin-superuser.jsp");
	}

}
