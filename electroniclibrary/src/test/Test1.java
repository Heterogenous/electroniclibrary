package test;

import domain.User;
import service.UserService;
import serviceImpl.UserServiceImpl;

public class Test1 {

	public static void main(String[] args) {
		UserService userService=new UserServiceImpl();
		User user=new User();
		user.setUsername("gouzebin");
		
		try {
			if(userService.registerUser(user)) {
				System.out.println("成功");
			}else {
				System.out.println("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
