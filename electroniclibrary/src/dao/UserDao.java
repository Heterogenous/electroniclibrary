package dao;

import java.util.List;

import domain.User;

public interface UserDao {
   /**
    * 
    * 添加用户
    * @param user JavaBean为User类型
    * @return 返回布尔型 判断是否添加成功
    */
	boolean insert(User user) throws Exception;
	
	/**
	 * 根据用户名查询
	 * @param username 需要查询的用户名
	 * @return 布尔型 true为存在这样的用户,否则不存在
	 * @throws Exception
	 */
	boolean findUN(String username) throws Exception;
	
	/**
	 * 根据mail查询
	 * @param mail 需要查询的电子邮件
	 * @return 布尔型 true为存在这样的用户,否则不存在
	 * @throws Exception
	 */
	boolean findM(String mail) throws Exception;
	
	/**
	 * 根据激活码查找用户
	 * @param activeCode
	 * @return User对象
	 * @throws Exception
	 */
	User findUserByActiveCode(String activeCode) throws Exception;
	
	/**
	 * 修改原来用户的数据(激活码、状态码)
	 * @param user
	 * @return 布尔型
	 * @throws Exception
	 */
	boolean updateUser(User user) throws Exception;
	
	/**
	 * 根据username查找用户
	 * @param username
	 * @return User对象
	 * @throws Exception
	 */
	User findUserByUsernameAndPwd(String username,String password) throws Exception;
	
	/**
	 * 更新用户全部的信息(除了激活码,注册时间)
	 * @param user
	 * @return
	 */
	boolean update(User user);
	
	/**
	 * 根据用户权限查找用户
	 * @param role 字符串类型
	 * @return list<User>类型
	 */
	List<User> selectUsersByRole(String role);
	
	/**
	 * 根据ID查找用户
	 * @param uid 整型
	 * @return User类型
	 */
	User selectUserByUid(int uid);
	
	/**
	 * 根据ID删除用户
	 * @param uid 整型
	 * @return	布尔型
	 */
	boolean deleteUserByUid(int uid);
	
	/**
	 * 管理员添加普通用户
	 * @param user对象
	 * @return 布尔类型
	 */
	boolean adminInsertUser(User user);
}
