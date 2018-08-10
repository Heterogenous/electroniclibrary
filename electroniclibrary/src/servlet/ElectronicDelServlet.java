package servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.BookDaoImpl;
import domain.Book;

/**
 * 电子书被删除操作
 */
@WebServlet("/ElectronicDelServlet")
public class ElectronicDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取被删除的电子书的ID
		String bid=request.getParameter("bid");
		//调用DAO层根据bid删除电子书
		Book book=new BookDaoImpl().selectBookByBid(bid);
		boolean b=new BookDaoImpl().delBookByBid(bid);
		if(b){
			request.getSession().setAttribute("delBook", "删除该电子书成功!");
			//删除图片
			String filepath=request.getServletContext().getRealPath("/bookUrl/"+book.getImgUrl());
			File file=new File(filepath);
			if(file.exists()) {
				file.delete();
			}
		}else {
			request.getSession().setAttribute("delBook", "删除该电子书失败!");
		}
		response.sendRedirect(request.getContextPath()+"/admin/admin-electronicbook.jsp");
	}

}
