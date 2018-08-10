package serviceImpl;

import java.util.List;

import daoImpl.BookDaoImpl;
import domain.Book;
import domain.BookType;
import domain.Borrow;
import service.BookService;

public class BookServiceImpl implements BookService {

	@Override
	public List<Book> selectAllBook() {
		//调用DAO层查找所有的书本
		return new BookDaoImpl().select();
	}

	@Override
	public List<Book> selectNewUploadBook() {
		//调用DAO层查找最新上传的书本
		return new BookDaoImpl().selectBookOrderByUploadTime();
	}

	@Override
	public List<Book> selectBookByType(String type) {
		//调用DAO层根据类型查找书本
		return new BookDaoImpl().selectBookByType(type);
	}

	@Override
	public List<Book> selectHotBook() {
		//调用DAO层查找前10本最热门的书
		return new BookDaoImpl().selectBookOrderByBorrowTimes();
	}

	@Override
	public List<Borrow> selectBorrowBookByUid(int uid) {
		//调用DAO层根据读者ID查找借阅课本
		return new BookDaoImpl().selectBorrowBookByReaderId(uid);
	}

	@Override
	public Book selectBookByBid(String bid) {
		//调用DAO层根据书本ID查找书本
		return new BookDaoImpl().selectBookByBid(bid);
	}

	@Override
	public boolean deleteBorrowBookByBid(int uid,String bid) {
		//调用DAO层根据读者Id和还书的ID对借阅表进行删除
		return new BookDaoImpl().deleteBorrowBookByBid(uid,bid);
	}

	@Override
	public List<Borrow> selectAllBorrowBook() {
		//调用DAO层查询所有借阅表
		return new BookDaoImpl().selectAllBorrowBook();
	}

	@Override
	public List<BookType> selectBookType() {
		//调用DAO层查询书籍类型
		return new BookDaoImpl().selectBookType();
	}

	@Override
	public Borrow selectBorrowBookByBorrowId(int borrowId) {
		//调用DAO层根据借阅ID查找借阅表数据
		return new BookDaoImpl().selectBorrowBookByBorrowId(borrowId);
	}

	@Override
	public boolean deleteBorrowBookByBorrowId(int borrowId) {
		//调用DAO层根据借阅ID归还电子书
		return new BookDaoImpl().delBorrowBookByBorrowId(borrowId);
	}

	@Override
	public boolean deleteBorrowDetailByUid(int uid) {
		//调用DAO层根据用户ID删除借阅表信息
		return new BookDaoImpl().delBorrowDetailByUid(uid);
	}

	@Override
	public boolean isBorrowByBidAndUid(String bid, int uid) {
		//调用DAO层根据用户ID和书籍ID查阅是否存在这样的借阅关系
		return new BookDaoImpl().selectBorrowByBidAndUid(bid, uid);
	}

	@Override
	public boolean borrowBook(Borrow borrow) {
		//调用DAO层存入借入的书
		return new BookDaoImpl().insertBorrowBook(borrow);
	}

	@Override
	public boolean updateBook(Book book) {
		//调用DAO层更新书本内容
		return new BookDaoImpl().updateBook(book);
	}

}
