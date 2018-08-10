package service;

import java.util.List;

import domain.User;

public interface UserService {
	/**
	 * 注册用户
	 * @param user 为User的javaBean
	 * @return 返回布尔型，判断是否注册成功
	 */
	boolean registerUser(User user) throws Exception;

	/**
	 * 根据username查询用户
	 * @param username
	 * @return
	 * @throws Exception
	 */
	boolean findUN(String username) throws Exception;

	boolean findM(String mail) throws Exception;

	User findUserByActiveCode(String activeCode) throws Exception;

	boolean updateUser(User user) throws Exception;
	
	User findUserByUsernameAndPwd(String username,String password) throws Exception;
	
	boolean update(User user);
	
	List<User> selectUsersByRole(String role);
	
	User selectUserByUid(int uid);
	
	boolean deleteUserByUid(int uid);
}
