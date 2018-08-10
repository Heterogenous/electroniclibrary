package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Book;
import domain.User;
import serviceImpl.BookServiceImpl;

/**
 * 处理用户点击电子书内容
 */
@WebServlet("/FindBookDetailServlet")
public class FindBookDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取传过来的电子书ID
		String bid=request.getParameter("bid");
		User user=(User) request.getSession().getAttribute("user");
		//判断是否已经登陆
		if(user==null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else {
			//根据bid查找对应书的详细信息
			Book book=new BookServiceImpl().selectBookByBid(bid);
			//根据当前读者和书籍查阅两者是否存在借阅关系
			boolean b=new BookServiceImpl().isBorrowByBidAndUid(bid, user.getUid());
			//System.out.println(new BookServiceImpl().isBorrowByBidAndUid(bid, user.getUid()));
			//将信息和两者借阅是否有关系存入request中
			request.setAttribute("bookDetail", book);
			request.setAttribute("isBorrow", b);
			request.getRequestDispatcher("book-detail.jsp").forward(request, response);
		}
	}

}
