package serviceImpl;


import java.util.List;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import domain.User;
import service.UserService;
import utils.MailUtils;

public class UserServiceImpl implements UserService {
	//注册用户
	@Override
	public boolean registerUser(User user) throws Exception {
		//调用DAO层，往数据库中的user表添加数据并发送激活邮件
		UserDao userDao=new UserDaoImpl();
		boolean b=userDao.insert(user);
		String url="http://localhost:8080/crm/ActiveServlet?activeCode="+user.getActiveCode();
		String emailMsg="<h1>恭喜您完成注册！需要激活才算成功注册哟！</h1><h3><p>点击下面的链接完成激活吧!</p><a href='"+url+"'>"+url+"</a><p style='color:red'>如果不能打开页面，请复制该地址到浏览器打开</p><p>有疑问请发email给管理员</p><p><a href='mailto:895246485@qq.com'>895246485@qq.com</a></p></h3>";
		//开启线程发送邮件,完成注册时，顺便发送邮件
		new Thread(new Runnable() {	
			@Override
			public void run() {
					try {
						if(b)MailUtils.sendMail(user.getMail(), emailMsg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				
			}
		}).start();
		return b;
	}

	//根据用户名查找用户
	@Override
	public boolean findUN(String username) throws Exception {
		//调用DAO层，往数据库中的user表查找是否有username	
		return new UserDaoImpl().findUN(username);
	}

	//根据电子邮箱查找用户
	@Override
	public boolean findM(String mail) throws Exception {
		//调用DAO层，往数据库中的user表查找是否有mail	
		return new UserDaoImpl().findM(mail);
	}

	//根据激活码查找用户
	@Override
	public User findUserByActiveCode(String activeCode) throws Exception {
		//调用DAO层,往数据库中的user表根据激活码查找是否存在这样的用户
		return new UserDaoImpl().findUserByActiveCode(activeCode);
	}

	//更新用户的数据
	@Override
	public boolean updateUser(User user) throws Exception {
		//调用DAO层,往数据库中的User表根据修改后的User对象,将原来的User进行修改
		return new UserDaoImpl().updateUser(user);
	}

	@Override
	public User findUserByUsernameAndPwd(String username,String password) throws Exception {
		// 调用DAO层,往数据库中个User表根据username查找用户
		return new UserDaoImpl().findUserByUsernameAndPwd(username,password);
	}

	@Override
	public boolean update(User user) {
		// 调用DAO层,更新该用户的所有数据
		return new UserDaoImpl().update(user);
	}

	@Override
	public List<User> selectUsersByRole(String role) {
		//调用DAO层根据用户权限查找用户
		return new UserDaoImpl().selectUsersByRole(role);
	}

	@Override
	public User selectUserByUid(int uid) {
		//调用DAO层根据uid查找用户
		return new UserDaoImpl().selectUserByUid(uid);
	}

	@Override
	public boolean deleteUserByUid(int uid) {
		//调用DAO层根据ID删除用户
		return new UserDaoImpl().deleteUserByUid(uid);
	}

}
