package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceImpl.BookServiceImpl;

/**
 * Servlet implementation class SuperReturnBookServlet
 */
@WebServlet("/SuperReturnBookServlet")
public class SuperReturnBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取归还的电子书的借阅表ID删除数据
		int borrowId=Integer.parseInt(request.getParameter("borrowId"));
		boolean b=new BookServiceImpl().deleteBorrowBookByBorrowId(borrowId);
		if(b) {
			request.getSession().setAttribute("delBorrowBook", "归还电子书成功!");
		}else {
			request.getSession().setAttribute("delBorrowBook", "归还电子书失败!");
		}
		response.sendRedirect(request.getContextPath()+"/admin/admin-borrowbook.jsp");
	}

}
