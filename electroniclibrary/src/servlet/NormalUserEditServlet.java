package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
 * 处理管理修改普通用户的操作
 */
@WebServlet("/NormalUserEditServlet")
public class NormalUserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String password=request.getParameter("password");
		String sex=request.getParameter("sex");
		String mail=request.getParameter("mail");
		int state=Integer.parseInt(request.getParameter("state"));
		int uid=Integer.parseInt(request.getParameter("uid"));
		//调用业务层获取uid为uid的用户
		UserService userService=new UserServiceImpl();
		User user=userService.selectUserByUid(uid);
		//判断用户是否修改
		if(user.getPassword().equals(password)&&user.getSex().equals(sex)&&user.getMail().equals(mail)&&user.getState()==state) {
			//没有修改则返回到普通用户管理的页面
			response.sendRedirect(request.getContextPath()+"/admin/admin-normaluser.jsp");
		}else {
			//其中有一个修改了
			String updateSuc="";
			//如果修改了邮箱,则需要判断修改的邮箱是否和数据库中的有雷同
			if(!user.getMail().equals(mail)) {
				boolean a=false;
				try {
					a=new UserDaoImpl().findM(mail);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(a) {
					updateSuc="修改失败！"+mail+"这个邮箱地址已存在！";
					request.getSession().setAttribute("updateSuc", updateSuc);
					response.sendRedirect(request.getContextPath()+"/admin/admin-normaluser.jsp");
					return;
				}
			}
			//修改的邮箱不存在雷同
			user.setPassword(password);
			user.setSex(sex);
			user.setMail(mail);
			user.setState(state);
			boolean b=userService.update(user);
			if(b) {
				updateSuc="修改成功！";
			}else {
				updateSuc="修改失败!";
			}
			request.getSession().setAttribute("updateSuc", updateSuc);
			response.sendRedirect(request.getContextPath()+"/admin/admin-normaluser.jsp");
		}
	}

}
