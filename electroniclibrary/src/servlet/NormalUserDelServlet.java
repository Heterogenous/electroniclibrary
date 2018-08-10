package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import serviceImpl.BookServiceImpl;
import serviceImpl.UserServiceImpl;

/**
 * 处理普通用户被删除操作
 */
@WebServlet("/NormalUserDelServlet")
public class NormalUserDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		int uid=Integer.parseInt(request.getParameter("uid"));
		//调用业务层
		UserService userService=new UserServiceImpl();
		int uid2=uid;//获取需要删除借阅表信息的uid
		boolean b=userService.deleteUserByUid(uid);
		if(b) {
			new BookServiceImpl().deleteBorrowDetailByUid(uid2);//调用删除借阅表方法
			request.getSession().setAttribute("delSuc","删除用户成功!");
		}else {
			request.getSession().setAttribute("delSuc","删除用户失败!");
		}
		response.sendRedirect(request.getContextPath()+"/admin/admin-normaluser.jsp");
	}

}
