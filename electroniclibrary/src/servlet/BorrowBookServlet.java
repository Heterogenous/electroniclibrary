package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Book;
import domain.Borrow;
import domain.User;
import service.BookService;
import serviceImpl.BookServiceImpl;

/**
 * Servlet implementation class BorrowBook
 */
@WebServlet("/BorrowBookServlet")
public class BorrowBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid=request.getParameter("bid");
		//System.out.println(bid);
		User user=(User) request.getSession().getAttribute("user");
		BookService bookService=new BookServiceImpl();
		Borrow borrow=new Borrow();
		borrow.setBid(bid);
		borrow.setUid(user.getUid());
		borrow.setBorrowTime(new Date());
		
//		long d=new Date().getTime()+(24*60*60*1000)*30;
//		System.out.println(d);
//		Date date=new Date(d);
//		System.out.println(date);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String returnTime=sdf.format(date);
//		System.out.println(returnTime);
//		Date date2=null;
//		try {
//			date2 = sdf.parse(returnTime);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		borrow.setReturnTime(date2);
		borrow.setReturnTime(null);
		boolean b=bookService.borrowBook(borrow);
		if(b) {
			//则需要在book表中借阅次数+1
			//获取到该本书
			Book book=bookService.selectBookByBid(bid);
			book.setBorrowTimes(book.getBorrowTimes()+1);
			//更新书籍
			bookService.updateBook(book);
			request.setAttribute("borrowSuc", "借阅成功!");
		}else {
			request.setAttribute("borrowSuc", "借阅失败!");
		}
		request.getRequestDispatcher("book-detail.jsp").forward(request, response);;
	}

}
