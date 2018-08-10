package daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.BookDao;
import domain.Book;
import domain.BookType;
import domain.Borrow;
import utils.C3p0Utils;

public class BookDaoImpl implements BookDao {

	@Override
	public List<Book> select(){
		List<Book> bookList=new ArrayList<Book>();
		try {
			//创建QueryRunner对象
			QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
			String sql="select * from book";
			bookList=runner.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<Book> selectBookByType(String type){
		List<Book> bookList=new ArrayList<Book>();
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from book where type=?";
		try {
			bookList=runner.query(sql, new BeanListHandler<Book>(Book.class),new Object[] {type});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<Book> selectBookOrderByUploadTime() {
		List<Book> bookList=new ArrayList<Book>();
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from book order by uploadTime DESC limit 0,10";//获取表前10条记录
		try {
			bookList=runner.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<Book> selectBookOrderByBorrowTimes() {
		List<Book> bookList=new ArrayList<Book>();
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from book where borrowTimes>0 order by borrowTimes DESC limit 0,10";
		try {
			bookList=runner.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<Borrow> selectBorrowBookByReaderId(int uid) {
		List<Borrow> borrowBookList=null;
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from borrow where uid=?";
		try {
			borrowBookList=runner.query(sql,new BeanListHandler<Borrow>(Borrow.class),new Object[] {uid});
		}catch (Exception e) {
			e.printStackTrace();
		}
		return borrowBookList;
	}

	@Override
	public Book selectBookByBid(String bid) {
		Book book=null;
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from book where bid=?";
		try {
			book=runner.query(sql, new BeanHandler<Book>(Book.class),new Object[] {bid});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public boolean deleteBorrowBookByBid(int uid,String bid) {
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="delete from borrow where uid=? and bid=?";
		try {
		int	num=runner.update(sql, new Object[]{uid,bid});
		if(num>0)return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Borrow> selectAllBorrowBook() {
		List<Borrow> borrows=null;
		//创建QueryRunner对象
		QueryRunner runner =new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from borrow";
		try {
			borrows=runner.query(sql, new BeanListHandler<Borrow>(Borrow.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrows;
	}

	@Override
	public List<Book> selectBookByLimit(int currentPage, int showPage) {
		//分页功能
		List<Book> bookList=null;
		int startLimit=currentPage*showPage-showPage;
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from book limit ?,?";
		try {
			bookList=runner.query(sql,new BeanListHandler<Book>(Book.class), new Object[]{startLimit,showPage});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<BookType> selectBookType() {
		//创建QueryRunner对象
		List<BookType> list=null;
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from book_type";
		try {
			list=runner.query(sql, new BeanListHandler<BookType>(BookType.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean delBookByBid(String bid) {
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="delete from book where bid=?";
		try {
			int num=runner.update(sql,new Object[]{bid});
			if(num>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertBook(Book book) {
		//创建QueryRunner对象
	    QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
	    String sql="insert into book values(?,?,?,?,?,?,?,?,?)";
	    try {
			int num=runner.update(sql, new Object[]{book.getBid(),book.getBookName(),book.getAuthor(),book.getIntroduce(),book.getPublisher(),book.getType(),book.getImgUrl(),book.getUploadTime(),0});
			if(num>0)return true;
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isImgUrl(String imgUrl) {
		//创建QueryRunner对象
		Book book=null;
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from book where imgUrl=?";
		try {
			book=runner.query(sql, new BeanHandler<Book>(Book.class),new Object[]{imgUrl});
			if(book!=null) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Borrow selectBorrowBookByBorrowId(int borrowId) {
		Borrow borrow=null;
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from borrow where borrowId=?";
		try {
			borrow=runner.query(sql, new BeanHandler<Borrow>(Borrow.class), new Object[]{borrowId});			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrow;
	}

	@Override
	public boolean delBorrowBookByBorrowId(int borrowId) {
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="delete form borrow where borrowId=?";
		try {
			int num=runner.update(sql, new Object[]{borrowId});
			if(num>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delBorrowDetailByUid(int uid) {
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="delete from borrow where uid=?";
		try {
			int num=runner.update(sql, new Object[] {uid});
			if(num>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean selectBorrowByBidAndUid(String bid, int uid) {
		Borrow borrow=null;
		//创建QueryRunner对象
		QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		String sql="select * from borrow where bid=? and uid=?";
		try {
			borrow=runner.query(sql, new BeanHandler<Borrow>(Borrow.class),new Object[]{bid,uid});
			//System.out.println(borrow);
			if(borrow!=null)return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertBorrowBook(Borrow borrow) {
		//创建QueryRunner对象
	    QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
	    String sql="insert into borrow(uid,bid,borrowTime,returnTime) values(?,?,?,?)";
	    try {
			int num=runner.update(sql, new Object[]{borrow.getUid(),borrow.getBid(),borrow.getBorrowTime(),borrow.getReturnTime()});
			if(num>0)return true;
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		 QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
		 String sql="update book set bookName=?,author=?,introduce=?,publisher=?,type=?,imgUrl=?,borrowTimes=? where bid=?";
		 try {
			int num=runner.update(sql,new Object[] {book.getBookName(),book.getAuthor(),book.getIntroduce(),book.getPublisher(),book.getType(),book.getImgUrl(),book.getBorrowTimes(),book.getBid()});
			if(num>0)return true;
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	

}
