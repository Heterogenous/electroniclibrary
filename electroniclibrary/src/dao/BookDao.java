package dao;


import java.util.List;

import domain.Book;
import domain.BookType;
import domain.Borrow;

public interface BookDao {
	/**
	 * 查询所有的书本
	 * @return list类型
	 */
	List<Book> select();
	
	/**
	 * 根据书的类型查找书本
	 * @param type 书本类型 字符串
	 * @return List类型
	 */
	List<Book> selectBookByType(String type);
	
	/**
	 * 根据上传时间最新的前10个查找书本
	 * @return List类型
	 */
	List<Book> selectBookOrderByUploadTime();
	
	/**
	 * 根据借阅次数最多的前10个查找书本
	 * @return List类型
	 */
	List<Book> selectBookOrderByBorrowTimes();
	
	/**
	 * 根据书本ID查找书本
	 * @param Bid 字符串	
	 * @return Book类型
	 */
	Book selectBookByBid(String bid);
	
	/**
	 * 更新书中的信息
	 * @param book
	 * @return
	 */
	boolean updateBook(Book book);
	
	//借书操作
	
	/**
	 * 先借书表添加借书内容
	 * @param borrow
	 * @return
	 */
	boolean insertBorrowBook(Borrow borrow);
	
	/**
	 * 因为用户被删除，所有的借书记录被消除,根据用户ID
	 * @param uid 用户ID
	 * @return 布尔类型
	 */
	boolean delBorrowDetailByUid(int uid);
	
	/**
	 * 根据借阅ID删除借阅中的一条记录
	 * @param borrowId 借阅ID整型
	 * @return　布尔型
	 */
	boolean delBorrowBookByBorrowId(int borrowId);
	
	/**
	 * 根据借阅ID查找借阅表中的数据
	 * @param borrowId 整型 借阅表的ID
	 * @return borrow类型
	 */
	Borrow selectBorrowBookByBorrowId(int borrowId);
	
	/**
	 * 根据书籍ID和用户ID查找是否已经借阅该本书
	 * @param bid 字符串 书籍ID
	 * @param uid　整型　用户ID
	 * @return 布尔型
	 */
	boolean selectBorrowByBidAndUid(String bid,int uid);
	
	/**
	 * 根据读者的ID查询借阅的课本
	 * @param uid 整型
	 * @return List类型
	 */
	List<Borrow> selectBorrowBookByReaderId(int uid);
	
	/**
	 * 借阅同一本书的读者有很多个
	 * 根据读者Id和归还书本的ID从借阅表中删除
	 * @param bid 字符串类型 uid 整型
	 * @return 布尔型
	 */
	boolean deleteBorrowBookByBid(int uid,String bid);
	
	/**
	 * 查找书类别
	 * @return 类别列表
	 */
	List<BookType> selectBookType();
	
	/**
	 * 获取所有借书情况
	 * @return
	 */
	List<Borrow> selectAllBorrowBook();
	
	/**
	 * 分页功能给管理员使用无须放到业务层
	 * 根据当前页和显示条数来查询书本
	 * @param currentPage 当前页 整型
	 * @param showPage 显示条数 整型
	 * @return list类型
	 */
	List<Book> selectBookByLimit(int currentPage,int showPage);
	
	/**
	 * 根据bid删除指定的电子书
	 * @param bid 字符串类型
	 * @return 布尔型
	 */
	boolean delBookByBid(String bid);
	
	/**
	 * 根据book对象插入
	 * @param book 对象
	 * @return 布尔型
	 */
	boolean insertBook(Book book);
	
	/**
	 * 判断是否存在相同照片名称
	 * @param imgUrl
	 * @return
	 */
	boolean isImgUrl(String imgUrl);
	
}
