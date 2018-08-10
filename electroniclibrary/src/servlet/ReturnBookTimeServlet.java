package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BookService;
import serviceImpl.BookServiceImpl;

/**
 * 读者自己归还书本
 */
@WebServlet("/ReturnBookTimeServlet")
public class ReturnBookTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取归还书本的ID和归还的读者
		int uid=Integer.parseInt(request.getParameter("uid"));
		System.out.println(uid);
		String bid=request.getParameter("bid");
		//System.out.println(bid);
		//创建业务层对象
		BookService bookService=new BookServiceImpl();
		//根据归还书本的ID从借书表中删除
		boolean b=bookService.deleteBorrowBookByBid(uid,bid);
		if(b) {
			
			request.getSession().setAttribute("deleteBorrowBook", "归还成功!");
			
		}else {
	
			request.getSession().setAttribute("deleteBorrowBook", "归还失败!");
		}
		response.sendRedirect(request.getContextPath()+"/client/returnbooktime.jsp");
	}

}
