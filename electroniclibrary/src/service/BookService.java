package service;

import java.util.List;

import domain.Book;
import domain.BookType;
import domain.Borrow;

public interface BookService {
	List<Book> selectAllBook();
	List<Book> selectNewUploadBook();
	List<Book> selectBookByType(String type);
	List<Book> selectHotBook();
	Book selectBookByBid(String bid);
	//操作借阅表的
	Borrow selectBorrowBookByBorrowId(int borrowId);
	List<Borrow> selectBorrowBookByUid(int uid);
	boolean deleteBorrowBookByBid(int uid, String bid);
	List<Borrow> selectAllBorrowBook();
	List<BookType> selectBookType();
	boolean deleteBorrowBookByBorrowId(int borrowId);
	boolean deleteBorrowDetailByUid(int uid);
	boolean isBorrowByBidAndUid(String bid,int uid);
	boolean borrowBook(Borrow borrow);
	boolean updateBook(Book book);
}
