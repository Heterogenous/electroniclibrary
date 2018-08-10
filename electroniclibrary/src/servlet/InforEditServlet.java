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

/**
 * 修改用户信息
 */
@WebServlet("/InforEditServlet")
public class InforEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户修改的信息
		String mail=request.getParameter("mail");
		String sex=request.getParameter("sex");
		//从session中获取用户
		User user=(User)request.getSession().getAttribute("user");
		//判断用户是否已经修改
		if(user.getMail().equals(mail)&&user.getSex().equals(sex)) {
			//没有修改则返回到个人信息页面
			response.sendRedirect(request.getContextPath()+"/client/information.jsp");
		}else {
			//其中一个修改了
			String updateSuccess="";
			//如果修改了邮箱,则需要判断修改的邮箱是否和数据库中的有雷同
			if(!user.getMail().equals(mail)) {
				boolean a=false;
				try {
					a=new UserDaoImpl().findM(mail);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(a) {
					updateSuccess="修改失败！"+mail+"这个邮箱地址已存在！";
					request.getSession().setAttribute("updateSuccess", updateSuccess);
					response.sendRedirect(request.getContextPath()+"/client/information.jsp");
					return;
				}
			}
			//修改的邮箱不存在雷同
			User user2 =new User();
			user2.setUid(user.getUid());
			user2.setUsername(user.getUsername());
			user2.setPassword(user.getPassword());
			user2.setSex(sex);
			user2.setMail(mail);
			user2.setState(user.getState());
			user2.setRole(user.getRole());
			user2.setRegisterTime(user.getRegisterTime());
			boolean b=new UserDaoImpl().update(user2);
			if(b) {
					request.getSession().setAttribute("user", user2);
					updateSuccess="修改成功！";
			}else {
					updateSuccess="修改失败！";		
			}
			request.getSession().setAttribute("updateSuccess", updateSuccess);
			response.sendRedirect(request.getContextPath()+"/client/information.jsp");
		}
	}

}
