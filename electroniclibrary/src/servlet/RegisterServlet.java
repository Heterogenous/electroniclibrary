package servlet;

import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserService;
import serviceImpl.UserServiceImpl;
import utils.UUIDUtils;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		//获取请求参数
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//String password1=request.getParameter("password1");
		String sex=request.getParameter("sex");
		String mail=request.getParameter("mail");
		//创建userservice业务层对象
		UserService userService=new UserServiceImpl();
		//判断注册的用户名和邮件是否重复
		try {
			//获取Session对象
			HttpSession session=request.getSession(false);
			//判断是否存在这样的Session
			if(session!=null) {
				//存在这样的Session,则防止Session在同一个网页上显示,强制使Session无效
				session.invalidate();
			}
			//当用户名或邮箱重复时请求转发至注册页面
			if(userService.findUN(username)||userService.findM(mail)) {
				//创建新的Session
				session=request.getSession();
				//用户名重复
				if(userService.findUN(username))
					session.setAttribute("UsernameAlready", "用户名已存在!");	
				//邮箱地址重复
				if(userService.findM(mail))
					session.setAttribute("MailAlready", "邮箱地址已存在!");
				
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				session.setAttribute("mail", mail);
				session.setAttribute("sex", sex);
				response.sendRedirect(request.getContextPath()+"/register.jsp");
			}
			else {
				//当用户名或密码不重复时注册用户
				//封装数据
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setSex(sex);
				user.setMail(mail);
				//设置激活码
				user.setActiveCode(UUIDUtils.getActiveCode());
				//设置注册时间HH为24小时进制,hh为12小时进制
				  //user.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				user.setRegisterTime(new Date());
				//调用service业务层注册用户
				userService.registerUser(user);
				//跳转页面
				//获取邮箱注册的后缀
				String[] mailtails=user.getMail().split("@");
				String mailTail=mailtails[1];
				String Rmessages="<h3>已经往您注册时填写的邮箱地址发送了一封账号激活码，点击这里<a href='https://mail."+mailTail+"'>登陆邮箱</a>进行激活</h3>";
				request.setAttribute("Rmessages", Rmessages);
				request.getRequestDispatcher("/WEB-INF/registerState.jsp").forward(request, response);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
