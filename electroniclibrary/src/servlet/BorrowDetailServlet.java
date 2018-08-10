package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Book;
import domain.Borrow;
import domain.User;
import service.BookService;
import service.UserService;
import serviceImpl.BookServiceImpl;
import serviceImpl.UserServiceImpl;

/**
 * 给借阅表提供详情数据
 */
@WebServlet("/BorrowDetailServlet")
public class BorrowDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		int borrowId=Integer.parseInt(request.getParameter("borrowId"));
		String bid=request.getParameter("bid");
		int uid=Integer.parseInt(request.getParameter("uid"));
		//根据id查找对应的数据
		UserService userService=new UserServiceImpl();
		BookService bookService=new BookServiceImpl();
		//借阅人
		User user=userService.selectUserByUid(uid);
		//借阅的电子书
		Book book=bookService.selectBookByBid(bid);
		//借阅表
		Borrow borrow=bookService.selectBorrowBookByBorrowId(borrowId);
		//将数据存入request中
		request.setAttribute("buser", user);
		request.setAttribute("bbook", book);
		request.setAttribute("bborrow", borrow);
		//转发到详情页面
		request.getRequestDispatcher("/admin/borrowbook-detail.jsp").forward(request, response);
		
	}

}
