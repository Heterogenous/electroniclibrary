package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import serviceImpl.UserServiceImpl;

/**
 * 处理用户修改密码
 */
@WebServlet("/UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String newPassword=request.getParameter("newPassword");
		User user=(User)request.getSession().getAttribute("user");
		//修改密码
		user.setPassword(newPassword);
		boolean b=new UserServiceImpl().update(user);
		if(b) {
			//修改成功
			//request.getSession().setAttribute("user",user);
			request.setAttribute("updatePassword", "密码修改成功!请重新登陆");
			request.setAttribute("successOrfail", "1");
			//request.getRequestDispatcher("/client/updatepassword.jsp").forward(request, response);
		}else {
			//修改失败
			request.setAttribute("updatePassword", "密码修改失败!请重新修改");
			request.setAttribute("successOrfail", "0");
		}
		request.getRequestDispatcher("/client/updatepassword.jsp").forward(request, response);
		
	}

}
