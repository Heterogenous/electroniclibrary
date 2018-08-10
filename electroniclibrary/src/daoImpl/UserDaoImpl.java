package daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.UserDao;
import domain.User;
import utils.C3p0Utils;

public class UserDaoImpl implements UserDao {

	
	//向数据库中user表添加数据
	@Override
	public boolean insert(User user) throws Exception {
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="insert into user(username,password,sex,mail,activeCode,registerTime) values (?,?,?,?,?,?)";
		int num=runner.update(sql,new Object[] {user.getUsername(),user.getPassword(),user.getSex(),user.getMail(),user.getActiveCode(),user.getRegisterTime()});
		if(num>0)return true;
		return false;
	}
	
	//向数据库中user表根据username查询
	public boolean findUN(String username) throws Exception {
		// 创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from user where username=?";
		User user=(User)runner.query(sql, new BeanHandler<User>(User.class), new Object[] {username});
		if(user!=null)return true;
		//System.out.println(user);
		return false;
	}
	
	//向数据库中user表根据mail查询
	public boolean findM(String mail) throws SQLException {
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from user where mail=?";
		User user=(User)runner.query(sql, new BeanHandler<User>(User.class), new Object[] {mail});
		if(user!=null)return true;
		return false;
	}

	public User findUserByActiveCode(String activeCode) throws Exception {
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from user where activeCode=?";
		User user=(User)runner.query(sql,new BeanHandler<User>(User.class), new Object[] {activeCode});
		return user;
	}

	public boolean updateUser(User user) throws Exception {
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="update user set state=?,activeCode=? where uid=?";
		int num=runner.update(sql, new Object[] {user.getState(),user.getActiveCode(),user.getUid()});
		if(num>0)return true;
		return false;
	}

	@Override
	public User findUserByUsernameAndPwd(String username,String password) throws Exception {
		// 创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from user where username=? and password=?";
		User user=(User)runner.query(sql, new BeanHandler<User>(User.class),new Object[] {username,password});		
		return user;
	}

	@Override
	public boolean update(User user) {
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="update user set username=?,password=?,sex=?,mail=?,role=?,state=? where uid=?";
		int num=0;
		try {
			num=runner.update(sql, new Object[]{user.getUsername(),user.getPassword(),user.getSex(),user.getMail(),user.getRole(),user.getState(),user.getUid()});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(num>0)return true;
		return false;
	}

	@Override
	public List<User> selectUsersByRole(String role) {
		List<User> users=null;
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from user where role=?";
		try {
			users=runner.query(sql, new BeanListHandler<User>(User.class), new Object[]{role});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User selectUserByUid(int uid) {
		User user=null;
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from user where uid=?";
		try {
			user=runner.query(sql, new BeanHandler<User>(User.class), new Object[]{uid});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean deleteUserByUid(int uid) {
		//创建QueryRunner
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="delete from user where uid=?";
		try {
			int num=runner.update(sql, new Object[] {uid});
			if(num>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//该方法不加入业务层
	@Override
	public boolean adminInsertUser(User user) {
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="insert into user(username,password,sex,mail,role,state,registerTime) values(?,?,?,?,?,?,?)";
		try {
			int num=runner.update(sql, new Object[]{user.getUsername(),user.getPassword(),user.getSex(),user.getMail(),user.getRole(),user.getState(),user.getRegisterTime()});
			if(num>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
