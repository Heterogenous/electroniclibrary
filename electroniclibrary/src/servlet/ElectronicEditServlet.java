package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.BookDaoImpl;
import domain.Book;
import serviceImpl.BookServiceImpl;

/**
 * Servlet implementation class ElectronicEditServlet
 */
@WebServlet("/ElectronicEditServlet")
public class ElectronicEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid=request.getParameter("bid");
		String bookName=request.getParameter("bookName");
		String author=request.getParameter("author");
		String type=request.getParameter("type");
		String publisher=request.getParameter("publisher");
		String introduce=request.getParameter("introduce");
		String imgUrl=request.getParameter("imgUrl");
		//判断照片是否雷同
		boolean b=new BookDaoImpl().isImgUrl(imgUrl);
		if(b) {
			request.getSession().setAttribute("imgAlready", "照片已存在相同名称");
			response.sendRedirect(request.getContextPath()+"/admin/electronicbook-edit.jsp");
		}else {
			//更新电子书
			Book book=new BookServiceImpl().selectBookByBid(bid);
			book.setBookName(bookName);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setImgUrl(imgUrl);
			book.setIntroduce(introduce);
			book.setType(type);
			if(new BookServiceImpl().updateBook(book)) {
				request.getSession().setAttribute("updateBook", "成功修改!");
			}else {
				request.getSession().setAttribute("updateBook", "修改失败!");
			}
			response.sendRedirect(request.getContextPath()+"/admin/admin-electronicbook.jsp");
		}
	}

}
