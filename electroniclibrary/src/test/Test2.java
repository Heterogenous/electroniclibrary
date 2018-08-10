package test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.omg.CORBA.Request;

import daoImpl.BookDaoImpl;
import domain.Book;
import domain.User;
import service.UserService;
import serviceImpl.UserServiceImpl;
import utils.C3p0Utils;
import utils.UUIDUtils;

public class Test2 {

	public static void main(String[] args) {
		/**
		 * 测试时如果不需要邮箱发送请先到注释发送邮件代码
		 */
		//测试查找是否存在用户名和邮箱
//		UserService userService=new UserServiceImpl();
//		try {
//			boolean usernameAlready=userService.findUN("hhhh");
//			System.out.println(usernameAlready);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//测试注册用户
//		User user=new User();
//		user.setUsername("ljy");
//		user.setPassword("12345");
//		try {
//			System.out.println(userService.registerUser(user));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//测试更新方法
//		String activeCode="c4bd852e1c6e4b14a80fe558202208c4";
//		
//		try {
//			User user=(User)userService.findUserByActiveCode(activeCode);
//			String registerTime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
//			user.setRegisterTime(registerTime);
//			boolean b=userService.updateUser(user);
//			if(b)System.out.println("注册激活成功!");
//			else {
//				System.out.println("注册激活失败");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
		//测试查找用户根据用户名，返回User对象
//		User user;
//		try {
//			user = userService.findUserByUsername("huangru1");
//			System.out.println(user);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//System.out.println(UUIDUtils.getActiveCode());
//		boolean b=new BookDaoImpl().isImgUrl("ABCmurder.jpg");
		Date date=new Date();
		//int d=30;
		long date2=date.getTime()+24*60*60*1000;
		Date d=new Date(date2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(d));
		
	}

}
